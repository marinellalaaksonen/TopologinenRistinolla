/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe.TicTacToeGames;

import TicTacToe.TicTacToeGame.Position;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marinella
 */
public class PositionTest {
    private Position position;
    
    @Before
    public void setUp() {
        String[][] board = {{"0", "X", "X"}, {null, null, null}, {null, null, "0"}};
        this.position = new Position(board, 0, 0, 5);
    }

    /**
     * Test of makeMove method, of class Position.
     */
    
    @Test
    public void makeMoveReturnsTrueWithValidMove() {
        assertEquals(true, position.makeMove(1, 1, "X"));
    }
    
    @Test
    public void makeMoveReturnsFalseWithInvalidMove() {
        assertEquals(false, position.makeMove(0, 1, "X"));
    }
    
    @Test
    public void validMoveReducesMovesLeftWith1() {
        position.makeMove(1, 1, "X");
        assertEquals(4, position.getMovesLeft());
    }
    
    @Test
    public void invalidMoveDoesNotReduceMovesLeft() {
        position.makeMove(0, 0, "X");
        assertEquals(5, position.getMovesLeft());
    }
    
    @Test
    public void validMoveSetsNewRowAndColOfLatestMove() {
        position.makeMove(1, 2, "X");
        assertEquals(1, position.getRowOfLatestMove());
        assertEquals(2, position.getColOfLatestMove());
    }
    
    @Test
    public void invalidMoveDoesNotChangeRowAndColOfLatestMove() {
        position.makeMove(2, 2, "X");
        assertEquals(0, position.getRowOfLatestMove());
        assertEquals(0, position.getColOfLatestMove());
    }
    
    @Test
    public void validMovePutsTheMoveOnBoard() {
        position.makeMove(2, 0, "X");
        assertEquals("X", position.getBoard()[2][0]);
    }
    
    @Test
    public void invalidMoveDoesNotChangeBoard() {
        position.makeMove(2, 2, "X");
        assertEquals("0", position.getBoard()[2][2]);
    }

    /**
     * Test of getNextPositions method, of class Position.
     */
    @Test
    public void nextPositionsReturnRightAmountOfPositions() {
        assertEquals(5, position.getNextPositions("X").length);
    }

    @Test
    public void nextPositionsReturnRightPositions() {
        String[][] board0 = {{"0", "X", "X"}, {"X", null, null}, {null, null, "0"}};
        Assert.assertArrayEquals(board0, position.getNextPositions("X")[0].getBoard());
        
        String[][] board1 = {{"0", "X", "X"}, {null, "X", null}, {null, null, "0"}};
        Assert.assertArrayEquals(board1, position.getNextPositions("X")[1].getBoard());
        
        String[][] board2 = {{"0", "X", "X"}, {null, null, "X"}, {null, null, "0"}};
        Assert.assertArrayEquals(board2, position.getNextPositions("X")[2].getBoard());
        
        String[][] board3 = {{"0", "X", "X"}, {null, null, null}, {"X", null, "0"}};
        Assert.assertArrayEquals(board3, position.getNextPositions("X")[3].getBoard());
        
        String[][] board4 = {{"0", "X", "X"}, {null, null, null}, {null, "X", "0"}};
        Assert.assertArrayEquals(board4, position.getNextPositions("X")[4].getBoard());
    }
}
