package TicTacToeGames;

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
    
    public Position(String[][] board, int colOfLatestMove, int rowOfLatestMove, int movesLeft) {
        this.board = board;
        this.colOfLatestMove = colOfLatestMove;
        this.rowOfLatestMove = rowOfLatestMove;
        this.size = board.length;
        this.movesLeft = movesLeft;
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
    
    public boolean makeMove(int row, int col, String turn) {
        if (!isValidMove(row, col)) return false;
        else {
            this.rowOfLatestMove = row;
            this.colOfLatestMove = col;
            board[row][col] = turn;
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
            clonedBoard, position.getColOfLatestMove(),
            position.getRowOfLatestMove(), position.getMovesLeft()
        );
    }
    
    public Position[] getNextPositions(String turn) {
        Position[] nextPositions = new Position[movesLeft];
        int count = 0;
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isValidMove(i, j)) {
                    Position clonedPosition = clonePosition(this);
                    clonedPosition.makeMove(i, j, turn);
                    
                    nextPositions[count] = clonedPosition;
                    count++;
                }
            }
        }
        
        return nextPositions;
    }
    
    public String[][] getBoard() {
        return this.board;
    }
    
    public int getColOfLatestMove() {
        return colOfLatestMove;
    }

    public int getRowOfLatestMove() {
        return rowOfLatestMove;
    }
     
    public int getMovesLeft() {
        return movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }
}
