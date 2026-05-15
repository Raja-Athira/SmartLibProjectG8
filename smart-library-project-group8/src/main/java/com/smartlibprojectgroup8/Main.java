package com.smartlibprojectgroup8;
import java.util.Scanner;

public class Main {
    final static String WELCOME_MESSAGE = "Welcome to The Smart Library Project!";
    final static String DASH = "-".repeat(WELCOME_MESSAGE.length() + 4);

    private static void mainMenu(){
        try (Scanner in = new Scanner(System.in)) {
            System.out.println(DASH);
            System.out.println(" Main Menu");
            System.out.println(DASH);
            
            String[] MAINMENU_OPTIONS = {
                "Add Book",
                "Search Book",
                "Borrow Book",
                "View History",
                "Exit",
            };
            
            for (int i = 1; i <= MAINMENU_OPTIONS.length; i++) {
                System.out.println(i + "  " + MAINMENU_OPTIONS[i-1]);
            }
            
            while (true) {
                System.out.print("Select Your Action (1-5): ");
                int Selection = in.nextInt();
                if (Selection < 1 || Selection > MAINMENU_OPTIONS.length) {
                    System.out.println("Invalid Option!");
                } else {
                    break;
                }
            }

            
        }
    };



    public static void main(String[] args) {
        System.out.println(DASH);
        System.out.println("| " + WELCOME_MESSAGE + " |");
        System.out.println(DASH);
        mainMenu();
    }

}