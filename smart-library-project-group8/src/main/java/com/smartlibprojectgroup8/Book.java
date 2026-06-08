package com.smartlibprojectgroup8;

public class Book {
    private final int isbn;
    private final String title;
    private final String author;
    private Book left;
    private Book right;

    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.left = null;
        this.right = null;
    }

    public int getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Book getLeft() { return left; }
    public Book getRight() { return right; }
    public void setLeft(Book left) { this.left = left; }
    public void setRight(Book right) { this.right = right; }

    @Override
    public String toString() {
        return "\'" + title + "\' by " + author + " (ISBN: " + isbn + ")";
    }

    public void printDetails() {
        System.out.println("\'" + title + "\' by " + author + " (ISBN: " + isbn + ")");
    }
}
