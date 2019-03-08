/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe.TicTacToeGames;

import TicTacToe.TicTacToeGame.Position;
import TicTacToe.GameTypes.BasicTicTacToe;
import TicTacToe.TicTacToeGame.Evaluator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marinella
 */
public class EvaluatorTest {
    //private Evaluator evaluator
    private Evaluator evaluator;
    
    @Before
    public void setUp() {
        evaluator = new Evaluator(new BasicTicTacToe(3, 3));
    }

    /**
     * Test of won method, of class BasicTicTacToe.
     */
    
    @Test
    public void wonReturnsFalseWithEmptyBoard() {
        String[][] board = {{null, null, null}, {null, null, null}, {null, null, null}};
        Position position = new Position(board, 0, 0, 9);
        
        assertEquals(false, evaluator.won(position));
    }
    
    @Test
    public void wonReturnsFalseIfTheRowIsTooShort() {
        String[][] board = {{"X", "X", null}, {null, "0", null}, {"0", null, null}};
        Position position = new Position(board, 1, 1, 5);
        
        assertEquals(false, evaluator.won(position));
    }
    
    @Test
    public void wonReturnsTrueIfThreeInCol() {
        String[][] board = {{"X", "0", "X"}, {"X", "0", null}, {null, "0", null}};
        Position position = new Position(board, 1, 1, 3);
        
        assertEquals(true, evaluator.won(position));
    }
    
    @Test
    public void wonReturnsTrueIfThreeInRow() {
        String[][] board = {{"X", "X", "X"}, {"0", "0", null}, {null, "0", null}};
        Position position = new Position(board, 0, 0, 3);
        
        assertEquals(true, evaluator.won(position));
    }
    
    @Test
    public void wonReturnsTrueIfThreeInRowDiagonalDown() {
        String[][] board = {{"0", "X", "X"}, {"X", "0", null}, {null, null, "0"}};
        Position position = new Position(board, 0, 0, 3);
        
        assertEquals(true, evaluator.won(position));
    }
    
    @Test
    public void wonReturnsTrueIfThreeInRowDiagonalUp() {
        String[][] board = {{"0", "0", "X"}, {"X", "X", null}, {"X", null, "0"}};
        Position position = new Position(board, 2, 0, 2);
        
        assertEquals(true, evaluator.won(position));
    }
    
    @Test
    public void wonWorksWithBiggerBoard() {
        Evaluator biggerGame = new Evaluator(new BasicTicTacToe(5, 3));
        String[][] board = {
            {"0" , "0" , "X" , null, null},
            {null, null, null, "X" , null},
            {"0" , null, null, "X" , null},
            {null, "0" , "X" , "X" , null},
            {"0" , "0" , "X" , null, null}
        };
        
        Position position = new Position(board, 1, 3, 13);
        
        assertEquals(true, biggerGame.won(position));
    }

    /**
     * Test of evaluate method, of class BasicTicTacToe.
     */
    @Test
    public void evaluateReturnsRightIfXWonWithNoDepthLeft() {
        String[][] board = {{"X", "X", "X"}, {"0", null, null}, {"0", null, null}};
        assertEquals((int) 1E9, evaluator.evaluate(new Position(board, 0, 2, 5), "X", 0));
    }
    
    @Test
    public void evaluateReturnsRightIfXWonWithDepthLeft() {
        String[][] board = {{"X", "X", "X"}, {"0", null, null}, {"0", null, null}};
        assertEquals((int) 1E9 + 3, evaluator.evaluate(new Position(board, 0, 2, 5), "X", 3));
    }
    
    @Test
    public void evaluateReturnsRightIf0WonWithNoDepthLeft() {
        String[][] board = {{"X", "X", "0"}, {"X", "0", null}, {"0", null, null}};
        assertEquals((int) -1E9, evaluator.evaluate(new Position(board, 0, 2, 4), "0", 0));
    }
    
    @Test
    public void evaluateReturnsRightIf0WonWithDepthLeft() {
        String[][] board = {{"X", "X", "0"}, {"X", "0", null}, {"0", null, null}};
        assertEquals((int) -1E9 - 5, evaluator.evaluate(new Position(board, 0, 2, 4), "0", 5));
    }
    
    @Test
    public void evaluateReturns0IfTie() {
        String[][] board = {{"X", "X", "0"}, {"0", "0", "X"}, {"X", "0", "X"}};
        assertEquals(0, evaluator.evaluate(new Position(board, 2, 2, 0), "X", 0));
    }    
}
