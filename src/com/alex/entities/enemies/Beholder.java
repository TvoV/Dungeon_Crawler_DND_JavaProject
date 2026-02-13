package com.alex.entities.enemies;

import java.util.Random;
public class Beholder extends Enemy{

    Random random = new Random();
    public Beholder(String name,
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
        int dmg = 0;
        int hit = random.nextInt(0, 101) + 1;
        if (hit <= 10)
        {
            dmg = random.nextInt(25,31);
            this.setCurrentState("Critical hit");
            return dmg;
        }
        if (hit <= 100)
        {
            dmg = random.nextInt(19,25);
            return dmg;
        }
        return dmg;
    }

    @ Override
    public int sattack()
    {
        this.setCurrentState("Desintegration Beam");
        int dmg = 0;
        int hit = random.nextInt(0, 101) + 1;
        if (hit <= 15)
        {
            dmg = random.nextInt(40,51);
            this.setCurrentState("critical Desintegration Beam");
            return dmg;
        }
        if (hit <= 75)
        {
            dmg = random.nextInt(30,36);
            return dmg;
        }
        return dmg;
    }
}
