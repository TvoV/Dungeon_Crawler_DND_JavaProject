package com.alex.entities.player;

import com.alex.entities.Entity;
import com.alex.items.Armor;
import com.alex.items.Item;
import com.alex.items.Potion;
import com.alex.items.Weapon;

import javax.swing.*;
import java.util.Random;


public class PlayerCharacter extends Entity {

    // ----------------------------------------------------------------------
    // Attributes
    // ----------------------------------------------------------------------

    private int strength = 0;
    private int dexterity = 0;
    private int intelligence = 0;
    private int constitution = 0;
    private Random random;
    public Weapon weapon;
    public Armor armor;
    private int inventoryCount = 0;
    public Item[] inventory = new Item[64];

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PlayerCharacter(String name,
                           int hp,
                           int startHp,
                           int restHp,
                           int mp,
                           int startMp,
                           int restMp,
                           int strength,
                           int dexterity,
                           int intelligence,
                           int constitution,
                           Weapon weapon,
                           Armor armor
                     ){
                super(
                    name,
                    hp,
                    startHp,
                    restHp,
                    mp,
                    startMp,
                    restMp);

                    this.strength = strength;
                    this.dexterity = dexterity;
                    this.intelligence = intelligence;
                    this.constitution = constitution;
                    this.weapon = weapon;
                    this.armor = armor;
                    this.inventoryCount = 0;
                    this.inventory = new Item[64];
                    random = new Random();
    }

    // ----------------------------------------------------------------------
    // Methods
    // ----------------------------------------------------------------------

    // Methode zum Aufnehmen von Loot ins Inventory
    public void addToInventory(Item item) {
        if (inventoryCount < inventory.length) {
            inventory[inventoryCount] = item;
            inventoryCount++;
        }
    }

    // Methode zum Ausrüsten einer Waffe
    public Weapon equipWeapon(Weapon weapon){
        this.weapon = weapon;
        return this.weapon;
    }

    // Methode zum Ausrüsten einer Rüstung
    public Armor equipArmor(Armor armor){
        this.armor = armor;
        return this.armor;
    }

    @Override
    public int attack()
    {
        int dmg = random.nextInt(15,20) + 1;
        return dmg;
    }

    @Override
    public int sattack()
    {
        int dmg = 0;
        int hit = random.nextInt(0, 100) + 1;
        if (hit < 66)
        {
            dmg = random.nextInt(30,40) + 1;
            return dmg;
        }
        System.out.println("The Attack has missed!");
        return dmg;
    }

    public void checkstats(){
         JOptionPane.showMessageDialog(null,
                 "Your current stats are: \n\n"
                            + getHp() + " of " + getStartHp() + " Health\n"
                            + getMp() + " of " + getStartMp() + " Mana\n"
                            + getStrength() + " Stength\n"
                            + getDexterity() + " Dexterity\n"
                            + getIntelligence() + " Intelligence\n"
                            + getConstitution() + " Constitution\n"
                            + weapon.getName() + " : equipped Weapon\n"
                            + armor.getName() + " : equipped Armor\n",
                 "Current Stats", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public int getDefense() {
        if(this.armor != null) {
            return this.armor.getDefensestat();
        }
        return 0;
    }

    // Methode zum Verzehren von Tränken

    public void usePotion(Potion potion) {

        // Health Potion
        if (potion.gethpgain() > 0) {
            this.hp += potion.gethpgain();
            if (this.hp > this.startHp) this.hp = this.startHp; // Cap bei Max-HP
            JOptionPane.showMessageDialog(null,
                    "Healed for " + potion.gethpgain() + " HP.");
        }
        // Mana Potion
        if (potion.getmpgain() > 0) {
            this.mp += potion.getmpgain();
            if (this.mp > this.startMp) this.mp = this.startMp; // Cap bei Max-MP
            JOptionPane.showMessageDialog(null,
                    "Restored " + potion.getmpgain() + " MP.");
        }
    }

    // ----------------------------------------------------------------------
    // Getter
    // ----------------------------------------------------------------------

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getConstitution() {
        return constitution;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    // ----------------------------------------------------------------------
    // Setter
    // ----------------------------------------------------------------------

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}
