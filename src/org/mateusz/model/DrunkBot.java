package org.mateusz.model;

import org.mateusz.remote.IGameListener;
import org.mateusz.utils.Constants;
import org.mateusz.utils.PlayerSymbol;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/4/13
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class DrunkBot extends DummyBot {

    private Random random;

    public DrunkBot(IGameListener listener, String name, PlayerSymbol symbol) {
        super(listener, name, symbol);
        random = new Random();
    }

    @Override
    public int[] makeMove() {
        try {
            int[] oppM = listener.getOpponentMove();
            map.setFieldValue(oppM[0],oppM[1], Constants.OPPOSITE_SYMBOLS.get(symbol));
            int x,y;
            do {
                x = random.nextInt(Map.SIZE);
                y = random.nextInt(Map.SIZE);
            } while( map.getFieldValue(x,y) == null);
            map.setFieldValue(x,y,symbol);
            return new int[]{x,y};
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
