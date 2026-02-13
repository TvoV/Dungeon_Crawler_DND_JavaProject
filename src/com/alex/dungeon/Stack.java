package com.alex.dungeon;

import java.util.LinkedList;

public class Stack {

    private LinkedList<Integer> elements;

    public Stack() {
        this.elements = new LinkedList<Integer>();
    }


    public void push( int id  ) {
        this.elements.add( id );
    }

    public int pop( ) {
        return this.elements.removeLast();
    }

    public int peak( ) {
        return this.elements.getLast();
    }


    public int size() {
        return this.elements.size();
    }
}
