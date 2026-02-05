package com.alex.entities;

import com.alex.items.Armor;
import com.alex.items.Weapon;

import java.util.Random;

public class Mage extends PlayerCharacter {

    Random random = new Random();
    public Mage(String name,
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
        int dmg = random.nextInt(7,8) + 1 + wb;
        return dmg;
    }

    @ Override
    public int sattack()
    {
        int wb = weapon.getWeaponbonus();
        int dmg = 0;
        int hit = random.nextInt(0, 100) + 1;
        if (hit < 15)
        {
            dmg = random.nextInt(44,54) + 1 + (wb / 2);
            System.out.println("Critical hit!");
            return dmg;
        }
        if (hit < 80)
        {
            dmg = random.nextInt(29,36) + 1 + (wb / 2);
            return dmg;
        }
        System.out.println("Attack has missed!");
        return dmg;
    }
}
