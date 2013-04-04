package org.mateusz.controller;

import org.mateusz.model.IPlayer;
import org.mateusz.model.Map;
import org.mateusz.utils.PlayerSymbol;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/4/13
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameController {

    private Map map;

    public GameController() {
        this.map = new Map();
    }

    public int[] makeMove(IPlayer player) {
        int[] move = player.makeMove();
        return map.setFieldValue(move[0],move[1],player.getSymbol())?move : null;
    }

    public PlayerSymbol gameDidFinish() {
        PlayerSymbol symbol = map.getFieldValue(0,0);
        if( symbol != null ) {
            if( map.getFieldValue(0,1) == symbol && map.getFieldValue(0,2) == symbol )
                return symbol;
            if( map.getFieldValue(1,0) == symbol && map.getFieldValue(2,0) == symbol )
                return symbol;
            if( map.getFieldValue(1,1) == symbol && map.getFieldValue(2,2) == symbol )
                return symbol;
        }
        symbol = map.getFieldValue(2,2);
        if( symbol != null ) {
            if( map.getFieldValue(2,1) == symbol && map.getFieldValue(2,0) == symbol )
                return symbol;
            if( map.getFieldValue(0,2) == symbol && map.getFieldValue(1,2) == symbol )
                return symbol;
        }
        symbol = map.getFieldValue(1,1);
        if( symbol != null ) {
            if( map.getFieldValue(0,1) == symbol && map.getFieldValue(2,1) == symbol )
                return symbol;
            if( map.getFieldValue(1,0) == symbol && map.getFieldValue(1,2) == symbol )
                return symbol;
            if( map.getFieldValue(0,2) == symbol && map.getFieldValue(2,0) == symbol )
                return symbol;
        }
        return null;
    }
}
