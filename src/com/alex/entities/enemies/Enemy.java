package com.alex.entities.enemies;

import com.alex.entities.Entity;

import java.util.Random;

public class Enemy extends Entity {

    // ----------------------------------------------------------------------
    // Attributes
    // ----------------------------------------------------------------------

    private Random random;
    private int defense;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    public Enemy(String name,
                           int hp,
                           int startHp,
                           int restHp,
                           int mp,
                           int startMp,
                           int restMp,
                           int defense
    ){
        super(
                name,
                hp,
                startHp,
                restHp,
                mp,
                startMp,
                restMp);
        this.defense = defense;
        random = new Random();
    }

    // ----------------------------------------------------------------------
    // Methods
    // ----------------------------------------------------------------------

    public int pickAttack()
    {
        int choice = 0;
        choice = random.nextInt(1, 4);
        return choice;
    }

    @Override
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

    public int getDefense() {
        return this.defense;
    }

}
