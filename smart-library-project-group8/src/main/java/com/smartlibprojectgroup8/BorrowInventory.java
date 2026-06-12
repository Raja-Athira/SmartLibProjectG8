package com.smartlibprojectgroup8;
import java.util.ArrayList;

public class BorrowInventory extends ArrayList<Book> {
    @Override
    // Add book into the Borrowing Inventory
    public boolean add(Book newBook){
        if (search(newBook.getIsbn()) != null) return true;
        Book clonedBook = new Book(newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor());
        return super.add(clonedBook);
    }

    // Search book by ISBN
    public Book search(int isbn) {
        for (Book currentBook: this) {
            if (currentBook.getIsbn() == isbn){
                return currentBook;
            }
        }
        return null;
    }

    // Remove book
    public Book removeBook(int isbn) {
        Book foundBook = search(isbn);
        if (foundBook != null) {
            remove(foundBook);
            return foundBook;
        }
        return null;
    }

    // Print the list of books inside the Borrwing Inventory
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