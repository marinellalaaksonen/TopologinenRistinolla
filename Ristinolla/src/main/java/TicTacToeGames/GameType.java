/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToeGames;

/**
 *
 * @author marinella
 */
public interface GameType {

    /**
     *
     * @param turn of X or 0
     * @return estimated value of the game in the position given
     */
    int evaluate(Position position, String turn);

    /**
     * Checks if one side has won, checks only around the latest move
     * @return true if the side that made the last move has won, otherwise false
     */
    boolean won(Position position);
    
}