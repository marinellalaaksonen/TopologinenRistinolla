/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe.TicTacToeGame;

import TicTacToe.GameTypes.GameType;

/**
 * Helps to determine the value of the game if it's finished or the latest move made
 * @author marinella
 */
public class Evaluator {
    private final GameType gameType;
    private final int winCondition;
    
    /**
     *
     * @param gameType the gametype that helps the evaluator to determine how going over
     * edges should be handled
     */
    public Evaluator(GameType gameType) {
        this.gameType = gameType;
        this.winCondition = gameType.getWinCondition();
    }
    
    private boolean isOverEdge(int row, int col) {
        int size = gameType.getSize();
        return row < 0 || row >= size || col < 0 || col >= size;
    }
    
    private boolean areEqual(String boardPos, String mark) {
        if (boardPos == null) return false;
        else if (!boardPos.equals(mark)) return false;
        else return true;
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
            int rowDiff, int colDiff, int maxLength) {
        int nextRowPosition = currentRowPos + rowDiff;
        int nextColPosition = currentColPos + colDiff;
        
        if (maxLength == 0) return 0;
        
        if (isOverEdge(nextRowPosition, nextColPosition)) {
            int[] nextRowAndCol = gameType.goOverEdge(nextRowPosition, nextColPosition);
            
            if (nextRowAndCol[0] == -1 || nextRowAndCol[1] == -1) return 0;
            else {
                nextRowPosition = nextRowAndCol[0];
                nextColPosition = nextRowAndCol[1];
            }
        } 
        
        if (!areEqual(position.getBoard()[currentRowPos][currentColPos], 
                position.getBoard()[nextRowPosition][nextColPosition])) {
            return 0;
        } else {
            return 1 + checkHowManyInRow(
                position, nextRowPosition, nextColPosition, rowDiff, colDiff, maxLength - 1
            );
        }
    } 
    
    private int howManyStraightInRow(Position position, int row, int col) {
        return checkHowManyInRow(position, row, col, -1, 0, winCondition)
            + checkHowManyInRow(position, row, col, 1, 0, winCondition) + 1;
    }
    
    private int howManyStraightInCol(Position position, int row, int col) {
        return checkHowManyInRow(position, row, col, 0, -1, winCondition)
            + checkHowManyInRow(position, row, col, 0, 1, winCondition) + 1;
    }
    
    private int howManyStraightDiagonalDown(Position position, int row, int col) {
        return checkHowManyInRow(position, row, col, -1, -1, winCondition)
            + checkHowManyInRow(position, row, col, 1, 1, winCondition) + 1;
    }
    
    private int howManyStraightDiagonalUp(Position position, int row, int col) {
        return checkHowManyInRow(position, row, col, 1, -1, winCondition)
            + checkHowManyInRow(position, row, col, -1, 1, winCondition) + 1;
    }
    
    /**
     * Checks if one side has won, checks only around the latest move
     * @param position
     * @return true if the side that made the last move has won, otherwise false
     */
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
    
    private boolean areEqualOrNull(String boardPos, String mark) {
        if (boardPos == null) return true;
        else if (!boardPos.equals(mark)) return false;
        else return true;
    }
    
    /**
     * Checks recirsively how many X/0 would be possible to fit in a row to that direction
     * @param currentRowPos the current row
     * @param currentColPos the current col
     * @param rowDiff which direction to move on between rows (1 moves right, 
     * -1 left, 0 stays in place)
     * @param colDiff which direction to move on between columnss (1 moves down, 
     * -1 up, 0 stays in place)
     * @return how many X/0 would be possible to fit in a line to that direction
     */
    private int howManyPossibleInRow(Position position, int currentRowPos, int currentColPos, 
            int rowDiff, int colDiff, String turn, int maxLength) {
        int nextRowPosition = currentRowPos + rowDiff;
        int nextColPosition = currentColPos + colDiff;
        
        if (maxLength == 0) return 0;
        
        if (isOverEdge(nextRowPosition, nextColPosition)) {
            int[] nextRowAndCol = gameType.goOverEdge(nextRowPosition, nextColPosition);
            
            if (nextRowAndCol[0] == -1 || nextRowAndCol[1] == -1) return 0;
            else {
                nextRowPosition = nextRowAndCol[0];
                nextColPosition = nextRowAndCol[1];
            }
        }
        
        if (!areEqualOrNull(position.getBoard()[nextRowPosition][nextColPosition], turn)) {
            return 0;
        } else {
            return 1 + howManyPossibleInRow(
                position, nextRowPosition, nextColPosition, rowDiff, colDiff, turn, maxLength - 1
            );
        }
    }
    
//    private int countMarksInLine(Position position, int firstRowPos, int firstColPos, 
//            int rowDiff, int colDiff, String turn, int length) {
//        int marks = 0;
//        String[][] board = position.getBoard();
//        
//        if (rowDiff == 0) {
//            for (int i = firstColPos; i < length && i > 0; i += colDiff) {
//                if (areEqual(board[firstRowPos][i], turn)) marks++;
//            }
//        } else if (colDiff == 0) {
//            for (int i = firstRowPos; i < length && i > 0; i += rowDiff) {
//                if (areEqual(board[i][firstColPos], turn)) marks++;
//            }
//        } else {
//            for (int i = firstRowPos; i < length && i > 0; i += rowDiff) {
//                for (int j = firstColPos; j < length && j > 0; j += colDiff) {
//                    if (areEqual(board[i][j], turn)) marks++;
//                }
//            }
//        }
//        
//        return marks;
//    }
    
