package org.mateusz.model;

import org.mateusz.remote.IGameListener;
import org.mateusz.utils.PlayerSymbol;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/5/13
 * Time: 10:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class MasterYoda extends DummyBot {

    public MasterYoda(IGameListener listener, String name, PlayerSymbol symbol) {
        super(listener, name, symbol);
    }

    @Override
    public int[] makeMove() {
        return new int[0];  //To change body of implemented methods use File | Settings | File Templates.
    }
}
