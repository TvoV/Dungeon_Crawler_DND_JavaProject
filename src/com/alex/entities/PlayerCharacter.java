package com.alex.entities;

import com.alex.items.Armor;
import com.alex.items.Weapon;
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
                    random = new Random();
    }

    // ----------------------------------------------------------------------
    // Methods
    // ----------------------------------------------------------------------

    public int attack()
    {
        int dmg = random.nextInt(15,20) + 1;
        return dmg;
    }

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