    /**
     * Checks if a winning line fits on a row, returns 0 if it doesn't and the 
     * length of the current line if it does.
     * @return 0 if the available place is too short, otherwise the length of the 
     * current line
     */
    private int checkRow(Position position, int currentRowPos, int currentColPos, 
            String turn, int size) {
        int rowLeft = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            -1, 0, turn, size);
        int rowRight = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            1, 0, turn, size);
        int lengthOfPossibleRow = Math.min(rowLeft + 1 + rowRight, size);
        
        if (lengthOfPossibleRow < winCondition) return 0;
        else {
            return howManyStraightInRow(position, currentRowPos, currentColPos);
        }
    }
    
    /**
     * Checks if a winning line fits on a column, returns 0 if it doesn't and the 
     * length of the current line if it does.
     * @return 0 if the available place is too short, otherwise the length of the 
     * current line
     */
    private int checkCol(Position position, int currentRowPos, int currentColPos, 
            String turn, int size) {
        int colDown = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            0, -1, turn, size);
        int colUp = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            0, 1, turn, size);
        int lengthOfPossibleRow = Math.min(colDown + 1 + colUp, size);
        
        if (lengthOfPossibleRow < winCondition) return 0;
        else {
            return howManyStraightInCol(position, currentRowPos, currentColPos);
        }
    }
    
    /**
     * Checks if a winning line fits diagonally up, returns 0 if it doesn't and the 
     * length of the current line if it does.
     * @return 0 if the available place is too short, otherwise the length of the 
     * current line
     */
    private int checkDiagonalUp(Position position, int currentRowPos, int currentColPos, 
            String turn, int size) {
        int bottomLeft = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            1, -1, turn, size);
        int topRight = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            -1, 1, turn, size);
        int lengthOfPossibleRow = Math.min(bottomLeft + 1 + topRight, size);
        
        if (lengthOfPossibleRow < winCondition) return 0;
        else {
            return howManyStraightDiagonalUp(position, currentRowPos, currentColPos);
        }
    }
    
    /**
     * Checks if a winning line fits diagonally down, returns 0 if it doesn't and the 
     * length of the current line if it does.
     * @return 0 if the available place is too short, otherwise the length of the 
     * current line
     */
    private int checkDiagonalDown(Position position, int currentRowPos, int currentColPos, 
            String turn, int size) {
        int topLeft = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            -1, -1, turn, size);
        int bottomRight = howManyPossibleInRow(position, currentRowPos, currentColPos, 
            1, 1, turn, size);
        int lengthOfPossibleRow = Math.min(topLeft + 1 + bottomRight, size);
        
        if (lengthOfPossibleRow < winCondition) return 0;
        else {
            return howManyStraightDiagonalDown(position, currentRowPos, currentColPos);
        }
    }
    
    /**
     * Evaluates the value of the game if the game is finished, otherwise the value 
     * of the latest move
     * @param position the current game position
     * @param turn of X or 0
     * @param depthLeft on minmax, helps to prefer early wins
     * @return the value of the latest move or the value of the game if finished
     */
    public int evaluate(Position position, String turn, int depthLeft) {
        if (won(position)) {
            return (int) (turn.equals("X") ? (1E9 + depthLeft) : (-1E9 - depthLeft));
        } else if (position.getMovesLeft() == 0) {
            return 0;
        }
        
        String[][] board = position.getBoard();
        int valueOfGame = 0;
        int size = this.gameType.getSize();
        
        int row = position.getRowOfLatestMove();
        int col = position.getColOfLatestMove();
        
        int multiplyWith = board[row][col].equals("X") ? 1 : -1; 
                    
        valueOfGame += multiplyWith * checkRow(position, row, col, turn, size);
        valueOfGame += multiplyWith * checkCol(position, row, col, turn, size);
        valueOfGame += multiplyWith * checkDiagonalDown(position, row, col, turn, size);
        valueOfGame += multiplyWith * checkDiagonalUp(position, row, col, turn, size);
        
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                if (board[i][j] == null) continue;
//                else {
//                    //multiply with 1 if counting X (max), else with -1 (0 is trying to minimize)
//                    int multiplyWith = board[i][j].equals("X") ? 1 : -1; 
//                    
//                    valueOfGame += multiplyWith * checkRow(position, i, j, turn, size);
//                    valueOfGame += multiplyWith * checkCol(position, i, j, turn, size);
//                    valueOfGame += multiplyWith * checkDiagonalDown(position, i, j, turn, size);
//                    valueOfGame += multiplyWith * checkDiagonalUp(position, i, j, turn, size);
//                }
//            }
//        }
        return valueOfGame;
    }
}
