package org.mateusz.client;

import org.mateusz.remote.IClientObserver;
import org.mateusz.remote.IGameListener;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/3/13
 * Time: 6:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientObserver extends UnicastRemoteObject implements IClientObserver {

    private boolean connection = true;

    protected ClientObserver() throws RemoteException {
        super();
    }

    @Override
    public void inviteToGame(IGameListener listener) throws RemoteException {

    }

    @Override
    public void loseConnection(IGameListener listener) throws RemoteException {
        connection = false;
    }

    public boolean isConnected() {
        return connection;
    }
}
