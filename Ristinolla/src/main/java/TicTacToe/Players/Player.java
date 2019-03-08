/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe.Players;

import TicTacToe.TicTacToeGame.Position;
import TicTacToe.IO.*;

/**
 *
 * @author marinella
 */
public interface Player {
    String move(IO io, Position currentPosition);
    String getMark();
}
