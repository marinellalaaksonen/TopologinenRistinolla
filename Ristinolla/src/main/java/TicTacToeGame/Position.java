package TicTacToeGame;

import IO.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marinella
 */
public class Position {
    private final String[][] board;
    private int colOfLatestMove;
    private int rowOfLatestMove;
    private final int size;
    private int movesLeft;
    private int amountOfChildren;
    
    /**
     * 
     * @param board
     * @param colOfLatestMove
     * @param rowOfLatestMove
     * @param movesLeft
     */
    public Position(String[][] board, int rowOfLatestMove, int colOfLatestMove, int movesLeft) {
        this.board = board;
        this.colOfLatestMove = colOfLatestMove;
        this.rowOfLatestMove = rowOfLatestMove;
        this.size = board.length;
        this.movesLeft = movesLeft;
        this.amountOfChildren = 50;
    }
    
    /**
     * Checks if the move is valid
     * @return true if the move is valid, otherwise false
     */
    private boolean isValidMove(int row, int col) {
        if (row >= size || col >= size || row < 0 || col < 0) return false;
        else if (board[row][col] != null) return false;
        else return true;
    }
    
    /**
     * makes the move on the board if the move is valid
     * @param row
     * @param col
     * @param turn
     * @return true if sucsessfull
     */
    public boolean makeMove(int row, int col, String turn) {
        if (!isValidMove(row, col)) return false;
        else {
            this.rowOfLatestMove = row;
            this.colOfLatestMove = col;
            this.board[row][col] = turn;
            movesLeft--;
            return true;
        }
    }
    
    private Position clonePosition(Position position) {
        String[][] clonedBoard = new String[size][size];
        String[][] oldBoard = position.getBoard();
                
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                clonedBoard[i][j] = oldBoard[i][j];
            }
        }
        
        return new Position(
            clonedBoard, position.getRowOfLatestMove(),
            position.getColOfLatestMove(), position.getMovesLeft()
        );
    }
    
    /**
     * Generates and returns all valid next positions
     * @param turn X or 0
     * @return all possible next positions
     */
    public Position[] getNextPositions(String turn) {
        Position[] nextPositions = new Position[amountOfChildren];
        int count = 0;
        int distance = 1;
        
        while (count < amountOfChildren) {
            
            for (int i = Math.max(0, rowOfLatestMove - distance); i < Math.min(size, rowOfLatestMove + distance); i++) {
                for (int j = Math.max(0, colOfLatestMove - distance); j < Math.min(size, colOfLatestMove + distance); j++) {
                    if (isValidMove(i, j)) {
                        Position clonedPosition = clonePosition(this);
                        clonedPosition.makeMove(i, j, turn);

                        nextPositions[count] = clonedPosition;
                        count++;
                        
                        if (count >= amountOfChildren) return nextPositions;
                    }
                }
            }
            
            distance++;
        }
        
        
        
        return nextPositions;
    }
    
    /**
     *
     * @return
     */
    public String[][] getBoard() {
        return this.board;
    }
    
    /**
     *
     * @return
     */
    public int getColOfLatestMove() {
        return colOfLatestMove;
    }

    /**
     *
     * @return
     */
    public int getRowOfLatestMove() {
        return rowOfLatestMove;
    }
     
    /**
     *
     * @return
     */
    public int getMovesLeft() {
        return movesLeft;
    }

    /**
     *
     * @param movesLeft
     */
    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }
    
    public int getAmountOfChildren() {
        return amountOfChildren;
    }
}
