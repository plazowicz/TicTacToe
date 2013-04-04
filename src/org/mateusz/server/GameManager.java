package org.mateusz.server;

import org.mateusz.controller.GameThread;
import org.mateusz.model.HumanPlayer;
import org.mateusz.model.IPlayer;
import org.mateusz.remote.IClientObserver;
import org.mateusz.remote.IGameListener;
import org.mateusz.remote.IGameManager;
import org.mateusz.utils.PlayerSymbol;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/4/13
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameManager extends UnicastRemoteObject implements IGameManager {

    public static final Map<PlayerSymbol,PlayerSymbol> OPPOSITE_SYMBOLS = Collections.unmodifiableMap(new HashMap<PlayerSymbol, PlayerSymbol>() {
        {
            put(PlayerSymbol.CIRCLE,PlayerSymbol.CROSS);
            put(PlayerSymbol.CROSS,PlayerSymbol.CIRCLE);
        }
    });

    private UserManager userManager;
    private Map<String,GameThread> games;

    protected GameManager(UserManager userManager) throws RemoteException {
        super();
        this.userManager = userManager;
        this.games = new HashMap<String, GameThread>();
    }

    @Override
    public IGameListener createGameWithHuman(PlayerSymbol symbol, String nick) throws RemoteException, MalformedURLException {
        IGameListener gameListener = new GameListener();
        Naming.rebind(nick+"_gl",gameListener);
        IPlayer player = new HumanPlayer(gameListener,nick,symbol);
        GameThread gt = new GameThread(player,userManager.getClientObserver(nick));
        games.put(nick,gt);
        return gameListener;
    }

    @Override
    public IGameListener joinGame(String name, String nick) throws RemoteException, MalformedURLException {
        GameThread gt = games.get(name);
        IGameListener gl = new GameListener();
        Naming.rebind(nick+"_gl",gl);
        IPlayer player = new HumanPlayer(gl,nick,OPPOSITE_SYMBOLS.get(gt.getFirstPlayer().getSymbol()));
        IClientObserver observer = userManager.getClientObserver(nick);
        gt.addPlayer(player,observer);
        return gl;
    }

    @Override
    public IGameListener createGameWithAI(PlayerSymbol symbol, String nick) throws RemoteException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void startGame(String owner) throws RemoteException {
        new Thread(games.get(owner)).start();
    }
}
