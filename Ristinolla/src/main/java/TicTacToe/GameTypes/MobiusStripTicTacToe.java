/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe.GameTypes;

/**
 * Still under developement, not tested and some bugs, can't be used yet.
 * @author marinella
 */
public class MobiusStripTicTacToe implements GameType {
    private int size;
    private int winCondition;
    
    /**
     *
     * @param size of the board
     * @param winCondition
     */
    public MobiusStripTicTacToe(int size, int winCondition) {
        this.size = size;
        this.winCondition = winCondition;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getWinCondition() {
        return this.winCondition;
    }
    
    private boolean out(int position) {
        return position < 0 || position >= size;
    }

    @Override
    public int[] goOverEdge(int row, int col) {
        int[] newRowAndCol = new int[2];
        
        if (out(row)) {
            newRowAndCol[0] = -1;
            return newRowAndCol;
        }
        
        if (out(col)) {
            if (col == -1) newRowAndCol[1] = size - 1;
            else if (col == size) newRowAndCol[1] = 0;
            
            newRowAndCol[0] = size - 1 - row;
        } else newRowAndCol[1] = col;
        
        return newRowAndCol;
    }
    
}
