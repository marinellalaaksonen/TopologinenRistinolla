/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToeGames;


public class BasicTicTacToe extends TicTacToeGame {
    
    public BasicTicTacToe(int size, int winCondition) {
        super(size, winCondition);
    }
    
    @Override
    public boolean won() {
        return false;
        //return ((checkHorisontalLeft(row - 1) + checkHorisontalRight(row + 1)) => );
        //    || 
    }
    
    @Override
    public int evaluate() {
        return 0;
    }
}
