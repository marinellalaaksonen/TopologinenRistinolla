/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe.Players;

import TicTacToe.TicTacToeGame.Position;
import TicTacToe.IO.IO;

/**
 * Handles interaction with human player
 * @author marinella
 */
public class HumanPlayer implements Player {
    private String mark;
    
    /**
     *
     * @param mark if the player is palying with X or 0
     */
    public HumanPlayer(String mark) {
        this.mark = mark;
    }

    /**
     * Asks the move from the player and returns it
     * @param io
     * @param game
     * @return next move of the player
     */
    @Override
    public String move(IO io, Position currentPosition) {
        io.print("Anna seuraava siirto (esimerikiksi 3A): ");
        return io.nextLine();
    }

    /**
     *
     * @return if player is playing wit X or 0
     */
    @Override
    public String getMark() {
        return this.mark;
    }   
}
