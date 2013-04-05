package org.mateusz.controller;

import org.mateusz.model.IPlayer;
import org.mateusz.model.Map;
import org.mateusz.remote.IClientObserver;
import org.mateusz.utils.PlayerSymbol;

import java.rmi.RemoteException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/4/13
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameRunnable implements Runnable {

    public static final Logger logger = Logger.getLogger(GameRunnable.class.getSimpleName());

    private GameController gameController;
    private IPlayer firstPlayer;
    private IPlayer secondPlayer;
    private IClientObserver firstObserver;
    private IClientObserver secondObserver;
    private boolean running;

    public GameRunnable(IPlayer firstPlayer, IClientObserver firstObserver) {
        this.firstPlayer = firstPlayer;
        this.firstObserver = firstObserver;
        gameController = new GameController();
    }

    public void addPlayer(IPlayer secondPlayer, IClientObserver secondObserver) {
        this.secondPlayer = secondPlayer;
        this.secondObserver = secondObserver;
        running = true;
    }

    public IPlayer getFirstPlayer() {
        return firstPlayer;
    }

    public IPlayer getSecondPlayer() {
        return secondPlayer;
    }

    @Override
    public void run() {
        PlayerSymbol symbol;
        int count = 0;
        int[] move;
        while( running ) {
            try {
                count++;
                if( (move = gameController.makeMove(firstPlayer)) == null ) {
                    firstObserver.loseConnection(firstPlayer.getListener());
                    secondObserver.loseConnection(secondPlayer.getListener());
                    running = false;
                    break;
                }
                secondPlayer.getListener().setOpponentMove(move);

                symbol = gameController.gameDidFinish();
                firstPlayer.getListener().setWinner(symbol);
                secondPlayer.getListener().setWinner(symbol);
                if( symbol != PlayerSymbol.LAST ) {
                    running = false;
                    break;
                }

                if( count == Map.SIZE*Map.SIZE ) {
                    firstPlayer.getListener().setWinner(PlayerSymbol.DRAW);
                    secondPlayer.getListener().setWinner(PlayerSymbol.DRAW);
                    running = false;
                    break;
                }
                count++;
                if( (move = gameController.makeMove(secondPlayer)) == null ) {
                    firstObserver.loseConnection(firstPlayer.getListener());
                    secondObserver.loseConnection(secondPlayer.getListener());
                    running = false;
                    break;
                }

                firstPlayer.getListener().setOpponentMove(move);

                symbol = gameController.gameDidFinish();
                firstPlayer.getListener().setWinner(symbol);
                secondPlayer.getListener().setWinner(symbol);
                if( symbol != PlayerSymbol.LAST ) {
                    running = false;
                    break;
                }
            } catch(RemoteException e) {
                logger.severe(e.getLocalizedMessage());
            }
        }
    }
}
