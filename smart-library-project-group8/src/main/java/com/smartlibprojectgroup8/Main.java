package com.smartlibprojectgroup8;
import java.util.Scanner;

class Menu {
    private final static String WELCOME_MESSAGE = "Welcome to The Smart Library Project!";
    private final static String DASH = "-".repeat(WELCOME_MESSAGE.length() + 4);
    private final static String FILENAME = "catalogue.txt";

    LibrarySystem MainLibrarySystem = new LibrarySystem();
    private Scanner in;

    private static void printTitle(String title) {
        System.out.println("");
        System.out.println(DASH);
        System.out.println(" " + title);
        System.out.println(DASH);
    }

    private static int promptAndChoose(String[] Options, Scanner in) {
        for (int i = 1; i <= Options.length; i++) {
            System.out.println(i + "  " + Options[i-1]);
        }

        System.out.println("");
        while (true) {
            System.out.print("Select Your Action (1-" + Options.length + "): ");
            int Selection = in.nextInt();
            if (Selection < 1 || Selection > Options.length) {
                System.out.println("Invalid Option!");
            } else {
                return Selection;
            }
        }
    }

    private void promptToMainMenu() {
        System.out.println("Press Enter to return to Main Menu");
        in.nextLine();
        in.nextLine();
    }

    private void promptInvalidOption() {
        System.out.println("\nUnknown Option; Returning to Main Menu");
        promptToMainMenu();
    }

    private final static String[] MainMenuOptions = {
        "Add Book",
        "View Book Catalogue",
        "Search Book",
        "Borrow Book",
        "Return Book",
        "View Borrow History",
        "Exit",
    };

    private final static String[] YesNoOptions = {
        "Yes",
        "No",
    };

    private void mainMenu(){
        boolean exit = false;
        while (!exit) { 
            printTitle("Main Menu");
            int Selection = promptAndChoose(MainMenuOptions, in);
            switch(Selection) {
                case 1 -> addBookMenu();
                case 2 -> bookCatalogueMenu();
                case 3 -> searchBookMenu();
                case 4 -> borrowBookMenu();
                case 5 -> returnBookMenu();
                case 6 -> {
                    printTitle("Borrow History");
                    MainLibrarySystem.viewLatestHistory();
                    System.out.println("");
                    promptToMainMenu();
                }
                case 7 -> {
                    System.out.println("Are you sure you want to exit?");
                    if (promptAndChoose(YesNoOptions, in) == 1) exit = true;
                }
                default -> promptInvalidOption();
            }
        }
    };

    private final static String[] AddBookOptions = {
        "Confirm",
        "Change Book Details",
        "Return to Main Menu"
    };

    private void addBookMenu(){
        boolean returnToMenu = false;
        while (!returnToMenu) { 
            printTitle("Add Book");

            System.out.print("Enter Book ISBN: ");
            int isbn = in.nextInt();
            in.nextLine();

            System.out.print("Enter Book Title: ");
            String title = in.nextLine();

            System.out.print("Enter Book Author: ");
            String author = in.nextLine();

            System.out.println("");
            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("ISBN: " + isbn);

            System.out.println("");
            System.out.println("Confirm Adding Book?");
            int Selection = promptAndChoose(AddBookOptions, in);
            switch(Selection) {
                case 1 -> {
                    MainLibrarySystem.addBook(isbn, title, author);
                    System.out.println("\nSuccessfully added book!");
                    promptToMainMenu();
                    returnToMenu = true;
                }
                case 2 -> {}
                case 3 -> returnToMenu = true;
                default -> {
                    promptInvalidOption();
                    returnToMenu = true;
                }
            }

        }
    };

    private final static String[] BookCatalogueOptions = {
        "Borrow a Book",
        "Return to Main Menu"
    };

    private void bookCatalogueMenu(){
        printTitle("Book Catalogue");
        MainLibrarySystem.showBookCatalogue();
        System.out.println("");
        int Selection = promptAndChoose(BookCatalogueOptions, in);
        switch(Selection) {
            case 1 -> {
                borrowBookMenu();
            }
            case 2 -> {}
            default -> {
                promptInvalidOption();
                promptToMainMenu();
            }
        }
    };

    private final static String[] FoundBookOptions = {
        "Borrow This Book",
        "Return to Main Menu"
    };

    private final static String[] NotFoundBookOptions = {
        "Retry",
        "Return to Main Menu"
    };

