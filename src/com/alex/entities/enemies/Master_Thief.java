package com.alex.entities.enemies;

import java.util.Random;

public class Master_Thief extends Enemy {
    Random random = new Random();
    public Master_Thief(String name,
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
            dmg = random.nextInt(12,17);
            this.setCurrentState("Critical hit");
            return dmg;
        }
        if (hit <= 100)
        {
            dmg = random.nextInt(8,13);
            return dmg;
        }
        return dmg;
    }

    @ Override
    public int sattack()
    {
        this.setCurrentState("Frenzy Slash");
        int dmg = 0;
        int hit = random.nextInt(0, 101) + 1;
        if (hit <= 15)
        {
            dmg = random.nextInt(24,31);
            this.setCurrentState("critical Frenzy Slash");
            return dmg;
        }
        if (hit <= 80)
        {
            dmg = random.nextInt(16,21);
            return dmg;
        }

        return dmg;
    }
}
