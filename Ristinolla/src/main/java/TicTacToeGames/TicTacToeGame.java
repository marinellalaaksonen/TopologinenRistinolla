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
public class TicTacToeGame {
    private final int size;
    private Position position;
    private final GameType type;
    
    /**
     * Constructor
     * @param size, size of the board
     * @param winCondition, how many noughts/crosses in line are needed to win
     * @param type basic or other
     */
    public TicTacToeGame(int size, GameType type) {
        this.size = size;
        this.position = new Position(new String[size][size], -1, -1, size * size);
        this.type = type;
    }
    
    /**
     *
     * @param size
     * @param winCondition
     * @return new tictactoe with basic
     */
    public static TicTacToeGame createBasicTicTacToe(int size, int winCondition) {
        return new TicTacToeGame(size, new BasicTicTacToe(size, winCondition));
    }
    
    /**
     * @return the corresponding int starting from 0
     */
    private int convertCharToInt(char c) {
        return (int) (c - 64) - 1;
    }
    
    /**
     * @param i int starting from 0
     * @return corresponding char
     */
    private char convertIntToChar(int i) {
        return (char) (i + 64 + 1);
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
    @Override
    public String toString() {
        String[][] board = position.getBoard();
        String printedBoard = "   |";
        
        for (int i = 0; i < size; i++) {
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
     *Makes the move on the board if it's valid
     * @param move move of the player as string, for example 3A,
     * where 3 is row and A is column 1
     * @param turn X or 0 turn
     * @return true if the move was valid, otherwise false
     */
    public boolean makeMove(String move, String turn) {
        int row;
        int col;
        
        try {
            row = Integer.parseInt(move.substring(0, move.length() - 1)) - 1;
            col = convertCharToInt(Character.toUpperCase(move.charAt(move.length() - 1)));
        } catch (NumberFormatException e) {
            return false;
        }
        
        if (position.makeMove(row, col, turn)) {
            return true;
        }
        
        return false;
    }

    /**
     *
     * @return true if the board is full (there are no possible moves left)
     */
    public boolean tie() {
        return position.getMovesLeft() <= 0;
    }
    
    /**
     *
     * @return true if the player in turn has won
     */
    public boolean won() {
        return type.won(position);
    }
    
    /**
     *
     * @return
     */
    public String[][] getBoard() {
        return position.getBoard();
    }
    
    /**
     *
     * @return
     */
    public Position getPosition() {
        return position;
    }
    
    public void setPosition(Position position) {
        this.position = position;
    }
    
    public GameType getType() {
        return type;
    }
}

