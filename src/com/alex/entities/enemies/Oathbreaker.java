package com.alex.entities.enemies;

import java.util.Random;

public class Oathbreaker extends Enemy {
    Random random = new Random();
    public Oathbreaker(String name,
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

    @ Override
    public int attack()
    {
        this.setCurrentState("Attack");
        int dmg = random.nextInt(15,21);
        return dmg;
    }

    @ Override
    public int sattack()
    {
        this.setCurrentState("Divine Smite!");
        int dmg = 0;
        int hit = random.nextInt(0, 101) + 1;
        if (hit <= 15)
        {
            dmg = random.nextInt(30,37);
            return dmg;
        }
        if (hit <= 90)
        {
            dmg = random.nextInt(20,29);
            return dmg;
        }
        return dmg;
    }
}
