package TicTacToe.GameTypes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author marinella
 */
public interface GameType {
    
    int getSize();
    int getWinCondition();

    /**
     * Handles going over the edge of the board in different gametypes.
     * @param row of tried move
     * @param col of tried move
     * @return an array where the first number is row of next move and second the 
     * column. Returns -1 as either row or col if the move is incorrect
     */
    int[] goOverEdge(int row, int col);
}
