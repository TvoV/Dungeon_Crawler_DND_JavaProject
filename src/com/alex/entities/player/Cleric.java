package com.alex.entities.player;

import com.alex.items.Armor;
import com.alex.items.Weapon;

import java.util.Random;

public class Cleric extends PlayerCharacter {

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

    // -------------------------------------------------------------------------
    // Individuelle Schadenberechnung der normalen Attacke
    // -------------------------------------------------------------------------

    @ Override
    public int attack()
    {
        this.setCurrentState("Attack");
        int as = weapon.getAttackstat();
        int wb = weapon.getWeaponbonus();
        int dmg = random.nextInt(10,15) + as + (wb / 2);
        return dmg;
    }

    // -------------------------------------------------------------------------
    // Individuelle Schadenberechnung der Spezial Attacke
    // -------------------------------------------------------------------------

    @ Override
    public int sattack()
    {
        this.setCurrentState("Guiding Bolt");
        int as = weapon.getAttackstat();
        int wb = weapon.getWeaponbonus();
        int dmg = 0;
        int hit = random.nextInt(0, 101) + 1;
        if (hit <= 15)
        {
            dmg = random.nextInt(24,33) + as + (wb / 2);
            this.setCurrentState("critical Guiding Bolt");
            return dmg;
        }
        else if (hit <= 85)
        {
            dmg = random.nextInt(16,25) + as + (wb / 2);
            return dmg;
        }
        return dmg;
    }

    @ Override
    public int getStrength() {
        return super.getStrength() + this.getWeapon().getWeaponbonus();
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

