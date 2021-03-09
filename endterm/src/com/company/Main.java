package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String n = "y";
        do {
            Interface.menu();
            System.out.println("Do you like to do more operations (n to quit) (any symbol to continue)");
            n = scan.nextLine();
        } while (!n.equals("n"));
    }
}
