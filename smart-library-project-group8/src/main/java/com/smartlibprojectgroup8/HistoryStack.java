package com.smartlibprojectgroup8;

public class HistoryStack {
    private Book top;

    public void push(Book book){
        if (book == null) return;
        
        Book clonedBook = new Book(book.getIsbn(), book.getTitle(), book.getAuthor());

        clonedBook.setRight(top);
        top = clonedBook;
    }

    public Book pop(){
        if (isEmpty()) return null;

        Book temp = top;
        top = top.getRight();

        return temp;
    }

    public Book search(int isbn){
        Book current = top;

        while (current != null) {
            if (current.getIsbn() == isbn) break;
            current = current.getRight();
        }

        return current;
    }

    public Book peek(){
        return top;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public void displayHistory(){
        if(isEmpty()){
            System.out.println("No history found!");
            return;
        }

        Book current = top;
        int i = 1;

        while (current != null) {
            System.out.print(i++ + " ");
            current.printDetails();
            current = current.getRight();
        }
    }
}
