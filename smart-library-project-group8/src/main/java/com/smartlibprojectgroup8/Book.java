package com.smartlibprojectgroup8;
/**
 -Book class represents a book in the Smart Library System.
 -Each book contains ISBN, title, author, and tree references
 -for BST implementation (parent, left, right children).
 */
public class Book {
    //Unique International Standard Book Number - used as BST key 
    private final int isbn;
    //Title of the book
    private final String title;
    // Author of the book
    private final String author;
    // Parent node in BST (used for deletion operations)
    private Book parent;
    // refer to left child (books with smaller ISBN)
    private Book left;
    // refer to right child (books with smaller ISBN)
    private Book right;

    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author; 
    // parent, left, right are null by default
    }

    public int getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Book getLeft() { return left; }
    public Book getRight() { return right; }
    public Book getParent() { return parent; }

    public void setLeft(Book left) {
        this.left = left;
        if (left != null) {
            left.setParent(this);
        }
    }

    public void setRight(Book right) {
        this.right = right;
        if (right != null) {
            right.setParent(this);
        }
    }

    public void setParent(Book parent) {
        this.parent = parent;
    }

    public void removeChild(Book child) {
        if (left.equals(child)) left = null;
        if (right.equals(child)) right = null;
    }

    @Override
    public String toString() {
        return "\'" + title + "\' by " + author + " (ISBN: " + isbn + ")";
    }

    public void printDetails() {
        System.out.println("\'" + title + "\' by " + author + " (ISBN: " + isbn + ")");
    }
}
