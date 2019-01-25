/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Players;

import IO.*;
import TicTacToeGames.*;

/**
 *
 * @author marinella
 */
public interface Player {
    String move(IO io, TicTacToeGame game);
    String getMark();
}
