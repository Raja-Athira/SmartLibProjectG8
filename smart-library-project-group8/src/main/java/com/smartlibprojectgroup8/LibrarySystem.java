package com.smartlibprojectgroup8;

interface LibraryADT {
    Book searchBook(int isbn);
    Book searchBookInBorrowHistory(int isbn);
    void addBook(int isbn, String title, String author);
    void borrowBook(Book toBorrow);
    
    void viewLatestHistory();
    void showBookCatalogue();

    void loadCatalogueFromFile(String fileName);
    void saveCatalogueToFile(String fileName);
}

public class LibrarySystem implements LibraryADT {    
    private final BookBST Catalogue = new BookBST();
    private final HistoryStack BorrowHistory = new HistoryStack();

    @Override
    public Book searchBook(int isbn) {
        return Catalogue.search(isbn);
    }

    @Override
    public Book searchBookInBorrowHistory(int isbn) {
        return BorrowHistory.search(isbn);
    }

    @Override
    public void addBook(int isbn, String title, String author) {
        Catalogue.insert(new Book(isbn, title, author));
    }

    @Override
    public void borrowBook(Book toBorrow) {
        if (toBorrow != null) {
            BorrowHistory.push(toBorrow);
        }
    }

    @Override
    public void viewLatestHistory() {
        BorrowHistory.displayHistory();
    }

    @Override
    public void showBookCatalogue() {
        Catalogue.printList();
    }

    @Override
    public void loadCatalogueFromFile(String filename) {
        Catalogue.loadFromFile(filename);
    }

    @Override
    public void saveCatalogueToFile(String filename) {
        Catalogue.saveToFile(filename);
    }
}
