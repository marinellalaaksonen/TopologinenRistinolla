
import IO.ConsoleIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marinella
 */
public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(new ConsoleIO());
        game.start();
    }
}
