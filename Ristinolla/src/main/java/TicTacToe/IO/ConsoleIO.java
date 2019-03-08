/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe.IO;

import java.util.Scanner;

/**
 * Handles the interaction with the players through console using Scanner
 * @author marinella
 */
public class ConsoleIO implements IO {
    private Scanner scanner;

    /**
     * Creates a new scanner
     */
    public ConsoleIO() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the next line from the console
     * @return
     */
    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Prints the given string as a line
     * @param s string to be printed
     */
    @Override
    public void print(String s) {
        System.out.println(s);
    }
    
}
