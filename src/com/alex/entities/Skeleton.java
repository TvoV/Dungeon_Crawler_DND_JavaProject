package com.alex.entities;

import java.util.Random;

public class Skeleton extends Enemy {

    Random random = new Random();
    public Skeleton(String name,
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
        int dmg = random.nextInt(8,10) + 1;
        return dmg;
    }

}
