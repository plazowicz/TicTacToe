package org.mateusz.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/3/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IUserManager extends Remote {

    public boolean register(String nick,IClientObserver observer) throws RemoteException;

    public void inviteToGame(String nick, IGameListener listener) throws RemoteException;

}
