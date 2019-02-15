/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Players;

import IO.*;
import TicTacToeGames.*;
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
public class AITest {
    private AI aiX;
    private AI ai0;
    private TicTacToeGame game;
    private IO io;
    
    @Before
    public void setUp() {
        this.aiX = new AI("X", new BasicTicTacToe(3, 3));
        this.ai0 = new AI("0", new BasicTicTacToe(3, 3));
        this.game = TicTacToeGame.createBasicTicTacToe(3, 3);
        this.io = new ConsoleIO();
    }

    /**
     * Test of move method, of class AI.
     */
    @Test
    public void moveWorksWhenXWinsWithNextMove() {
        String[][] board = {{"X", "X", null}, {null, "0", null}, {"0", null, null}};
        game.setPosition(new Position(board, 1, 0, 5));
        assertEquals("1C", aiX.move(io, game));
    }
    
    @Test
    public void moveWorksWhen0WinsWithNextMove() {
        String[][] board = {{null, "X", "X"}, {"0", null, "X"}, {"0", null, null}};
        game.setPosition(new Position(board, 1, 0, 4));
        assertEquals("1A", ai0.move(io, game));
    }
    
    @Test
    public void moveWorksWhenXChoosesBetweenBlockingAndWinning() {
        String[][] board = {{null, null, null}, {null, "0", "X"}, {null, "0", "X"}};
        game.setPosition(new Position(board, 1, 1, 5));
        assertEquals("1C", aiX.move(io, game));
    }
    
    @Test
    public void moveWorksWhenXBlocking0FromWinning() {
        String[][] board = {{"X", null, null}, {null, "0", "X"}, {null, "0", null}};
        game.setPosition(new Position(board, 1, 1, 5));
        assertEquals("1B", aiX.move(io, game));
    }
    
    @Test
    public void moveWorksWhen0BlockingXFromWinning() {
        String[][] board = {{"X", null, null}, {null, null, null}, {null, "0", "X"}};
        game.setPosition(new Position(board, 0, 0, 6));
        assertEquals("2B", ai0.move(io, game));
    }
    
    @Test
    public void moveWorksWhenXSeveralMovesFromWinning() {
        String[][] board = {{"0", "X", "X"}, {null, null, null}, {null, null, "0"}};
        game.setPosition(new Position(board, 0, 0, 5));
        game.makeMove(aiX.move(io, game), "X");
        game.makeMove(ai0.move(io, game), "0");
        game.makeMove(aiX.move(io, game), "X");
        assertEquals(true, game.won());
    }
    
    @Test
    public void moveWorksWhen0SeveralMovesFromWinning() {
        String[][] board = {{"0", "0", null}, {"X", null, null}, {"X", null, null}};
        game.setPosition(new Position(board, 0, 0, 5));
        game.makeMove(aiX.move(io, game), "X");
        game.makeMove(ai0.move(io, game), "0");
        game.makeMove(aiX.move(io, game), "X");
        game.makeMove(ai0.move(io, game), "0");
        assertEquals(true, game.won());
    }
}
