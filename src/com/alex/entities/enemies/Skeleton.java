package com.alex.entities.enemies;

import java.util.Random;

public class Skeleton extends Enemy {

    Random random = new Random();
    public Skeleton(String name,
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
        int dmg = random.nextInt(8,11);
        return dmg;
    }

}
