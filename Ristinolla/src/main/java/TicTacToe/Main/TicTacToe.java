package TicTacToe.Main;

import TicTacToe.TicTacToeGame.TicTacToeGame;
import TicTacToe.IO.*;
import TicTacToe.Players.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Textual UI for TicTacToe, handles interaction with the players.
 * @author marinella
 */
public class TicTacToe {
    private IO io;
    private TicTacToeGame game;
    
    /**
     * Constructor
     * @param io to be used
     */
    public TicTacToe(IO io) {
        this.io = io;
    }
    
    /**
     * Asks player to select gameboard size and returns it if it's valid
     * @return size of the game
     */
    private int gameSize() {
        io.print("Valitse pelin koko: ");
        
        try {
            int size = Integer.parseInt(io.nextLine());
            
            if (size < 3) {
                io.print("Koon tulee olla vähintään 3");
                return gameSize();
            } else return size;
            
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
        io.print("Valitse voittoehto: ");
        
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
     * Asks player to select AI performance and updates it to the AI
     */
    private void chooseAIPerformance(AI ai) {
        io.print("Valitse H (hidas) tai N (nopea) tekoäly: ");
        String speed = io.nextLine().toUpperCase();
        
        if (speed.equals("N")) ai.setDepthConstant(28);
        else if (speed.equals("H")) ai.setDepthConstant(38);
        else {
            io.print("Virheellinen valinta");
            chooseAIPerformance(ai)
;        }
    }
    
    /**
     * Asks player to select playertype(AI or human player)
     * @return new Player object
     */
    private Player choosePlayer(String mark) {
        io.print("Valitse pelaaja " + mark + " I (ihminen) tai T (tekoäly): ");
        String player = io.nextLine();
        
        if (player.toUpperCase().equals("I")) return new HumanPlayer(mark);
        else if (player.toUpperCase().equals("T")) {
            AI ai = new AI(mark, game.getEvaluator());
            chooseAIPerformance(ai);
            return ai;
        } else {
            io.print("Virheellinen valinta");
            return choosePlayer(mark);
        }
    }
    
    /**
     * Asks player to select gametype, creates and updates it to the object parameter
     */
    private void chooseGameType(int size, int winCondition) {
        io.print("Valitse pelityyppi:\n"
            + "B = basic"
            + "\nC = cylinder"
//            + "\nM = Möbius strip"
        );
        
        String gameType = io.nextLine().toUpperCase();
        
        if (gameType.equals("B")) {
            this.game = TicTacToeGame.createBasicTicTacToe(size, winCondition);
        } else if (gameType.equals("C")) {
            this.game = TicTacToeGame.createCylinderTicTacToe(size, winCondition);
//        } else if (gameType.equals("M")) {
//            this.game = TicTacToeGame.createMobiusStripTicTacToe(size, winCondition);
        } else {
            io.print("Virheellinen valinta");
            chooseGameType(size, winCondition);
        }
    }
    
    /**
     * Makes the preparations needed to start the game
     */
    public void start() {
        int size = gameSize();
        int winCondition = winCondition(size);
        chooseGameType(size, winCondition);
        
        Player playerX = choosePlayer("X");
        Player player0 = choosePlayer("0");
        
        io.print(game.toString());
        
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
            String move = playerInTurn.move(io, game.getPosition());
            
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
