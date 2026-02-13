package com.alex.entities.player;

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
        this.setCurrentState("Attack");
        int as = weapon.getAttackstat();
        int wb = weapon.getWeaponbonus();
        int dmg = 0;
        int hit = random.nextInt(0, 101) + 1;
        if (hit <= 20)
        {
            dmg = random.nextInt(18,26) + as + (wb / 2);
            this.setCurrentState("Critical hit");
            return dmg;
        }
        else if (hit <= 100)
        {
            dmg = random.nextInt(12,17) + as + (wb / 2);
            return dmg;
        }
        return dmg;
    }

    @ Override
    public int sattack()
    {
        this.setCurrentState("Shadow Strike");
        int as = weapon.getAttackstat();
        int wb = weapon.getWeaponbonus();
        int dmg = 0;
        int hit = random.nextInt(0, 101) + 1;
        if (hit <= 25)
        {
            dmg = random.nextInt(36,48) + 1 + as + (wb / 2);
            this.setCurrentState("critical Shadow Strike");
            return dmg;
        }
        else if (hit <= 90)
        {
            dmg = random.nextInt(24,32) + 1 + as + (wb / 2);
            return dmg;
        }
        return dmg;
    }

    @ Override
    public int getDexterity() {
        return super.getDexterity() + this.getWeapon().getWeaponbonus();
    }

    @ Override
    public int getConstitution() {
        return super.getConstitution() + this.getArmor().getArmorbonus();
    }

    @Override
    public int getStartHp() {
        return super.getStartHp() + this.getArmor().getArmorbonus() * 4;
    }

    @ Override
    public int getHp() {
        if (armor != null)
            return super.getHp() + this.getArmor().getArmorbonus() * 4;
        return super.getHp();
    }
}
