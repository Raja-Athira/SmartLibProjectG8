package com.smartlibprojectgroup8;

public class HistoryStack {

    private Book top; //Reference to the top book in the stack

    //Push a book onto the stack
    public void push(Book book){
        if (book == null) return;  //Return nothing if the book is null
        
        //Create a copy of the book to avoid modifying the original object
        Book clonedBook = new Book(book.getIsbn(), book.getTitle(), book.getAuthor());

        clonedBook.setRight(top); //Link the new book to the current top
        top = clonedBook;         //Update top to the new book
    }

    //Remove and return the top book from the stack
    public Book pop(){
        if (isEmpty()) return null; //Return null if the stack is empty

        Book temp = top;        //Store current top book
        top = top.getRight();   //Move top to the next book
        return temp;            //Return removed book
    }

    //Search book by its ISBN
    public Book search(int isbn){
        Book current = top;

        //Traverse the stack until ISBN is found or end of stack is reached
        while (current != null) {
            if (current.getIsbn() == isbn) break;
            current = current.getRight();
        }
        return current; //Return found book or null if not found
    }

    //Return the top book in stack without removing it
    public Book peek(){
        return top;
    }

    //Check whether the stack is empty
    public boolean isEmpty(){
        return top == null;
    }
    
    //Display all books in the history stack
    public void displayHistory(){
        if(isEmpty()){ //Check if stack is empty
            System.out.println("No history found!");
            return;
        }
        Book current = top;
        int i = 1;

        //Traverse and display each book
        while (current != null) {
            System.out.println(i++ + " " + current);
            current = current.getRight();
        }
    }
}
