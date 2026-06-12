package com.smartlibprojectgroup8;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
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
        newBook.setParent(currentRoot);

        if (newIsbn < rootIsbn) {
            currentRoot.setLeft(insertRec(currentRoot.getLeft(), newBook));
            if (currentRoot.getLeft() != null) currentRoot.getLeft().setParent(currentRoot);
        }
        else if (newIsbn > rootIsbn) {
            currentRoot.setRight(insertRec(currentRoot.getRight(), newBook));
            if (currentRoot.getRight() != null) currentRoot.getRight().setParent(currentRoot);
        }
        
        return currentRoot;
    }

    public void remove(Book removeBook) {
        if (removeBook == null) return;
        
        if (removeBook.getRight() == null && removeBook.getLeft() == null) {
            Book parent = removeBook.getParent();
            if (parent != null) {
                parent.removeChild(removeBook);
            }
            
            if (root.equals(removeBook)) root = null;
        } else if (removeBook.getRight() != null && removeBook.getLeft() != null) {
            Book parent = removeBook.getParent();
            Book successor = getSuccessor(removeBook);
            Book successorParent = successor.getParent();
            if (successorParent != null) {
                successorParent.removeChild(successor);
            }

            successor.setLeft(removeBook.getLeft());
            successor.setRight(removeBook.getRight());
            successor.setParent(removeBook.getParent());

            if (root.equals(removeBook)) root = successor;
            
            if (parent != null) {
                parent.removeChild(removeBook);
            }
        } else {
            Book successor = (removeBook.getRight() != null) ? removeBook.getRight() : removeBook.getLeft();
            if (successor != null) {
                Book successorParent = successor.getParent();
                if (successorParent != null) {
                    successorParent.removeChild(successor);
                }

                successor.setParent(removeBook.getParent());
                if (root.equals(removeBook)) root = successor;
            }
        }

        removeBook.setParent(null);
        removeBook.setRight(null);
        removeBook.setLeft(null);
    }

    private Book getSuccessor(Book currentRoot){
        currentRoot = currentRoot.getRight();
        while (currentRoot != null && currentRoot.getLeft() != null)
            currentRoot = currentRoot.getLeft();
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

        System.out.println(i++ + " " + currentBook);
        //System.out.println("Parent: " + currentBook.getParent());
        //System.out.println("Left: " + currentBook.getLeft());
        //System.out.println("Right: " + currentBook.getRight());
        //System.out.println("");

        if (currentBook.getRight() != null) traverseQueue.add(currentBook.getRight());
        if (currentBook.getLeft() != null) traverseQueue.add(currentBook.getLeft());

        if (traverseQueue.isEmpty()) return;
        printListRec(i, traverseQueue);
    }

    private void buildBalancedTree(ArrayList<Book> booksArray) {
        // Sort the array by ISBN
        booksArray.sort(Comparator.comparingInt(Book::getIsbn));
        root = buildBalancedTreeRec(booksArray, null,  0, booksArray.size() - 1);
    }

    private Book buildBalancedTreeRec(ArrayList<Book> booksArray, Book parent, int start, int end) {
        // Base case
        if (start > end) 
            return null;
        
        int mid = (start + end) / 2;
        Book currentRoot = booksArray.get(mid);
        
        if (currentRoot == null) return null;

        currentRoot.setParent(parent);
        currentRoot.setLeft(buildBalancedTreeRec(booksArray, currentRoot, start, mid - 1));
        currentRoot.setRight(buildBalancedTreeRec(booksArray, currentRoot, mid + 1, end));

        return currentRoot;
    }

    // Load and Save with a .csv/.txt file

    public void loadFromFile(String filename) {
        String regex = "\\s*\\d+\\s*,\\s*.*\\s*,\\s*.*\\s*";
        ArrayList<Book> booksArray = new ArrayList<>();
        System.out.println("Reading Books from File...");
        try (BufferedReader Reader = new BufferedReader(new FileReader(new File(filename)))) {
            String line = Reader.readLine();
            while (line != null) {
                if (line.matches(regex)) {
                    
                    String[] separatedStrings = line.split(",");
                    int isbn = Integer.parseInt(separatedStrings[0].strip());
                    String title = separatedStrings[1].strip();
                    String author = separatedStrings[2].strip();

                    booksArray.add(new Book(isbn, title, author));
                }
                line = Reader.readLine();
            }
            System.out.println("Read file successfully: Loading " + booksArray.size() + " books...");
            buildBalancedTree(booksArray);
        } catch (Exception e) {
            System.out.println("File not found. Try changing your terminal's directory to this project folder");
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