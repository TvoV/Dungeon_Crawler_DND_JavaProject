package com.alex.engines;

import com.alex.entities.Entity;
import com.alex.entities.enemies.*;
import java.util.Random;

    // -------------------------------------------------------------------------
    // Logik für die Abfolge der Kampfinstanzen
    // -------------------------------------------------------------------------

public class CombatEngine {

    // Attack Method -> liefert String an das CombatWindow
    public static String handleAttack(Entity attacker, Entity target, int action){
        int rawDmg = 0;
        String message = attacker.getCurrentState();

        // Mana Check und DMG Berechnung
        if(action == 1) {
            rawDmg = attacker.attack();
            message = attacker.getCurrentState();
        }
        else {
            if(attacker.getMp() >= 5) {
                rawDmg = attacker.sattack();
                attacker.setMp(attacker.getMp() - 5);
                message = attacker.getCurrentState();
            }
            else {
                rawDmg = attacker.attack();
                message = "Normal Attack";
                message = attacker.getCurrentState();
            }
        }
        int finalDmg = target.takeDamage(rawDmg);

        if (finalDmg <= 0 && rawDmg > 0) {
            return attacker.getName() + "'s " + message + " failed to hit!";
        }

        return attacker.getName() + " attacks " + target.getName() + " with a " + message + " for " + finalDmg + " damage!";
    }

}

