package com.alex.items;

public class Armor extends Item{

    // ----------------------------------------------------------------------
    // Attributes
    // ----------------------------------------------------------------------

    private int defensestat = 0;
    private int armorbonus = 0;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Armor(String name,
                 int buyValue,
                 int sellValue,
                 boolean isConsumable,
                 int defensestat,
                 int armorbonus) {
        super(name,
                buyValue,
                sellValue,
                isConsumable);
        this.defensestat = defensestat;
        this.armorbonus = armorbonus;
    }

    public int getDefensestat() {
        return defensestat;
    }
    public int getArmorbonus() {
        return armorbonus;
    }

}
