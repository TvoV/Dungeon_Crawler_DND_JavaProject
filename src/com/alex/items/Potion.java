package com.alex.items;

public class Potion extends Item{

    // ----------------------------------------------------------------------
    // Attributes
    // ----------------------------------------------------------------------

    private int hpgain = 0;
    private int mpgain = 0;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Potion(String name,
                 int buyValue,
                 int sellValue,
                 boolean isConsumable,
                  int hpgain,
                  int mpgain) {
        super(name,
                buyValue,
                sellValue,
                isConsumable);
        this.hpgain = hpgain;
        this.mpgain = mpgain;
    }

    // ----------------------------------------------------------------------
    // Setter
    // ----------------------------------------------------------------------

    public int gethpgain() {
        return hpgain;
    }

    public int getmpgain() {
        return mpgain;
    }
}