    private void searchBookMenu() {
        boolean returnToMenu = false;
        while (!returnToMenu) { 
            printTitle("Search Book");

            System.out.print("Enter Book ISBN: ");
            int isbn = in.nextInt();
            in.nextLine();

            System.out.println("");

            Book foundBook = MainLibrarySystem.searchBook(isbn);
            if (foundBook == null) {
                System.out.println("Book not found!\n");
                int Selection = promptAndChoose(NotFoundBookOptions, in);
                switch(Selection) {
                    case 1 -> {}
                    case 2 -> {
                        returnToMenu = true;
                    }
                    default -> promptInvalidOption();
                }
            } else {
                System.out.println(foundBook);
                int Selection = promptAndChoose(FoundBookOptions, in);
                System.out.println("");
                switch(Selection) {
                    case 1 -> returnToMenu = borrowBookMenu2(foundBook);
                    case 2 -> {
                        returnToMenu = true;
                    }
                    default -> {
                        promptInvalidOption();
                        returnToMenu = true;
                    }
                }
            }
        }
    }

    private final static String[] BorrowBookOptions = {
        "Confirm",
        "Borrow Another Book",
        "Return to Main Menu"
    };

    private final static String[] BorrowBookOptions2 = {
        "Retry",
        "Return to Main Menu"
    };

    private void borrowBookMenu(){
        boolean returnToMenu = false;
        while (!returnToMenu) { 
            printTitle("Borrow Book");

            System.out.print("Enter Book ISBN: ");
            int isbn = in.nextInt();

            System.out.println("");

            Book toBorrow = MainLibrarySystem.searchBook(isbn);
            if (toBorrow == null) {
                System.out.println("Book not found!\n");
                int Selection = promptAndChoose(BorrowBookOptions2, in);
                switch(Selection) {
                    case 1 -> {}
                    case 2 -> {
                        returnToMenu = true;
                    }
                    default -> {
                        promptInvalidOption();
                        returnToMenu = true;
                    }
                }
            } else {
                returnToMenu = borrowBookMenu2(toBorrow);
            }
        }
    }

    private boolean borrowBookMenu2(Book toBorrow) {
        boolean returnToMenu = false;
        if (toBorrow == null) return returnToMenu;
        Book inBorrowHistory = MainLibrarySystem.searchBookInBorrowHistory(toBorrow.getIsbn());
        if (inBorrowHistory != null && inBorrowHistory.getIsbn() == toBorrow.getIsbn()) { // Book is already being borrowed
            System.out.println("Book is already in Borrowing History!\n");
            int Selection = promptAndChoose(BorrowBookOptions2, in);
            switch(Selection) {
                case 1 -> borrowBookMenu();
                case 2 -> {
                    returnToMenu = true;
                }
                default -> {
                    promptInvalidOption();
                    returnToMenu = true;
                }
            }
        } else {
            System.out.println(toBorrow + "\nConfirm to borrow this book?");
            int Selection = promptAndChoose(BorrowBookOptions, in);
            switch(Selection) {
                case 1 -> {
                        MainLibrarySystem.borrowBook(toBorrow);
                        System.out.println("Book successfully borrowed!\n");
                        promptToMainMenu();
                        returnToMenu = true;
                    }
                case 2 -> {}
                case 3 -> {
                    returnToMenu = true;
                }
                default -> {
                    promptInvalidOption();
                    returnToMenu = true;
                }
            }
        }

        return returnToMenu;
    }
    
    private final static String[] ReturnBookOptions = {
        "Return Book",
        "Return to Main Menu"
    };

    private final static String[] ReturnBookOptions2 = {
        "Return Another Book",
        "Return to Main Menu"
    };

    private void returnBookMenu(){
        boolean returnToMenu = false;
        while (!returnToMenu) { 
            printTitle("Return Book");
            Book returningBook = MainLibrarySystem.getBookToReturn();
            if (returningBook != null) {
                System.out.println("Book to Return:\n" + returningBook);
                System.out.println("");
                int Selection = promptAndChoose(ReturnBookOptions, in);
                switch(Selection) {
                    case 1 -> {
                        MainLibrarySystem.returnBook();
                        System.out.println("\nBook Successfully Returned!\n");
                        returnToMenu = promptAndChoose(ReturnBookOptions2, in) == 2;
                    }
                    case 2 -> {
                        returnToMenu = true;
                    }
                    default -> {
                        promptInvalidOption();
                        returnToMenu = true;
                    }
                }
                
            } else {
                System.out.println("You have no books borrowed.\n");
                promptToMainMenu();
                returnToMenu = true;
            }
        }
    }

    public void start(Scanner in){
        this.in = in;
        MainLibrarySystem.loadCatalogueFromFile(FILENAME);
        System.out.println(DASH);
        System.out.println("| " + WELCOME_MESSAGE + " |");
        System.out.println(DASH);
        mainMenu();
    }
}

public class Main {
    public static void main(String[] args) {
        Menu newMenu = new Menu();
        newMenu.start(new Scanner(System.in));
    }
}