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
    // return book's ISBN title
    public int getIsbn() { return isbn; }
    // return book's title
    public String getTitle() { return title; }
    // return book's author
    public String getAuthor() { return author; }
    // return left child node (smaller ISBN)
    public Book getLeft() { return left; }
    // return right child node (larger ISBN)
    public Book getRight() { return right; }
    // return parent node
    public Book getParent() { return parent; }

    /* Sets the left child and establishes parent-child relationship, 
     left Book node to become left child */
    
    public void setLeft(Book left) {
        this.left = left;
        if (left != null) {
         // Maintain bidirectional link
            left.setParent(this);
        }
    }
    parent; }

    /* Sets the right child and establishes parent-child relationship, 
     right Book node to become right child */

    public void setRight(Book right) {
        this.right = right;
        if (right != null) {
         // Maintain bidirectional link
            right.setParent(this);
        }
    }

   /*Sets the parent node (used when inserting/deleting), 
     Book node to become parent
     */
    public void setParent(Book parent) {
        this.parent = parent;
    }
 
/* Removes a child node (left or right) by setting to null, 
     Used during deletion operations,
      The child node to remove (must be left or right)
     */
    public void removeChild(Book child) {
        if (left.equals(child)) left = null;
        if (right.equals(child)) right = null;
    }


  /* Returns formatted string representation of the book.
     Format: 'Title' by Author (ISBN: number)
     return Formatted book details
     */
    @Override
    public String toString() {
        return "\'" + title + "\' by " + author + " (ISBN: " + isbn + ")";
    }

   // Prints book details to console
    public void printDetails() {
        System.out.println("\'" + title + "\' by " + author + " (ISBN: " + isbn + ")");
    }
}
