package com.smartlibprojectgroup8;
import java.util.ArrayList;

class BorrowInventory extends ArrayList<Book> {
    @Override
    public boolean add(Book newBook){
        if (search(newBook.getIsbn()) != null) return true;
        Book clonedBook = new Book(newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor());
        return super.add(clonedBook);
    }

    public Book search(int isbn) {
        for (Book currentBook: this) {
            if (currentBook.getIsbn() == isbn){
                return currentBook;
            }
        }
        return null;
    }

    public Book removeBook(int isbn) {
        Book foundBook = search(isbn);
        if (foundBook != null) {
            remove(foundBook); 
            return foundBook;
        }
        return null;
    }

    public void printList() {
        if (isEmpty()) {
            System.out.println("You have no borrowed books.");
            return;
        }
        int i = 1;
        for (Book currentBook: this) {
            System.out.println(i++ + " " + currentBook);
        }
    }
}