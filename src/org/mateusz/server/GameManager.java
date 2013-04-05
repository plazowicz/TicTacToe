package org.mateusz.server;

import org.mateusz.controller.GameRunnable;
import org.mateusz.model.BotFactory;
import org.mateusz.model.DrunkBot;
import org.mateusz.model.HumanPlayer;
import org.mateusz.model.IPlayer;
import org.mateusz.remote.IClientObserver;
import org.mateusz.remote.IGameListener;
import org.mateusz.remote.IGameManager;
import org.mateusz.utils.Constants;
import org.mateusz.utils.Level;
import org.mateusz.utils.PlayerSymbol;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/4/13
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameManager extends UnicastRemoteObject implements IGameManager {

    private UserManager userManager;
    private Map<String,GameRunnable> games;

    protected GameManager(UserManager userManager) throws RemoteException {
        super();
        this.userManager = userManager;
        this.games = new HashMap<String, GameRunnable>();
    }

    @Override
    public IGameListener createGameWithHuman(PlayerSymbol symbol, String nick) throws RemoteException, MalformedURLException {
        IGameListener gameListener = new GameListener(Constants.OPPOSITE_SYMBOLS.get(symbol));
//        Naming.rebind(nick+"_gl",gameListener);
        IPlayer player = new HumanPlayer(gameListener,nick,symbol);
        GameRunnable gr = new GameRunnable(player,userManager.getClientObserver(nick));
        games.put(nick,gr);
        return gameListener;
    }

    @Override
    public IGameListener joinGame(String name, String nick) throws RemoteException, MalformedURLException {
        GameRunnable gt = games.get(name);
        IGameListener gl = new GameListener(gt.getFirstPlayer().getSymbol());
//        Naming.rebind(nick+"_gl",gl);
        IPlayer player = new HumanPlayer(gl,nick, Constants.OPPOSITE_SYMBOLS.get(gt.getFirstPlayer().getSymbol()));
        IClientObserver observer = userManager.getClientObserver(nick);
        gt.addPlayer(player,observer);
        gt.getFirstPlayer().getListener().setPresence();
        return gl;
    }

    @Override
    public IGameListener createGameWithAI(PlayerSymbol symbol, String nick, Level botLevel) throws RemoteException {
        IGameListener gl = new GameListener(Constants.OPPOSITE_SYMBOLS.get(symbol));
        IPlayer player = new HumanPlayer(gl,nick,symbol);
        IClientObserver observer = userManager.getClientObserver(nick);
        GameRunnable gr = new GameRunnable(player,observer);
        IGameListener botGl = new GameListener(symbol);
        IPlayer bot = BotFactory.getInstance().createBot(botGl,Constants.OPPOSITE_SYMBOLS.get(symbol),botLevel);
        gr.addPlayer(bot,null);
        gl.setPresence();
        new Thread(gr).start();
        return gl;
    }

    @Override
    public void startGame(String owner) throws RemoteException {
        new Thread(games.get(owner)).start();
    }

    @Override
    public String[] listGames() throws RemoteException {
        Set<String> set = games.keySet();
        String[] tmp = new String[set.size()];
        return set.toArray(tmp);
    }
}
