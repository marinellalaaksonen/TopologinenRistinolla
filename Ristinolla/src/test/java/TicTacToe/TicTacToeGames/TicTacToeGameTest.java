/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe.TicTacToeGames;

import TicTacToe.TicTacToeGame.TicTacToeGame;
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
public class TicTacToeGameTest {
    public TicTacToeGame game;
    
    @Before
    public void setUp() {
        game = TicTacToeGame.createBasicTicTacToe(12, 5);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void makeMoveWorksWithCorrectOutput() {
        game.makeMove("1A", "X");
        game.makeMove("12L", "0");
        
        assertEquals("X", game.getBoard()[0][0]);
        assertEquals("0", game.getBoard()[11][11]);
    }
    
    @Test
    public void makeReturnsTrueWithCorrectOutput() {
        assertEquals(true, game.makeMove("1A", "X"));
        assertEquals(true, game.makeMove("12L", "0"));
    }
    
    @Test
    public void makeMoveReturnsFalseIfInputHasIncorrectForm() {
        assertEquals(false, game.makeMove("A66", "X"));
    }
    
    @Test
    public void makeMoveReturnsFalseIfInputIsOutsideBoard() {
        assertEquals(false, game.makeMove("13A", "X"));
        assertEquals(false, game.makeMove("1M", "0"));
        assertEquals(false, game.makeMove("0A", "X"));
    }
    
    @Test
    public void makeMoveReturnsFalseIfTheSpotIsAlreadyPlayed() {
        game.makeMove("7A", "X");
        
        assertEquals(false, game.makeMove("7A", "X"));
    }
    
    @Test
    public void makeMoveDoesntChangeBoardIfTheSpotIsAlreadyPlayed() {
        game.makeMove("7C", "X");
        game.makeMove("7C", "0");
        
        assertEquals("X", game.getBoard()[6][2]);
    }
    
    @Test
    public void isFinishedReturnsTrueIfTheBoardIsFull() {
        game = TicTacToeGame.createBasicTicTacToe(3, 3);
        
        game.makeMove("1A", "X");
        game.makeMove("1B", "0");
        game.makeMove("1C", "X");
        game.makeMove("2A", "0");
        game.makeMove("2B", "X");
        game.makeMove("3C", "0");
        game.makeMove("2C", "X");
        game.makeMove("3A", "0");
        game.makeMove("3B", "X");
        
        assertEquals(true, game.tie());
    }
    
    @Test
    public void isFinishedReturnsFalseIfTheBoardIsNotFull() {
        game = TicTacToeGame.createBasicTicTacToe(3, 3);
        
        game.makeMove("1A", "X");
        game.makeMove("1B", "0");
        game.makeMove("1C", "X");
        game.makeMove("2A", "0");
        game.makeMove("2B", "X");
        game.makeMove("3C", "0");
        game.makeMove("3C", "X");
        game.makeMove("3A", "X");
        game.makeMove("3B", "0");
        
        assertEquals(false, game.tie());
    }

    /**
     * Test of visualBoard method, of class TicTacToeGame.
     */
    @Test
    public void testVisualBoard() {
        game.makeMove("5A", "X");
        game.makeMove("12D", "0");
        
        String board = "   | A | B | C | D | E | F | G | H | I | J | K | L |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
                + " 1 |   |   |   |   |   |   |   |   |   |   |   |   |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
                + " 2 |   |   |   |   |   |   |   |   |   |   |   |   |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
                + " 3 |   |   |   |   |   |   |   |   |   |   |   |   |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
                + " 4 |   |   |   |   |   |   |   |   |   |   |   |   |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
                + " 5 | X |   |   |   |   |   |   |   |   |   |   |   |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
                + " 6 |   |   |   |   |   |   |   |   |   |   |   |   |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
                + " 7 |   |   |   |   |   |   |   |   |   |   |   |   |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
                + " 8 |   |   |   |   |   |   |   |   |   |   |   |   |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
                + " 9 |   |   |   |   |   |   |   |   |   |   |   |   |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
                + " 10|   |   |   |   |   |   |   |   |   |   |   |   |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
                + " 11|   |   |   |   |   |   |   |   |   |   |   |   |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
                + " 12|   |   |   | 0 |   |   |   |   |   |   |   |   |\n"
                + "---+---+---+---+---+---+---+---+---+---+---+---+---+\n";
              
        assertEquals(board, game.toString());
    }

}
