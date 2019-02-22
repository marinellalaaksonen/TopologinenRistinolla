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
    
    private int howManyPossibleInRow(Position position, int currentRowPos, int currentColPos, 
            int rowDiff, int colDiff, String turn) {
        int nextRowPosition = currentRowPos + rowDiff;
        int nextColPosition = currentColPos + colDiff;
        
        if (nextRowPosition < 0 || nextRowPosition >= size
                || nextColPosition < 0 || nextColPosition >= size) {
            return 0;
        } else if (position.getBoard()[nextRowPosition][nextColPosition] != null &&
                !position.getBoard()[nextRowPosition][nextColPosition].equals(turn)) {
            return 0;
        } else {
            return 1 + howManyPossibleInRow(
                position, nextRowPosition, nextColPosition, rowDiff, colDiff, turn
            );
        }
    }
    
    private boolean areEqual(String boardPos, String mark) {
        if (boardPos == null) return false;
        else if (!boardPos.equals(mark)) return false;
        else return true;
    }
    
    private int countMarksInLine(Position position, int firstRowPos, int firstColPos, 
            int rowDiff, int colDiff, String turn, int length) {
        int marks = 0;
        String[][] board = position.getBoard();
        
        if (rowDiff == 0) {
            for (int i = firstColPos; i < length && i > 0; i += colDiff) {
                if (areEqual(board[firstRowPos][i], turn)) marks++;
            }
        } else if (colDiff == 0) {
            for (int i = firstRowPos; i < length && i > 0; i += rowDiff) {
                if (areEqual(board[i][firstColPos], turn)) marks++;
            }
        } else {
            for (int i = firstRowPos; i < length && i > 0; i += rowDiff) {
                for (int j = firstColPos; j < length && j > 0; j += colDiff) {
                    if (areEqual(board[i][j], turn)) marks++;
                }
            }
        }
        
        return marks;
    }
    
    private int checkRow(Position position, int currentRowPos, int currentColPos, 
            String turn) {
        int rowLeft = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            -1, 0, turn);
        int rowRight = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            1, 0, turn);
        
        if (rowLeft + 1 + rowRight < winCondition) return 0;
        else {
            return countMarksInLine(position, currentRowPos, currentColPos - rowLeft, 
                0, 1, turn, rowLeft + 1 + rowRight);
        }
    }
    
    private int checkCol(Position position, int currentRowPos, int currentColPos, 
            String turn) {
        int colDown = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            0, -1, turn);
        int colUp = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            0, 1, turn);
        
        if (colDown + 1 + colUp < winCondition) return 0;
        else {
            return countMarksInLine(position, currentRowPos - colUp, currentColPos, 
                1, 0, turn, colDown + 1 + colUp);
        }
    }
    
    private int checkDiagonalUp(Position position, int currentRowPos, int currentColPos, 
            String turn) {
        int bottomLeft = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            1, -1, turn);
        int topRight = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            -1, 1, turn);
        
        int lengthOfPossibleRow = bottomLeft + 1 + topRight;
        
        if (lengthOfPossibleRow < winCondition) return 0;
        else {
            return countMarksInLine(position, currentRowPos + bottomLeft, 
                currentColPos - bottomLeft, -1, 1, turn, lengthOfPossibleRow);
        }
    }
    
    private int checkDiagonalDown(Position position, int currentRowPos, int currentColPos, 
            String turn) {
        int topLeft = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            -1, -1, turn);
        int bottomRight = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            1, 1, turn);
        
        int lengthOfPossibleRow = topLeft + 1 + bottomRight;
        
        if (lengthOfPossibleRow < winCondition) return 0;
        else {
            return countMarksInLine(position, currentRowPos - topLeft, 
                currentColPos - topLeft, 1, 1, turn, lengthOfPossibleRow);
        }
    }
    
    /**
     *
     * @param turn of X or 0
     * @return estimated value of the game in the position given 
     * or value of the game if finished
     */
    @Override
    public int evaluate(Position position, String turn, int depthLeft) {
        if (won(position)) {
            return (int) (turn.equals("X") ? (1E9 + depthLeft) : (-1E9 - depthLeft));
        } else if (position.getMovesLeft() == 0) {
            return 0;
        }
        
        String[][] board = position.getBoard();
        int valueOfGame = 0;
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) continue;
                else {
                    //multiply with 1 if counting X (max), else with -1 (0 is trying to minimize)
                    int multiplyWith = board[i][j].equals("X") ? 1 : -1; 
                    
                    valueOfGame += multiplyWith * checkRow(position, i, j, turn);
                    valueOfGame += multiplyWith * checkCol(position, i, j, turn);
                    valueOfGame += multiplyWith * checkDiagonalDown(position, i, j, turn);
                    valueOfGame += multiplyWith * checkDiagonalUp(position, i, j, turn);
                }
            }
        }
        return valueOfGame;
    }
}
