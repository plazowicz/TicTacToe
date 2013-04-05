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
    private boolean ndPlayerPresence;
    private PlayerSymbol opponent;

    protected GameListener(PlayerSymbol opponent) throws RemoteException {
        super();
        ndPlayerPresence = false;
        winner = null;
        move = null;
        opponentMove = null;
        this.opponent = opponent;
    }

    @Override
    public void makeMove(int[] move) throws RemoteException {
        this.move = move;
    }

    @Override
    public void setOpponentMove(int[] move) throws RemoteException {
        opponentMove = move;
    }

    @Override
    public int[] getOpponentMove() throws RemoteException {
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
    public boolean isMoveReady() throws RemoteException {
        return move != null;
    }

    @Override
    public boolean isOpponentMoveReady() throws RemoteException {
        return opponentMove != null;
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

    @Override
    public boolean playerDidJoin() throws RemoteException {
        return ndPlayerPresence;
    }

    @Override
    public void setPresence() throws RemoteException {
        ndPlayerPresence = true;
    }

    @Override
    public PlayerSymbol getOpponentSymbol() throws RemoteException {
        return opponent;
    }

}
