package com.alex.dungeon;

public class Position {
    private int x;
    private int y;


    public Position( int x, int y ) {
        this.setX(x);
        this.setY(y);
    }


    public static int idFromPos( int x, int y, int numberOfFields) {
        return y * numberOfFields + x;
    }

    public static int idFromPos( Position pos, int numberOfFields) {
        return pos.getY() * numberOfFields + pos.getX();
    }

    public static Position posFromId( int id, int numberOfFields) {
        return new Position ( id % numberOfFields, id / numberOfFields);
    }


    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }


    public int getX() {
        return x;
    }


    public void setX(int x) {
        this.x = x;
    }
}
