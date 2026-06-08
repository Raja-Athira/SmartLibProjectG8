package com.smartlibprojectgroup8;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class BookBST {
    private Book root;
    
    public void insert(Book newBook) {
        root = insertRec(root, newBook);
    }

    private Book insertRec(Book currentRoot, Book newBook) {
        if (currentRoot == null) {
            return newBook;
        }

        int rootIsbn = currentRoot.getIsbn();
        int newIsbn = newBook.getIsbn();

        if (newIsbn < rootIsbn) {
            currentRoot.setLeft(insertRec(currentRoot.getLeft(), newBook));
        }
        else if (newIsbn > rootIsbn) {
            currentRoot.setRight(insertRec(currentRoot.getRight(), newBook));
        }
        
        return currentRoot;
    }

    public Book search(int isbn) {
        return searchRec(root, isbn);
    }

    private Book searchRec(Book current, int isbn){
        if (current == null){
            return null;
        }

        if (isbn == current.getIsbn()){
            return current;
        }

        if (isbn < current.getIsbn()){
            return searchRec(current.getLeft(), isbn);
        }

        return searchRec(current.getRight(), isbn);
    }

    public void printList() { // Breath-First Recursive Search
        Queue<Book> traverseQueue = new ArrayDeque<>();
        if (root == null) {
            System.out.println("There are no books in here!");
            return;
        }

        int i = 1;
        traverseQueue.add(root);
        printListRec(i, traverseQueue);
    }

    private void printListRec(int i, Queue<Book> traverseQueue) {
        Book currentBook = traverseQueue.poll();

        System.out.print(i++ + " " + currentBook);

        if (currentBook.getRight() != null) traverseQueue.add(currentBook.getRight());
        if (currentBook.getLeft() != null) traverseQueue.add(currentBook.getLeft());

        if (traverseQueue.isEmpty()) return;
        printListRec(i, traverseQueue);
    }

    // Load and Save with a .csv file

    public void loadFromFile(String filename) {
        String regex = "\\s*\\d+\\s*,\\s*.*\\s*,\\s*.*\\s*";
        try (BufferedReader Reader = new BufferedReader(new FileReader(filename))) {
            String line = Reader.readLine();
            while (line != null) {
                if (line.matches(regex)) {

                    String[] separatedStrings = line.split(",");
                    int isbn = Integer.parseInt(separatedStrings[0].strip());
                    String title = separatedStrings[1].strip();
                    String author = separatedStrings[2].strip();

                    insert(new Book(isbn, title, author));
                }
                line = Reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void saveToFile(String filename) {
        try (FileWriter Writer = new FileWriter(filename)) {
            Writer.write("ISBN, TITLE, AUTHOR\n");

            Queue<Book> traverseQueue = new ArrayDeque<>();
            if (root != null) {
                traverseQueue.add(root);
                saveToFileRec(Writer, traverseQueue);
            }
            
            Writer.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void saveToFileRec(FileWriter Writer, Queue<Book> traverseQueue) throws IOException {
        Book currentBook = traverseQueue.poll();

        Writer.write(currentBook.getIsbn() + "," + currentBook.getTitle() + "," + currentBook.getAuthor());

        if (currentBook.getRight() != null) traverseQueue.add(currentBook.getRight());
        if (currentBook.getLeft() != null) traverseQueue.add(currentBook.getLeft());

        if (traverseQueue.isEmpty()) return;
        saveToFileRec(Writer, traverseQueue);
    }
}