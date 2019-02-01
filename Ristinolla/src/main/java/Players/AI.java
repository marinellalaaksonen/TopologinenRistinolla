/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Players;

import IO.IO;
import TicTacToeGames.*;


public class AI implements Player {
    private String mark;
    private GameType gameType;
    
    /**
     * @param mark if the player is palying with X or 0
     */
    public AI(String mark, GameType gameType) {
        this.mark = mark;
        this.gameType = gameType;
    }
    
    private int minNode(Position position, int depht, int alpha, int beta) {
        if (position.getMovesLeft() <= 0) return 0;
        else if (depht == 0 || gameType.won(position)) {
            return gameType.evaluate(position, "0");
        }
        
        int value = Integer.MAX_VALUE;
        Position bestMove;
        Position[] nextPositions = position.getNextPositions("0");

        for (int i = 0; i < nextPositions.length; i++) {
            int max = maxNode(nextPositions[i], depht - 1, alpha, beta);
            if (value >= max) {
                value = max;
                bestMove = nextPositions[i];
            }
            beta = Math.min(value, beta);
            if (alpha >= beta) return value;
        }
        
        return value;
    }
    
    private int maxNode(Position position, int depht, int alpha, int beta) {
        if (position.getMovesLeft() <= 0) return 0;
        else if (depht == 0 || gameType.won(position)) {
            return gameType.evaluate(position, "X");
        }
        
        int value = Integer.MIN_VALUE;
        Position bestMove;
        Position[] nextPositions = position.getNextPositions("X");

        for (int i = 0; i < nextPositions.length; i++) {
            int min = minNode(nextPositions[i], depht - 1, alpha, beta);
            if (value <= min) {
                value = min;
                bestMove = nextPositions[i];
            }
            alpha = Math.max(value, alpha);
            if (alpha >= beta) return value;
        }
        
        return value;
    }

    @Override
    public String move(IO io, TicTacToeGame game) {
        return " ";
//        Position currentPosition = game.getPosition();
//        Position bestMove;
//        Position[] nextPositions = currentPosition.getNextPositions(mark);
//
//        if (mark == "0") {
//            int value = Integer.MAX_VALUE;
//            
//            for (int i = 0; i < nextPositions.length; i++) {
//                int max = maxNode(nextPositions[i], 5, Integer.MIN_VALUE, Integer.MAX_VALUE);
//                
//                if (max <= value) {
//                    value = max;
//                    bestMove = nextPositions[i];
//                }
//            } 
//        } else {
//            int value = Integer.MAX_VALUE;
//            
//            for (int i = 0; i < nextPositions.length; i++) {
//                int max = maxNode(nextPositions[i], 5, Integer.MIN_VALUE, Integer.MAX_VALUE);
//                
//                if (max <= value) {
//                    value = max;
//                    bestMove = nextPositions[i];
//                }
//            }
//        }
//        
//        return 
    }

    @Override
    public String getMark() {
        return this.mark;
    }
    
}
