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

    CROSS("X"), CIRCLE("O"), DRAW("nobody"), LAST("L");

    private String code;

    private PlayerSymbol(String s) {
        code = s;
    }

    public String toString() {
        return code;
    }

}
