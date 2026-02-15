package com.alex.entities.enemies;

import java.util.Random;

public class Necromancer extends Enemy {
    Random random = new Random();
    public Necromancer(String name,
                       int hp,
                       int startHp,
                       int restHp,
                       int mp,
                       int startMp,
                       int restMp,
                       int defense) {
        super(name,
                hp,
                startHp,
                restHp,
                mp,
                startMp,
                restMp,
                defense);
    }

    // -------------------------------------------------------------------------
    // Individuelle Schadenberechnung der normalen Attacke
    // -------------------------------------------------------------------------

    @ Override
    public int attack()
    {
        this.setCurrentState("Attack");
        int dmg = random.nextInt(6,11);
        return dmg;
    }

    // -------------------------------------------------------------------------
    // Individuelle Schadenberechnung der Spezial Attacke
    // -------------------------------------------------------------------------

    @ Override
    public int sattack()
    {
        this.setCurrentState("Eldritch Blast!");
        int dmg = 0;
        int hit = random.nextInt(0, 101);
        if (hit <= 70)
        {
            dmg = random.nextInt(24,31);
            return dmg;
        }
        return dmg;
    }
}