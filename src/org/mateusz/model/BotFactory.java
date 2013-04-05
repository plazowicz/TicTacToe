package org.mateusz.model;

import org.mateusz.remote.IGameListener;
import org.mateusz.utils.Level;
import org.mateusz.utils.PlayerSymbol;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/5/13
 * Time: 10:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class BotFactory {

    private static BotFactory factory;

    private BotFactory() {

    }

    public static synchronized BotFactory getInstance() {
        if( factory == null ) {
            factory = new BotFactory();
        }
        return factory;
    }

    public IPlayer createBot(IGameListener listener,PlayerSymbol symbol, Level level) {
        switch (level) {
            case DRUNK_BOT:
                return new DrunkBot(listener,Level.DRUNK_BOT.getName(),symbol);
            case MASTER_YODA:
                return new MasterYoda(listener,Level.MASTER_YODA.getName(),symbol);
            default:
                return null;
        }
    }

}
