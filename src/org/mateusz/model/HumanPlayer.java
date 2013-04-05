package org.mateusz.model;

import org.mateusz.remote.IGameListener;
import org.mateusz.utils.PlayerSymbol;

import java.rmi.RemoteException;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/4/13
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class HumanPlayer implements IPlayer {

    public static final Logger logger = Logger.getLogger(HumanPlayer.class.getSimpleName());

    private IGameListener listener;
    private String nick;
    private PlayerSymbol symbol;

    public HumanPlayer(IGameListener listener, String nick, PlayerSymbol symbol) {
        this.listener = listener;
        this.nick = nick;
        this.symbol = symbol;
    }

    @Override
    public int[] makeMove() {
        try {
            while( !listener.isMoveReady() )
                sleep(10);
        }   catch (InterruptedException e) {
            logger.severe(e.getLocalizedMessage());
        }   catch (RemoteException e) {
            logger.severe(e.getLocalizedMessage());
        }
        try {
            return listener.getMove();
        } catch (RemoteException e) {
            logger.severe(e.getLocalizedMessage());
        }
        return null;
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
