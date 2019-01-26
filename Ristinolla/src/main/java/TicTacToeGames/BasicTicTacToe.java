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
public class BasicTicTacToe extends TicTacToeGame {
    
    /**
     *
     * @param size
     * @param winCondition
     */
    public BasicTicTacToe(int size, int winCondition) {
        super(size, winCondition);
    }
    
    /**
     * Checks recirsively how many X/0 there are in a straight line
     * @param currentRowPos the current row
     * @param currentColPos the current col
     * @param rowDiff which direction to move on between rows (1 moves right, 
     * -1 left, 0 stays in place)
     * @param colDiff which direction to move on between columnss (1 moves down, 
     * -1 up, 0 stays in place)
     * @return how many X/0 there are in line to that direction
     */
    private int checkWinRow(int currentRowPos, int currentColPos, 
            int rowDiff, int colDiff) {
        int nextRowPosition = currentRowPos + rowDiff;
        int nextColPosition = currentColPos + colDiff;
        
        if (nextRowPosition < 0 || nextRowPosition >= super.size
                || nextColPosition < 0 || nextColPosition >= super.size) {
            return 0;
        } else if (super.board[nextRowPosition][nextColPosition] == null ||
                !super.board[nextRowPosition][nextColPosition]
                .equals(super.board[currentRowPos][currentColPos])) {
            return 0;
        } else {
            return 1 + checkWinRow(
                nextRowPosition, nextColPosition, rowDiff, colDiff
            );
        }
    } 
    
    /**
     * Checks if one side has won
     * @return true if one side has won, otherwise false
     */
    @Override
    public boolean won() {
        int howManyStraightInRow = (
            checkWinRow(super.rowOfLatestMove, super.colOfLatestMove, -1, 0)
            + checkWinRow(super.rowOfLatestMove, super.colOfLatestMove, 1, 0)
            + 1
            );
        if (howManyStraightInRow >= super.winCondition) return true;
        
        int howManyStraightInCol = (
            checkWinRow(super.rowOfLatestMove, super.colOfLatestMove, 0, -1)
            + checkWinRow(super.rowOfLatestMove, super.colOfLatestMove, 0, 1)
            + 1
            );
        if (howManyStraightInCol >= super.winCondition) return true;
        
        int howManyStraightDiagonalDown = (
            checkWinRow(super.rowOfLatestMove, super.colOfLatestMove, -1, -1)
            + checkWinRow(super.rowOfLatestMove, super.colOfLatestMove, 1, 1)
            + 1
            );
        if (howManyStraightDiagonalDown >= super.winCondition) return true;
        
        int howManyStraightDiagonalUp = (
            checkWinRow(super.rowOfLatestMove, super.colOfLatestMove, 1, -1)
            + checkWinRow(super.rowOfLatestMove, super.colOfLatestMove, -1, 1)
            + 1
            );
        if (howManyStraightDiagonalUp >= super.winCondition) return true;
        
        return false;
    }
    
    /**
     *
     * @return estimated value of the game in the position given
     */
    @Override
    public int evaluate() {
        return 0;
    }
}
