package com.alex.entities.enemies;

import java.util.Random;

public class Gnoll extends Enemy {

    Random random = new Random();
    public Gnoll(String name,
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

        @ Override
        public int sattack()
        {
            this.setCurrentState("Multistab!");
            int dmg = 0;
            int hit = random.nextInt(0, 101) + 1;
            if (hit <= 75)
            {
                dmg = random.nextInt(12,16);
                return dmg;
            }
            return dmg;
        }
}
