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
public abstract class TicTacToeGame {
    private int size;
    protected int winCondition;
    private String[][] board;
    protected int rowOfLatestMove;
    protected int colOfLatestMove;
    
    /**
     * Constructor
     * @param size, size of the board
     * @param winCondition, how many noughts/crosses in line are needed to win
     */
    public TicTacToeGame(int size, int winCondition) {
        this.size = size;
        this.winCondition = winCondition;
        this.board = new String[size][size];
    }
    
    private int convertCharToInt(char c) {
        return (int) c - 64;
    }
    
    private char convertIntToChar(int i) {
        return (char) (i + 64);
    }
    
    /**
     *
     * @return the horisontal line between rows as String for printing
     */
    private String printHorisontalLine() {
        String line = "\n---+";
        for (int i = 0; i < size; i++) {
            line += "---+";
        }
        return line + "\n";
    }
    
    /**
     *
     * @return the board as string for printing
     */
    public String visualBoard() {
        String printedBoard = "   |";
        
        for (int i = 1; i <= size; i++) {
            printedBoard += " " + convertIntToChar(i) + " |"; 
        }
        
        printedBoard += printHorisontalLine();
        
        for (int i = 0; i < size; i++) {
            printedBoard += " " + (i + 1) + ((i + 1) < 10 ? " |" : "|");
            
            for (int j = 0; j < size; j++) {
                printedBoard += " " 
                    + (board[i][j] == null ? " " : board[i][j]) + " |";
            }
            
            printedBoard += printHorisontalLine();
        }
        
        return printedBoard;
    }
    
    /**
     * Checks if the move is valid
     * @return true if the move is valid, otherwise false
     */
    private boolean isValidMove() {
        if (this.rowOfLatestMove >= size || this.colOfLatestMove >= size ||
           this.rowOfLatestMove < 0 || this.colOfLatestMove < 0) return false;
        else if (board[rowOfLatestMove][colOfLatestMove] != null) return false;
        else return true;
    }
    
    /**
     *Makes the move on the board if it's valid
     * @param move move of the player as string, for example 3A,
     * where 3 is row and A is column 1
     * @param turn X or 0 turn
     * @return true if the move was valid, otherwise false
     */
    public boolean makeMove(String move, String turn) {
        try {
            this.rowOfLatestMove 
                = Integer.parseInt(move.substring(0, move.length() - 1)) - 1;
            this.colOfLatestMove 
                = convertCharToInt(Character.toUpperCase(move.charAt(move.length() - 1))) - 1;
        } catch (NumberFormatException e) {
            return false;
        }
        
        if (!isValidMove()) return false;
        else {
            board[this.rowOfLatestMove][this.colOfLatestMove] = turn;
            return true;
        }
    }
    
    /**
     * Done for tests
     */
    public String[][] getBoard() {
        return board;
    }
    
    /**
     * Checks if one side has won
     * @return true if one side has won, otherwise false
     */
    public abstract boolean won();

    /**
     *
     * @return estimated value of the game in the position given
     */
    public abstract int evaluate();
}
