/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe.GameTypes;

/**
 * Methods of cylinder tictactoe to aid evaluator in gametype spesific moves. Can
 * go over the vertical edges, but not horizontal.
 * @author marinella
 */
public class CylinderTicTacToe implements GameType {
    private int size;
    private int winCondition;
    
    /**
     *
     * @param size of the board
     * @param winCondition
     */
    public CylinderTicTacToe(int size, int winCondition) {
        this.size = size;
        this.winCondition = winCondition;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getWinCondition() {
        return winCondition;
    }
    
    private boolean out(int position) {
        return position < 0 || position >= size;
    }

    /**
     * Handles going over edge. This method is only called if row or col have gone 
     * over the edge of the board. If the row has gone over the edge the move is 
     * incorrect, if only column is over edge the method returns the given row and 
     * the column on the  opposite side of the board from where the play went over edge.
     * @param row of tried move
     * @param col of tried move
     * @return first number is next row, second next column. Returns {-1, 0} if 
     * row is over edge, otherwise returns the given row and the column on the 
     * opposite side of the board from where the play went over edge.
     */
    @Override
    public int[] goOverEdge(int row, int col) {
        int[] newRowAndCol = new int[2];
        
        if (out(row)) {
            newRowAndCol[0] = -1;
            return newRowAndCol;
        } else {
            newRowAndCol[0] = row;
        }
        
        if (out(col)) {
            if (col == -1) newRowAndCol[1] = size - 1;
            else if (col == size) newRowAndCol[1] = 0;
        } else newRowAndCol[1] = col;
        
        return newRowAndCol;
    }
    
}
