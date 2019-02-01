
import IO.*;
import Players.*;
import TicTacToeGames.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marinella
 */
public class TicTacToe {
    private IO io;
    private TicTacToeGame game;
    
    /**
     * Constructor
     * @param io
     */
    public TicTacToe(IO io) {
        this.io = io;
    }
    
    /**
     * Asks player to select gameboard size and returns it if it's valid
     * @return size of the game
     */
    private int gameSize() {
        io.print("Valitse  pelin koko: ");
        
        try {
            return Integer.parseInt(io.nextLine());
        } catch (NumberFormatException e) {
            io.print("Pelin koon tulee olla numero");
            return gameSize();
        }
    }
    
    /**
     * Asks player to select win condition and returns it if it's valid
     * @return game's win condition
     */
    private int winCondition(int size) {
        io.print("Valitse  voittoehto: ");
        
        try {
            int winCondition = Integer.parseInt(io.nextLine());
            
            if (winCondition <= size) return winCondition;
            else {
                io.print("Voittoehdon tulee olla pienempi kuin pelin koko");
                return winCondition(size);
            }
        } catch (NumberFormatException e) {
            io.print("Voittoehdon tulee olla numero");
            return winCondition(size);
        }
    }
    
    /**
     * Makes the preparations needed to start the game
     */
    public void start() {
        int size = gameSize();
        int winCondition = winCondition(size);
        
        this.game = new TicTacToeGame(size, winCondition, new BasicTicTacToe(size, winCondition));
        io.print(game.toString());
        
        Player playerX = new HumanPlayer("X");
        Player player0 = new HumanPlayer("0");
        
        play(playerX, player0);
    }
    
    private Player switchPlayer(Player current, Player playerX, Player player0) {
        if (current.equals(playerX)) return player0;
        else return playerX;
    }
    
    /**
     * Carries out the actual playing
     */
    private void play(Player playerX, Player player0) {
        Player playerInTurn = playerX;
        
        while(!game.won() && !game.tie()) {
            io.print("Pelaajan " + playerInTurn.getMark() + " vuoro: ");
            String move = playerInTurn.move(io, game);
            
            if (!game.makeMove(move, playerInTurn.getMark())) {
                io.print("Siirto ei ole validi");
            } else {
                io.print(game.toString());
                playerInTurn = switchPlayer(playerInTurn, playerX, player0);
            }
        }
        
        if(game.won()) {
            playerInTurn = switchPlayer(playerInTurn, playerX, player0);
            io.print("Pelaaja " + playerInTurn.getMark() + " voittaa, peli päättyy");
        } else {
            io.print("Tasapeli, peli päättyy");
        }
    }
}
