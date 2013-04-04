package org.mateusz.model;

import org.mateusz.remote.IGameListener;
import org.mateusz.utils.PlayerSymbol;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/4/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IPlayer {

    public int[] makeMove();

    public PlayerSymbol getSymbol();

    public IGameListener getListener();

}
