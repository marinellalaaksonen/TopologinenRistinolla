/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToeGames;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marinella
 */
public class BasicTicTacToeTest {
    TicTacToeGame game;
    
    @Before
    public void setUp() {
        game = TicTacToeGame.createBasicTicTacToe(3, 3);
    }

    /**
     * Test of won method, of class BasicTicTacToe.
     */
    @Test
    public void wonReturnsFalseIfTheRowIsTooShort() {
        game.makeMove("1A", "X");
        game.makeMove("2A", "X");
        
        assertEquals(false, game.won());
    }
    
    @Test
    public void wonReturnsTrueIfThreeInCol() {
        game.makeMove("1A", "X");
        game.makeMove("2A", "X");
        game.makeMove("3A", "X");
        
        assertEquals(true, game.won());
    }
    
    @Test
    public void wonReturnsTrueIfThreeInRow() {
        game.makeMove("2A", "0");
        game.makeMove("2B", "0");
        game.makeMove("2C", "0");
        
        assertEquals(true, game.won());
    }
    
    @Test
    public void wonReturnsTrueIfThreeInRowDiagonalDown() {
        game.makeMove("1A", "0");
        game.makeMove("2B", "0");
        game.makeMove("3C", "0");
        
        assertEquals(true, game.won());
    }
    
    @Test
    public void wonReturnsTrueIfThreeInRowDiagonalUp() {
        game.makeMove("3A", "X");
        game.makeMove("2B", "X");
        game.makeMove("1C", "X");
        
        assertEquals(true, game.won());
    }
    
    @Test
    public void wonWorksWithBiggerBoard() {
        TicTacToeGame biggerGame = TicTacToeGame.createBasicTicTacToe(5, 3);
        
        
        biggerGame.makeMove("2B", "X");
        biggerGame.makeMove("2C", "X");
        biggerGame.makeMove("2E", "0");
        biggerGame.makeMove("2D", "X");
        
        assertEquals(true, biggerGame.won());
    }

    /**
     * Test of evaluate method, of class BasicTicTacToe.
     */
    @Test
    public void evaluateReturnsIntMaxIfXWon() {
        BasicTicTacToe basic = new BasicTicTacToe(3, 3);
        String[][] board = {{"X", "X", "X"}, {"0", null, null}, {"0", null, null}};
        assertEquals(Integer.MAX_VALUE, basic.evaluate(new Position(board, 2, 0, 5), "X"));
    }
    
    @Test
    public void evaluateReturnsIntMinIf0Won() {
        BasicTicTacToe basic = new BasicTicTacToe(3, 3);
        String[][] board = {{"X", "X", "0"}, {"X", "0", null}, {"0", null, null}};
        assertEquals(Integer.MIN_VALUE, basic.evaluate(new Position(board, 2, 0, 4), "0"));
    }
    
    @Test
    public void evaluateReturns0IfTie() {
        BasicTicTacToe basic = new BasicTicTacToe(3, 3);
        String[][] board = {{"X", "X", "0"}, {"0", "0", "X"}, {"X", "0", "X"}};
        assertEquals(0, basic.evaluate(new Position(board, 2, 2, 0), "X"));
    }
    
}
