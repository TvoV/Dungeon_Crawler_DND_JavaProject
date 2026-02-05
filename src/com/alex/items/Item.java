package com.alex.items;

public class Item {

    // ----------------------------------------------------------------------
    // Attributes
    // ----------------------------------------------------------------------

    private String name = "";
    private int buyValue = 0;
    private int sellValue = 0;
    private boolean isConsumable = false;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Item(String name,
                int buyValue,
                int sellValue,
                boolean isConsumable) {
        this.name = name;
        this.buyValue = buyValue;
        this.sellValue = sellValue;
        this.isConsumable = isConsumable;
    }

    public void Item(){};

    // ----------------------------------------------------------------------
    // Getter
    // ----------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public int getBuyValue() {
        return buyValue;
    }

    public int getSellValue() {
        return sellValue;
    }

    public boolean isConsumable() {
        return isConsumable;
    }


    // ----------------------------------------------------------------------
    // Setter
    // ----------------------------------------------------------------------


    public void setName(String name) {
        this.name = name;
    }

    public void setBuyValue(int buyValue) {
        this.buyValue = buyValue;
    }

    public void setSellValue(int sellValue) {
        this.sellValue = sellValue;
    }

    public void setConsumable(boolean consumable) {
        isConsumable = consumable;
    }
}
