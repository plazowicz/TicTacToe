package org.mateusz.server;

import org.mateusz.remote.IGameListener;
import org.mateusz.utils.PlayerSymbol;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/4/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameListener extends UnicastRemoteObject implements IGameListener {

    public static final Logger logger = Logger.getLogger(GameListener.class.getSimpleName());

    private int[] move;
    private int[] opponentMove;
    private PlayerSymbol winner;

    protected GameListener() throws RemoteException {
        super();
    }

    @Override
    public synchronized void makeMove(int[] move) throws RemoteException {
        this.move = move;
        notifyAll();
    }

    @Override
    public synchronized void setOpponentMove(int[] move) throws RemoteException {
        opponentMove = move;
        notifyAll();
    }

    @Override
    public int[] getOponentMove() throws RemoteException {
        int[] opponentMove = this.opponentMove;
        this.opponentMove = null;
        return opponentMove;
    }

    @Override
    public int[] getMove() throws RemoteException {
        int[] move = this.move;
        this.move = null;
        return move;
    }

    @Override
    public boolean isReady() throws RemoteException {
        return move != null;
    }

    @Override
    public PlayerSymbol gameDidFinish() throws RemoteException {
        return winner;
    }

    @Override
    public synchronized void setWinner(PlayerSymbol symbol) throws RemoteException {
        winner = symbol;
        notifyAll();
    }

    @Override
    public boolean isCheckReady() throws RemoteException {
        return winner != null;
    }
}
