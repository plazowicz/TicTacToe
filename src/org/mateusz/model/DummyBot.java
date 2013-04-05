package org.mateusz.model;

import org.mateusz.remote.IGameListener;
import org.mateusz.utils.PlayerSymbol;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/5/13
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DummyBot implements IPlayer{

    protected IGameListener listener;
    protected String name;
    protected PlayerSymbol symbol;
    protected Map map;

    public DummyBot(IGameListener listener, String name, PlayerSymbol symbol) {
        this.listener = listener;
        this.name = name;
        this.symbol = symbol;
        map = new Map();
    }

    @Override
    public PlayerSymbol getSymbol() {
        return symbol;
    }

    @Override
    public IGameListener getListener() {
        return listener;
    }
}
