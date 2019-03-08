/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe.Players;

import TicTacToe.TicTacToeGame.Position;
import TicTacToe.IO.*;

/**
 *
 * @author marinella
 */
public interface Player {

    /**
     * Decides the next move
     * @param io
     * @param currentPosition
     * @return the next move of the player
     */
    String move(IO io, Position currentPosition);

    /**
     * @return if player is playing with X or 0
     */
    String getMark();
}
