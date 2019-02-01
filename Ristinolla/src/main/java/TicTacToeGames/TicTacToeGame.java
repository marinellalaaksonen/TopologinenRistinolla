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
    private final int winCondition;
    private Position position;
    private final GameType type;
    
    /**
     * Constructor
     * @param size, size of the board
     * @param winCondition, how many noughts/crosses in line are needed to win
     */
    public TicTacToeGame(int size, int winCondition, GameType type) {
        this.size = size;
        this.winCondition = winCondition;
        this.position = new Position(new String[size][size], -1, -1, size * size);
        this.type = type;
    }
    
    public static TicTacToeGame createBasicTicTacToe(int size, int winCondition) {
        return new TicTacToeGame(size, winCondition, new BasicTicTacToe(size, winCondition));
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
    @Override
    public String toString() {
        String[][] board = position.getBoard();
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
            col = convertCharToInt(Character.toUpperCase(move.charAt(move.length() - 1))) - 1;
        } catch (NumberFormatException e) {
            return false;
        }
        
        if (position.makeMove(row, col, turn)) {
            return true;
        }
        
        return false;
    }

    public boolean tie() {
        return position.getMovesLeft() <= 0;
    }
    
    public boolean won() {
        return type.won(position);
    }
    

    public String[][] getBoard() {
        return position.getBoard();
    }
    
    public Position getPosition() {
        return position;
    }
}

