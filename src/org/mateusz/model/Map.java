package org.mateusz.model;

import org.mateusz.utils.PlayerSymbol;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/4/13
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Map {

    public static final int SIZE = 3;

    private PlayerSymbol[][] map;

    public Map() {
        map = new PlayerSymbol[SIZE][SIZE];
    }

    public PlayerSymbol[][] getMap() {
        return map;
    }

    public boolean setFieldValue(int x, int y, PlayerSymbol val) {
        if( map[x][y] != null )
            return false;
        map[x][y] = val;
        return true;
    }

    public PlayerSymbol getFieldValue(int x, int y) {
        return map[x][y];
    }

    public void print() {
        for( int i = 0 ; i < Map.SIZE ; i++ ) {
            for( int j = 0 ; j < Map.SIZE ; j++ ) {
                if( map[i][j] != null )
                    System.out.print(map[i][j].toString());
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Map map = new Map();
        for( int i = 0 ; i < SIZE ; i++ ) {
            for( int j = 0 ; j < SIZE ; j++) {
                System.out.println(map.getMap()[i][j]);
            }
        }
    }

}
