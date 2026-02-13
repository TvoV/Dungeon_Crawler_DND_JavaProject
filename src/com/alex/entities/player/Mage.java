package com.alex.entities.player;

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
        this.setCurrentState("Attack");
        int as = weapon.getAttackstat();
        int wb = weapon.getWeaponbonus();
        int dmg = random.nextInt(7,9) + as + (wb / 2);
        return dmg;
    }

    @ Override
    public int sattack()
    {
        this.setCurrentState("Fireball");
        int as = weapon.getAttackstat();
        int wb = weapon.getWeaponbonus();
        int dmg = 0;
        int hit = random.nextInt(0, 101) + 1;
        if (hit <= 15)
        {
            dmg = random.nextInt(44,55) + as + (wb / 2);
            this.setCurrentState("critical Fireball");
            return dmg;
        }
        else if (hit <= 80)
        {
            dmg = random.nextInt(29,37) + as + (wb / 2);
            return dmg;
        }
        return dmg;
    }

    @ Override
    public int getIntelligence() {
        return super.getIntelligence() + this.getWeapon().getWeaponbonus();
    }

    @ Override
    public int getConstitution() {
        return super.getConstitution() + this.getArmor().getArmorbonus();
    }

    @Override
    public int getStartHp() {
        return super.getStartHp() + this.getArmor().getArmorbonus() * 4;
    }

    @Override
    public int getStartMp() {
        return super.getStartMp() + this.getWeapon().getWeaponbonus() * 4;
    }

    @ Override
    public int getHp() {
        if (armor != null)
            return super.getHp() + this.getArmor().getArmorbonus() * 4;
        return super.getHp();
    }

    public int getMp() {
        if (weapon != null)
            return super.getMp() + this.getWeapon().getWeaponbonus() * 4;
        return super.getMp();
    }
}
