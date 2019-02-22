/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTypes;


public class CylinderTicTacToe implements GameType {
    private int size;
    private int winCondition;
    
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
