package com.alex.items;

public class Weapon extends Item{

    // ----------------------------------------------------------------------
    // Attributes
    // ----------------------------------------------------------------------

    private int attackstat = 0;
    private int weaponbonus = 0;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Weapon(String name,
                 int buyValue,
                 int sellValue,
                 boolean isConsumable,
                 int attackstat,
                 int weaponbonus) {
        super(name,
                buyValue,
                sellValue,
                isConsumable);
        this.attackstat = attackstat;
        this.weaponbonus = weaponbonus;
    }

    public int getWeaponbonus() {
        return weaponbonus;
    }

    public int getAttackstat() {
        return attackstat;
    }

    public void setAttackstat(int attackstat) {
        this.attackstat = attackstat;
    }
}
