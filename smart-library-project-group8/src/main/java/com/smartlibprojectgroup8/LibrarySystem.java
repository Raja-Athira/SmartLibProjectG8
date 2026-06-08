package com.smartlibprojectgroup8;

interface LibraryADT {
    Book searchBook(int isbn);
    Book searchBookInInventory(int isbn);
    int getInventorySize();

    void addBook(int isbn, String title, String author);
    void borrowBook(Book toBorrow);
    void returnBook(Book toReturn);
    
    void viewInventory();
    void viewLatestHistory();
    void showBookCatalogue();

    void loadCatalogueFromFile(String fileName);
    void saveCatalogueToFile(String fileName);
}

public class LibrarySystem implements LibraryADT {    
    private final BookBST Catalogue = new BookBST();
    private final HistoryStack BorrowHistory = new HistoryStack();
    private final BorrowInventory Inventory = new BorrowInventory();

    @Override
    public Book searchBook(int isbn) {
        return Catalogue.search(isbn);
    }

    @Override
    public Book searchBookInInventory(int isbn) {
        return Inventory.search(isbn);
    }

    @Override
    public int getInventorySize() {
        return Inventory.size();
    }

    @Override
    public void addBook(int isbn, String title, String author) {
        Catalogue.insert(new Book(isbn, title, author));
    }

    @Override
    public void borrowBook(Book toBorrow) {
        if (toBorrow != null) {
            Catalogue.remove(toBorrow);
            BorrowHistory.push(toBorrow);
            Inventory.add(toBorrow);
        }
    }

    @Override
    public void returnBook(Book toReturn) {
        if (toReturn != null) {
            Book toReturnClone = new Book(toReturn.getIsbn(), toReturn.getTitle(), toReturn.getAuthor());
            Catalogue.insert(toReturnClone);
            Inventory.removeBook(toReturn.getIsbn());
        }
    }

    @Override
    public void viewInventory() {
        Inventory.printList();
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
