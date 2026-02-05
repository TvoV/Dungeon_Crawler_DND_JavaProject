package com.alex.entities;

import java.util.Random;

public class Oathbreaker extends Enemy {
    Random random = new Random();
    public Oathbreaker(String name,
                       int hp,
                       int startHp,
                       int restHp,
                       int mp,
                       int startMp,
                       int restMp) {
        super(name,
                hp,
                startHp,
                restHp,
                mp,
                startMp,
                restMp);
    }

    @ Override
    public int attack()
    {
        int dmg = random.nextInt(15,20) + 1;
        return dmg;
    }

    @ Override
    public int sattack()
    {
        int dmg = 0;
        int hit = random.nextInt(0, 100) + 1;
        if (hit < 15)
        {
            dmg = random.nextInt(30,36) + 1;
            return dmg;
        }
        if (hit < 90)
        {
            dmg = random.nextInt(20,28) + 1;
            return dmg;
        }
        System.out.println("Attack has missed!");
        return dmg;
    }
}
