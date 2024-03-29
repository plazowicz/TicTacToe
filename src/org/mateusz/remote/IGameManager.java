package org.mateusz.remote;

import org.mateusz.utils.Level;
import org.mateusz.utils.PlayerSymbol;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/3/13
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IGameManager extends Remote {

    public IGameListener createGameWithHuman(PlayerSymbol symbol, String nick) throws RemoteException, MalformedURLException;

    public IGameListener joinGame(String name, String nick) throws RemoteException, MalformedURLException;

    public IGameListener createGameWithAI(PlayerSymbol symbol, String nick, Level botLevel) throws RemoteException;

    public void startGame(String owner) throws RemoteException;

    public String[] listGames() throws RemoteException;

}
