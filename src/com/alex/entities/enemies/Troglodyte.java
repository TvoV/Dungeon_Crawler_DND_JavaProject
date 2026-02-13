package com.alex.entities.enemies;

import java.util.Random;

public class Troglodyte extends Enemy{

    Random random = new Random();
    public Troglodyte(String name,
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
        int dmg = random.nextInt(12,17);
        return dmg;
    }

    @ Override
    public int sattack()
    {
        this.setCurrentState("Poison Spit!");
        int dmg = 0;
        int hit = random.nextInt(0, 101) + 1;
        if (hit <= 75)
        {
            dmg = random.nextInt(16,25);
            return dmg;
        }
        return dmg;
    }
}
