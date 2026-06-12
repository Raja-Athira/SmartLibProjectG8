package com.smartlibprojectgroup8;

interface LibraryADT {
    // Searches for a book in the catalogue by ISBN
    Book searchBook(int isbn);

    // Searches for a borrowed book in the inventory by ISBN
    Book searchBookInInventory(int isbn);

    // Returns the number of books currently borrowed
    int getInventorySize();

    // Creates a new book and adds it to the catalogue
    void addBook(int isbn, String title, String author);

    // Removes a book from the catalogue and marks it as borrowed
    void borrowBook(Book toBorrow);

    // Returns a borrowed book back to the catalogue
    void returnBook(Book toReturn);

    // Displays all currently borrowed books
    void viewInventory();

    // Displays the borrow history from most recent to oldest
    void viewLatestHistory();

    // Displays all available books in the catalogue
    void showBookCatalogue();

    // Loads book records into the catalogue from a file
    void loadCatalogueFromFile(String fileName);

    // Saves the current catalogue to a file
    void saveCatalogueToFile(String fileName);
}

public class LibrarySystem implements LibraryADT {
    private final BookBST Catalogue = new BookBST();
    private final HistoryStack BorrowHistory = new HistoryStack();
    private final BorrowInventory Inventory = new BorrowInventory();

    // Searches and returns a book from the catalogue by ISBN
    @Override
    public Book searchBook(int isbn) {
        return Catalogue.search(isbn);
    }

    // Searches and returns a book from the borrow inventory by ISBN
    @Override
    public Book searchBookInInventory(int isbn) {
        return Inventory.search(isbn);
    }

    // Returns the total count of books currently in the borrow inventory
    @Override
    public int getInventorySize() {
        return Inventory.size();
    }

    // Creates a new Book object and inserts it into the catalogue
    @Override
    public void addBook(int isbn, String title, String author) {
        Catalogue.insert(new Book(isbn, title, author));
    }

    // Removes the book from the catalogue, logs it to history, and adds it to inventory
    @Override
    public void borrowBook(Book toBorrow) {
        if (toBorrow != null) {
            Catalogue.remove(toBorrow);
            BorrowHistory.push(toBorrow);
            Inventory.add(toBorrow);
        }
    }

    // Reinserts the book into the catalogue and removes it from the inventory
    @Override
    public void returnBook(Book toReturn) {
        if (toReturn != null) {
            Catalogue.insert(toReturn);
            Inventory.removeBook(toReturn.getIsbn());
        }
    }

    // Prints the list of all currently borrowed books
    @Override
    public void viewInventory() {
        Inventory.printList();
    }

    // Prints the borrow history stack from most recent to oldest
    @Override
    public void viewLatestHistory() {
        BorrowHistory.displayHistory();
    }

    // Prints all books available in the catalogue
    @Override
    public void showBookCatalogue() {
        Catalogue.printList();
    }

    // Loads books from the given file into the catalogue
    @Override
    public void loadCatalogueFromFile(String filename) {
        Catalogue.loadFromFile(filename);
    }

    // Saves all catalogue books to the given file
    @Override
    public void saveCatalogueToFile(String filename) {
        Catalogue.saveToFile(filename);
    }
}
