package org.mateusz.remote;

import org.mateusz.utils.PlayerSymbol;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.locks.Condition;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/3/13
 * Time: 7:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IGameListener extends Remote {

    public void makeMove(int[] move) throws RemoteException;

    public void setOpponentMove(int[] move) throws RemoteException;

    public int[] getOponentMove() throws RemoteException;

    public int[] getMove() throws RemoteException;

    public boolean  isReady() throws RemoteException;

    public PlayerSymbol gameDidFinish() throws RemoteException;

    public void setWinner(PlayerSymbol symbol) throws RemoteException;

    public boolean isCheckReady() throws RemoteException;

    public Condition getMyCond() throws RemoteException;

    public Condition getOpponentCond() throws RemoteException;

    public boolean playerDidJoin() throws RemoteException;

    public void setPresence() throws RemoteException;

    public Condition getJoinCond() throws RemoteException;

}