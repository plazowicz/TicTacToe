package org.mateusz.server;

import org.mateusz.remote.IClientObserver;
import org.mateusz.remote.IGameListener;
import org.mateusz.remote.IUserManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/3/13
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserManager extends UnicastRemoteObject implements IUserManager {

    public static final Logger logger = Logger.getLogger(UserManager.class.getSimpleName());

    private Map<String,IClientObserver> players;

    protected UserManager() throws RemoteException {
        super();
        players = new HashMap<String, IClientObserver>();
    }

    @Override
    public synchronized boolean register(String nick, IClientObserver observer) {
        if( players.containsKey(nick) ) {
            return false;
        }
        players.put(nick, observer);
        for(String n : listPlayers()) {
            System.out.println(n);
        }
        return true;
    }

    @Override
    public void inviteToGame(String nick, IGameListener listener) throws RemoteException {
        players.get(nick).inviteToGame(listener);
    }

    public Set<String> listPlayers() {
        return players.keySet();
    }

    public IClientObserver getClientObserver(String nick) {
        return players.get(nick);
    }
}
