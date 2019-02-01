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
public class BasicTicTacToe implements GameType {
    private int size;
    private int winCondition;
    
    /**
     *
     * @param size
     * @param winCondition
     */
    public BasicTicTacToe(int size, int winCondition) {
        this.size = size;
        this.winCondition = winCondition;
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
    private int checkHowManyInRow(Position position, int currentRowPos, int currentColPos, 
            int rowDiff, int colDiff) {
        int nextRowPosition = currentRowPos + rowDiff;
        int nextColPosition = currentColPos + colDiff;
        
        if (nextRowPosition < 0 || nextRowPosition >= size
                || nextColPosition < 0 || nextColPosition >= size) {
            return 0;
        } else if (position.getBoard()[nextRowPosition][nextColPosition] == null ||
                !position.getBoard()[nextRowPosition][nextColPosition]
                .equals(position.getBoard()[currentRowPos][currentColPos])) {
            return 0;
        } else {
            return 1 + checkHowManyInRow(
                position, nextRowPosition, nextColPosition, rowDiff, colDiff
            );
        }
    } 
    
    private int howManyStraightInRow(Position position, int row, int col) {
        return checkHowManyInRow(position, row, col, -1, 0)
            + checkHowManyInRow(position, row, col, 1, 0) + 1;
    }
    
    private int howManyStraightInCol(Position position, int row, int col) {
        return checkHowManyInRow(position, row, col, 0, -1)
            + checkHowManyInRow(position, row, col, 0, 1) + 1;
    }
    
    private int howManyStraightDiagonalDown(Position position, int row, int col) {
        return checkHowManyInRow(position, row, col, -1, -1)
            + checkHowManyInRow(position, row, col, 1, 1) + 1;
    }
    
    private int howManyStraightDiagonalUp(Position position, int row, int col) {
        return checkHowManyInRow(position, row, col, 1, -1)
            + checkHowManyInRow(position, row, col, -1, 1) + 1;
    }
    
    /**
     * Checks if one side has won, checks only around the latest move
     * @return true if the side that made the last move has won, otherwise false
     */
    @Override
    public boolean won(Position position) {
        int rowOfLatestMove = position.getRowOfLatestMove();
        int colOfLatestMove = position.getColOfLatestMove();
        
        return howManyStraightInRow(position, rowOfLatestMove, colOfLatestMove) 
            >= winCondition ||
            howManyStraightInCol(position, rowOfLatestMove, colOfLatestMove) 
            >= winCondition ||
            howManyStraightDiagonalDown(position, rowOfLatestMove, colOfLatestMove) 
            >= winCondition ||
            howManyStraightDiagonalUp(position, rowOfLatestMove, colOfLatestMove) 
            >= winCondition;
    }
    
    /**
     *
     * @param turn of X or 0
     * @return estimated value of the game in the position given
     */
    @Override
    public int evaluate(Position position, String turn) {
        if (won(position)) {
            return turn.equals("X") ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        
        String[][] board = position.getBoard();
        int valueOfGame = 0;

        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) continue;
                else {
                    //multiply with 1 if counting X (max), else with -1 (0 is trying to minimize)
                    int multiplyWith = board[i][j].equals("X") ? 1 : -1; 
                    
                    valueOfGame += multiplyWith * howManyStraightInRow(position, i, j);
                    valueOfGame += multiplyWith * howManyStraightInCol(position, i, j);
                    valueOfGame += multiplyWith * howManyStraightDiagonalDown(position, i, j);
                    valueOfGame += multiplyWith * howManyStraightDiagonalDown(position, i, j);
                }
            }
        }
        return valueOfGame;
    }
}
