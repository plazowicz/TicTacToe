package org.mateusz.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/3/13
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IClientObserver extends Remote {

    public void inviteToGame(IGameListener listener) throws RemoteException;

    public void loseConnection(IGameListener listener) throws RemoteException;
}
