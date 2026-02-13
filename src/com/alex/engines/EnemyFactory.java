package com.alex.engines;

import com.alex.entities.enemies.*;

import java.util.Random;

public class EnemyFactory {

    private static final Random random = new Random();

    public static Enemy getRandomEnemy(){
        int select = random.nextInt(6);

        return switch (select) {
            case 0 -> new Skeleton("Skeleton",
                    40,
                    40,
                    40,
                    0,
                    0,
                    0,
                    0);
            case 1 -> new Gnoll("Gnoll",
                    45,
                    45,
                    45,
                    10,
                    10,
                    10,
                    0);
            case 2 -> new Troglodyte("Troglodyte",
                    60,
                    60,
                    60,
                    5,
                    5,
                    5,
                    0);
            case 3 -> new Master_Thief("Master_Thief",
                    50,
                    50,
                    50,
                    15,
                    15,
                    15,
                    0);
            case 4 -> new Necromancer("Necromancer",
                    40,
                    40,
                    40,
                    25,
                    25,
                    25,
                    0);
            case 5 -> new Oathbreaker("Oathbreaker",
                    80,
                    80,
                    80,
                    20,
                    20,
                    20,
                    5);
            default -> null;
        };
    }
    public static Enemy getBoss() {
        return new Beholder("Beholder",
                180,
                180,
                180,
                100,
                100,
                100,
                10);
    }
}

