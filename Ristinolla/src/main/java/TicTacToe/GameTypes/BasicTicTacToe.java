/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe.GameTypes;

/**
 * Methods of basic tictactoe to aid evaluator in gametype spesific moves. Can't
 * go over edges.
 * @author marinella
 */
public class BasicTicTacToe implements GameType {
    private final int size;
    private final int winCondition;
    
    /**
     *
     * @param size of the board
     * @param winCondition
     */
    public BasicTicTacToe(int size, int winCondition) {
        this.size = size;
        this.winCondition = winCondition;
    }
    
    @Override
    public int getSize() {
        return this.size;
    }
    
    @Override
    public int getWinCondition() {
        return winCondition;
    }
    
    /**
     * This method is only called if row or col have gone over the edge of the board,
     * so with basic tictactoe that means always incorrect move (marked with -1)
     * @param row
     * @param col
     * @return int[] {-1, -1} marking a incorrect move
     */
    @Override
    public int[] goOverEdge(int row, int col) {
        return new int[] {-1, -1};
    }
    
}
