/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Players;

import IO.IO;
import TicTacToeGames.*;

/**
 *
 * @author marinella
 */
public class AI implements Player {
    private String mark;
    private GameType gameType;
    
    /**
     * @param mark if the player is palying with X or 0
     * @param gameType basic or other
     */
    public AI(String mark, GameType gameType) {
        this.mark = mark;
        this.gameType = gameType;
    }
    
    /**
     * 0's turn, tries to minimize the value of the game from the position given
     */
    private int minNode(Position position, int depht, int alpha, int beta) {
        if (position.getMovesLeft() <= 0 || depht == 0 || gameType.won(position)) {
            return gameType.evaluate(position, "X");
        }
        
        int value = Integer.MAX_VALUE;
        Position[] nextPositions = position.getNextPositions("0");

        for (int i = 0; i < nextPositions.length; i++) {
            int max = maxNode(nextPositions[i], depht - 1, alpha, beta);
            if (value > max) {
                value = max;
            }
            beta = Math.min(value, beta);
            if (alpha >= beta) return value;
        }
        
        return value;
    }
    
    /**
     * X's turn, tries to maximize the value of the game from the position given
     */
    private int maxNode(Position position, int depht, int alpha, int beta) {
        if (position.getMovesLeft() <= 0 || depht == 0 || gameType.won(position)) {
            return gameType.evaluate(position, "0");
        }
        
        int value = Integer.MIN_VALUE;
        Position[] nextPositions = position.getNextPositions("X");

        for (int i = 0; i < nextPositions.length; i++) {
            int min = minNode(nextPositions[i], depht - 1, alpha, beta);
            if (value < min) {
                value = min;
            }
            alpha = Math.max(value, alpha);
            if (alpha >= beta) return value;
        }
        
        return value;
    }
    
    private Position bestMoveFor0(Position currentPosition, int depht) {
        Position[] nextPositions = currentPosition.getNextPositions(mark);
        Position bestMove = nextPositions[0];
        int value = Integer.MAX_VALUE;
            
        for (int i = 0; i < nextPositions.length; i++) {
            int min = maxNode(nextPositions[i], depht, Integer.MIN_VALUE, Integer.MAX_VALUE);

            if (min < value) {
                value = min;
                bestMove = nextPositions[i];
            }
        }
        
        return bestMove;
    }
    
    private Position bestMoveForX(Position currentPosition, int depht) {
        Position[] nextPositions = currentPosition.getNextPositions(mark);
        Position bestMove = nextPositions[0];
        int value = Integer.MIN_VALUE;
            
        for (int i = 0; i < nextPositions.length; i++) {
            int max = minNode(nextPositions[i], depht, Integer.MIN_VALUE, Integer.MAX_VALUE);

            if (max > value) {
                value = max;
                bestMove = nextPositions[i];
            }
        }
        
        return bestMove;
    }
    
    /**
     * @param i int starting from 0
     * @return corresponding char
     */
    private char convertIntToChar(int i) {
        return (char) (i + 64 + 1);
    }

    /**
     * Makes the move for the AI
     * @param io
     * @param game current game situation
     * @return next move for the AI
     */
    @Override
    public String move(IO io, TicTacToeGame game) {
        Position currentPosition = game.getPosition();
        Position bestMove;
        
        int depht = 80/currentPosition.getMovesLeft() + 18/10; 

        if (mark.equals("0")) bestMove = bestMoveFor0(currentPosition, depht);
        else bestMove = bestMoveForX(currentPosition, depht);
        
        String move = "" + (bestMove.getRowOfLatestMove() + 1) 
            + convertIntToChar(bestMove.getColOfLatestMove());
        
        io.print("Tekoäly teki siirron " + move);
        
        return move;
    }

    /**
     *
     * @return if AI is playing with X or 0
     */
    @Override
    public String getMark() {
        return this.mark;
    }
    
}
