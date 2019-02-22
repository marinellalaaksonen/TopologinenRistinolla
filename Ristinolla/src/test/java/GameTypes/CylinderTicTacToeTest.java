/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTypes;

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
public class CylinderTicTacToeTest {
    private CylinderTicTacToe game;
    
    @Before
    public void setUp() {
        this.game = new CylinderTicTacToe(3, 3);
    }

    /**
     * Test of goOverEdge method, of class CylinderTicTacToe.
     */
    @Test
    public void goOverEdgeReturnsNegativeIfRowTooBig() {
        int[] nextRowAndCol = game.goOverEdge(3, 0);
        assertEquals(-1, nextRowAndCol[0]);
    }
    
    @Test
    public void goOverEdgeReturnsNegativeIfRowTooSmall() {
        int[] nextRowAndCol = game.goOverEdge(-1, 2);
        assertEquals(-1, nextRowAndCol[0]);
    }
    
    @Test
    public void goOverEdgeReturnsRightIfColTooBig() {
        int[] nextRowAndCol = game.goOverEdge(0, 3);
        assertEquals(0, nextRowAndCol[0]);
        assertEquals(0, nextRowAndCol[1]);
    }
    
    @Test
    public void goOverEdgeReturnsRightIfColTooSmall() {
        int[] nextRowAndCol = game.goOverEdge(2, -1);
        assertEquals(2, nextRowAndCol[0]);
        assertEquals(2, nextRowAndCol[1]);
    }
    
}
