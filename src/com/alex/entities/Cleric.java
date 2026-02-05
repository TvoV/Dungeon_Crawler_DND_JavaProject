package com.alex.entities;

import com.alex.items.Armor;
import com.alex.items.Weapon;

import java.util.Random;

public class Cleric extends PlayerCharacter{

    Random random = new Random();
    public Cleric(String name,
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

    public Weapon equipWeapon(Weapon weapon){
        this.weapon = weapon;
        return this.weapon;
    }

    public Armor equipArmor(Armor armor){
        this.armor = armor;
        return this.armor;
    }

    @ Override
    public int attack()
    {
        int as = weapon.getAttackstat();
        int wb = weapon.getWeaponbonus();
        int dmg = random.nextInt(10,14) + 1 + as + (wb / 2);
        return dmg;
    }

    @ Override
    public int sattack()
    {
        int as = weapon.getAttackstat();
        int wb = weapon.getWeaponbonus();
        int dmg = 0;
        int hit = random.nextInt(0, 100) + 1;
        if (hit < 15)
        {
            dmg = random.nextInt(24,32) + 1 + as + (wb / 2);
            System.out.println("Critical hit!");
            return dmg;
        }
        if (hit < 85)
        {
            dmg = random.nextInt(16,24) + 1 + as + (wb / 2);
            return dmg;
        }
        System.out.println("Attack has missed!");
        return dmg;
    }
}
