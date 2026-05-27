/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.library_adt;

/**
 *
 * @author puterahariz06
 */

import java.util.Scanner;
public class SmartLibrary implements Library_ADT {
    
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();
    
    
    public void addBook(int isbn, String title, String author){
    catalogue.insert(isbn,title,author);
    System.out.print("Book has been added");
    }
    
    public void searchBook(int isbn){
        Book b = catalogue.search(isbn);
        if(b != null)
        System.out.println("Book" + b.isbn + " title  " + b.title + " written by " + b.author + " is found ");
        else 
            System.out.print("Book is not found");
    }
    
    public void borrowBook(int isbn){
    Book b = catalogue.search(isbn);
    if(b != null){
        history.push(b);
        System.out.println("Book" + b.isbn + " title  " + b.title + " written by " + b.author + " is borrowed ");
    }else{ 
            System.out.print("Book is not found");
    }
    }
    
    public void viewLatestHistory(){
    history.show();
    }
    
    public void runMenu(){
    Scanner sc = new Scanner(System.in);
    while(true){
    printMenu();
    System.out.print("Choice: ");
    if(!sc.hasNextInt()){
    System.out.println("Invalid input. Please enter number only.");
    sc.next();
    continue;
    }
    int choice = sc.nextInt();
    if(choice == 5) break;
    handleChoice(choice, sc);
    }
    sc.close();
    }
    
    private void printMenu(){
     System.out.println("\n--- SmartLibrary Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Search Book (BST)");
        System.out.println("3. Borrow Book (Stack)");
        System.out.println("4. View History");
        System.out.println("5. Exit");
    }
    
    private void handleChoice(int choice, Scanner sc){
        switch(choice){
            case 1:
                System.out.print("Enter ISBN: ");
                int i = sc.nextInt();
                System.out.print("Enter Title: ");
                String t = sc.next();
                System.out.print("Enter Author: ");
                String a = sc.next();
                addBook(i,t,a);
                break;
            case 2:
                System.out.print("Enter ISBN to search book: ");
                searchBook(sc.nextInt());
                break;
            case 3:
                System.out.print("Enter ISBN to borrow book: ");
                borrowBook(sc.nextInt());
                break;
            case 4:
                viewLatestHistory();
                break;
            default:
                System.out.println("Invalid option.");
                
        }
    }
    
    
}
