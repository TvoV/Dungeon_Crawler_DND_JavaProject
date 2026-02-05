package com.alex.entities;

import java.util.Random;

public class Necromancer extends Enemy {
    Random random = new Random();
    public Necromancer(String name,
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
        int dmg = random.nextInt(6,8) + 1;
        return dmg;
    }

    @ Override
    public int sattack()
    {
        int dmg = 0;
        int hit = random.nextInt(0, 100) + 1;
        if (hit < 70)
        {
            dmg = random.nextInt(20,30) + 1;
            return dmg;
        }
        System.out.println("Attack has missed!");
        return dmg;
    }
}