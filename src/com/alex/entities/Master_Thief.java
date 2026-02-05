package com.alex.entities;

import java.util.Random;

public class Master_Thief extends Enemy {
    Random random = new Random();
    public Master_Thief(String name,
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
        int dmg = 0;
        int hit = random.nextInt(0, 100) + 1;
        if (hit < 10)
        {
            dmg = random.nextInt(12,16) + 1;
            System.out.println("Critical hit!");
            return dmg;
        }
        if (hit < 100)
        {
            dmg = random.nextInt(8,12) + 1;
            return dmg;
        }
        return dmg;
    }

    @ Override
    public int sattack()
    {
        int dmg = 0;
        int hit = random.nextInt(0, 100) + 1;
        if (hit < 15)
        {
            dmg = random.nextInt(24,30) + 1;
            System.out.println("Critical hit!");
            return dmg;
        }
        if (hit < 80)
        {
            dmg = random.nextInt(16,20) + 1;
            return dmg;
        }
        System.out.println("Attack has missed!");
        return dmg;
    }
}
