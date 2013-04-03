package org.mateusz.server;

import org.mateusz.remote.IClientObserver;
import org.mateusz.remote.IRegistrationManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
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
public class RegistrationManager extends UnicastRemoteObject implements IRegistrationManager {

    public static final Logger logger = Logger.getLogger(RegistrationManager.class.getSimpleName());

    private Map<String,IClientObserver> players;

    protected RegistrationManager() throws RemoteException {
        players = new HashMap<String, IClientObserver>();
    }

    @Override
    public synchronized boolean register(String nick, IClientObserver observer) {
        if( players.containsKey(nick) ) {
            return false;
        }
        players.put(nick,observer);
        for(String n : listPlayers()) {
            System.out.println(n);
        }
        return true;
    }

    public Set<String> listPlayers() {
        return players.keySet();
    }
}
