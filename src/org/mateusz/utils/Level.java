package org.mateusz.utils;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/5/13
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
public enum Level {
    DRUNK_BOT("Drinker"), MASTER_YODA("Yoda");

    private String name;

    private Level(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }
}
