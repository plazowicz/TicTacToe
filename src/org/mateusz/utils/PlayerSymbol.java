package org.mateusz.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/3/13
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public enum PlayerSymbol {

    CROSS("X"), CIRCLE("O"), NOBODY("N"), LAST("L");

    private String code;

    private PlayerSymbol(String s) {
        code = s;
    }

    public String toString() {
        return code;
    }

    public static final Map<PlayerSymbol,PlayerSymbol> OPPOSITE_SYMBOLS = Collections.unmodifiableMap(new HashMap<PlayerSymbol, PlayerSymbol>() {
        {
            put(PlayerSymbol.CIRCLE,PlayerSymbol.CROSS);
            put(PlayerSymbol.CROSS,PlayerSymbol.CIRCLE);
        }
    });

}
