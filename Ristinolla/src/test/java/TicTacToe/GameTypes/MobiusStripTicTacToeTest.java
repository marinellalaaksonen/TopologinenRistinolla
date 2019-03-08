/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TicTacToe.GameTypes;

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
public class MobiusStripTicTacToeTest {
    private MobiusStripTicTacToe game;

    @Before
    public void setUp() {
        this.game = new MobiusStripTicTacToe(3, 3);
    }

    /**
     * Test of goOverEdge method, of class MobiusStripTicTacToe.
     */
    @Test
    public void testGoOverEdge() {
        
    }

}