package com.alex.entities;

import com.alex.items.Armor;
import com.alex.items.Weapon;

import java.util.Random;

public class Rogue extends PlayerCharacter {

    Random random = new Random();
    public Rogue(String name,
                 int hp,
                 int startHp,
                 int restHp,
                 int mp,
                 int startMp,
                 int restMp,
                 int strength,
                 int dexterity,
                 int intelligence,
                 int constitution,
                 Weapon weapon,
                 Armor armor) {
        super(name,
                hp,
                startHp,
                restHp,
                mp,
                startMp,
                restMp,
                strength,
                dexterity,
                intelligence,
                constitution,
                weapon,
                armor);
    }

    @ Override
    public int attack()
    {
        int wb = weapon.getWeaponbonus();
        int dmg = 0;
        int hit = random.nextInt(0, 100) + 1 + wb;
        if (hit < 20)
        {
            dmg = random.nextInt(18,24) + 1;
            System.out.println("Critical hit!");
            return dmg;
        }
        if (hit < 100)
        {
            dmg = random.nextInt(12,16) + 1;
            return dmg;
        }
        return dmg;
    }

    @ Override
    public int sattack()
    {
        int wb = weapon.getWeaponbonus();
        int dmg = 0;
        int hit = random.nextInt(0, 100) + 1;
        if (hit < 25)
        {
            dmg = random.nextInt(36,48) + 1;
            System.out.println("Critical hit!");
            return dmg;
        }
        if (hit < 90)
        {
            dmg = random.nextInt(24,32) + 1;
            return dmg;
        }
        System.out.println("Attack has missed!");
        return dmg;
    }
}
