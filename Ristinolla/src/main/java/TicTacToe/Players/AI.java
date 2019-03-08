/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe.Players;

import TicTacToe.TicTacToeGame.TicTacToeGame;
import TicTacToe.TicTacToeGame.Position;
import TicTacToe.TicTacToeGame.Evaluator;
import TicTacToe.IO.IO;

/**
 * The methods needed for AI to decide it's next move. Implements minmax with 
 * alpha-beta pruning
 * @author marinella
 */
public class AI implements Player {
    private String mark;
    private Evaluator evaluator;
    private int depthConstant;
    
    /**
     * @param mark if the player is palying with X or 0
     * @param evaluator
     */
    public AI(String mark, Evaluator evaluator) {
        this.mark = mark;
        this.evaluator = evaluator;
        this.depthConstant = 28;
    }
    
    /**
     * 0's turn, tries to minimize the value of the game from the position given
     * @param position the current game position
     * @param depht left to go (how many moves to go before returning)
     * @param alpha the current alpha value from the parent
     * @param beta the current beta value from the parent
     * @param valueOfGame value of moves made by minmax this far
     * @return the value of the made moves or the value of the game (if finished).
     */
    private int minNode(Position position, int depth, int alpha, int beta, int valueOfGame) {
        valueOfGame += evaluator.evaluate(position, "X", depth);
        
        if (position.getMovesLeft() <= 0 || depth == 0 || evaluator.won(position)) {
            return valueOfGame;
        }
        
        int value = Integer.MAX_VALUE;
        Position[] nextPositions = position.getNextPositions("0");

        for (int i = 0; i < nextPositions.length; i++) {
            int min = maxNode(nextPositions[i], depth - 1, alpha, beta, valueOfGame);
            if (min < value) {
                value = min;
            }
            beta = Math.min(value, beta);
            if (alpha >= beta) return value;
        }
        
        return value;
    }
    
    /**
     * X's turn, tries to maximize the value of the game from the position given
     * @param position the current game position
     * @param depht left to go (how many moves to go before returning)
     * @param alpha the current alpha value from the parent
     * @param beta the current beta value from the parent
     * @param valueOfGame value of moves made by minmax this far
     * @return the value of the made moves or the value of the game (if finished).
     */
    private int maxNode(Position position, int depth, int alpha, int beta, int valueOfGame) {
        valueOfGame = evaluator.evaluate(position, "0", depth);
        
        if (position.getMovesLeft() <= 0 || depth == 0 || evaluator.won(position)) {
            return valueOfGame;
        }
        
        int value = Integer.MIN_VALUE;
        Position[] nextPositions = position.getNextPositions("X");

        for (int i = 0; i < nextPositions.length; i++) {
            int max = minNode(nextPositions[i], depth - 1, alpha, beta, valueOfGame);
            if (max > value) {
                value = max;
            }
            alpha = Math.max(value, alpha);
            if (alpha >= beta) return value;
        }
        
        return value;
    }
    
    /**
     * 0's turn, tries to minimize the value of the game from the position given
     * @param position the current game position
     * @param depht left to go (how many moves to go before returning)
     * @param alpha the current alpha value from the parent
     * @param beta the current beta value from the parent
     * @param valueOfGame value of moves made by minmax this far
     * @return the position of the best move
     */
    private Position bestMoveFor0(Position currentPosition, int depth) {
        Position[] nextPositions = currentPosition.getNextPositions(mark);
        Position bestMove = nextPositions[0];
        int value = Integer.MAX_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
            
        for (int i = 0; i < nextPositions.length; i++) {
            int min = maxNode(nextPositions[i], depth, alpha, beta, 0);

            if (min < value) {
                value = min;
                bestMove = nextPositions[i];
            }
            
            beta = Math.min(value, beta);
        }
        
        return bestMove;
    }
    
    /**
     * X's turn, tries to maximize the value of the game from the position given
     * @param position the current game position
     * @param depht left to go (how many moves to go before returning)
     * @param alpha the current alpha value from the parent
     * @param beta the current beta value from the parent
     * @param valueOfGame value of moves made by minmax this far
     * @return the position of the best move
     */
    private Position bestMoveForX(Position currentPosition, int depth) {
        Position[] nextPositions = currentPosition.getNextPositions(mark);
        Position bestMove = nextPositions[0];
        int value = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
            
        for (int i = 0; i < nextPositions.length; i++) {
            int max = minNode(nextPositions[i], depth, alpha, beta, 0);

            if (max > value) {
                value = max;
                bestMove = nextPositions[i];
            }
            
            alpha = Math.max(value, alpha);
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
     * Makes the move for the AI, in the middle of the board if first move, otherwise
     * uses minmax to determine the move.
     * @param io
     * @param currentPosition
     * @return next move for the AI
     */
    @Override
    public String move(IO io, Position currentPosition) {
        int size = currentPosition.getBoard().length;
        if (currentPosition.getMovesLeft() == size * size) {
            return "" + (size / 2 + 1) + convertIntToChar(size / 2);
        }
        
        Position bestMove;
        
        int depth = 80/currentPosition.getMovesLeft() + depthConstant/10; 

        long startTime = System.currentTimeMillis();

        if (mark.equals("0")) bestMove = bestMoveFor0(currentPosition, depth);
        else bestMove = bestMoveForX(currentPosition, depth);

        long endTime = System.currentTimeMillis();
        
        
        String move = "" + (bestMove.getRowOfLatestMove() + 1) 
            + convertIntToChar(bestMove.getColOfLatestMove());
        
        io.print("Tekoäly teki siirron " + move + ", siirto kesti "
            + (endTime - startTime) + " ms");
        
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
    
    /**
     * Sets the constant which regulates how deep minmax goes
     * @param depthConstant
     */
    public void setDepthConstant(int depthConstant) {
        this.depthConstant = depthConstant;
    }
    
}
