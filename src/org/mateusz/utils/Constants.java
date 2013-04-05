package org.mateusz.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/5/13
 * Time: 9:41 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Constants {

    public static final Map<PlayerSymbol,PlayerSymbol> OPPOSITE_SYMBOLS = Collections.unmodifiableMap(new HashMap<PlayerSymbol, PlayerSymbol>() {
        {
            put(PlayerSymbol.CIRCLE, PlayerSymbol.CROSS);
            put(PlayerSymbol.CROSS, PlayerSymbol.CIRCLE);
        }
    });

}
