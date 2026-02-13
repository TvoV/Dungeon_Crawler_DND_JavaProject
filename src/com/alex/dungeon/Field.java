package com.alex.dungeon;

import com.alex.entities.enemies.Enemy;
import com.alex.items.Item;

public abstract class Field {

    // ----------------------------------------------------------------------
    // Attribute
    // ----------------------------------------------------------------------

    private boolean isDiscovered = false;

    private String eventType = null;
    private boolean eventResolved = false;

    private Field north;
    private Field east;
    private Field south;
    private Field west;

    private Enemy enemy;
    private Item lootItem;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Field() {}

    // ----------------------------------------------------------------------
    // Getter
    // ----------------------------------------------------------------------

    public Field getNorth() {
        return north;
    }

    public Field getEast() {
        return east;
    }

    public Field getSouth() {
        return south;
    }

    public Field getWest() {
        return west;
    }

    public boolean getIsDiscovered() {
        return isDiscovered;
    }

    public String getEventType() { return eventType; }

    public boolean getEventResolved() { return eventResolved; }

    public Item getLootItem() {
        return lootItem;
    }

    // ----------------------------------------------------------------------
    // Setter
    // ----------------------------------------------------------------------

    public void setNorth(Field north) {
        this.north = north;
        if (north != null) north.south = this;
    }

    public void setEast(Field east) {
        this.east = east;
        if (east != null) east.west = this;
    }

    public void setSouth(Field south) {
        this.south = south;
        if (south != null) south.north = this;
    }

    public void setWest(Field west) {
        this.west = west;
        if (west != null) west.east = this;
    }

    public void setLootItem(Item item) {
        this.lootItem = item;
    }

    // ----------------------------------------------------------------------
    // Methoden
    // ----------------------------------------------------------------------

    public String toString() {
        String str = "";

        if(north != null)	{
            str += "North ";
        }
        if(east != null)	{
            str += "East ";
        }
        if(south != null)	{
            str += "South ";
        }
        if(west != null)	{
            str += "West ";
        }
        return str;
    }

    // ----------------------------------------------------------------------
    // Getter
    // ----------------------------------------------------------------------

    public Enemy getEnemy() {
        return this.enemy;
    }

    // ----------------------------------------------------------------------
    // Setter
    // ----------------------------------------------------------------------

    public void setIsDiscovered(boolean discovered) {
        this.isDiscovered = discovered;
    }

    public void setEventType(String type) { this.eventType = type; }

    public void setEventResolved(boolean resolved) { this.eventResolved = resolved; }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
