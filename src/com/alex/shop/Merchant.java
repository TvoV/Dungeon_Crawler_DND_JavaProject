package com.alex.shop;

import com.alex.items.Item;

import java.util.List;

public class Merchant {

    // -----------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------

    private String name;
    private int gold;
    private List<Item> inventory;

    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    public Merchant(String name, int gold, List<Item> inventory){
        this.name = name;
        this.gold = gold;
        this.inventory = inventory;
    }

    // -----------------------------------------------------------
    // Getters
    // -----------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    // -----------------------------------------------------------
    // Setters
    // -----------------------------------------------------------

    public void setGold(int gold) {
        this.gold = gold;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }
}
