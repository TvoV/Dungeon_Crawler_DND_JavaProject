package com.alex.engines;

import com.alex.entities.Entity;
import com.alex.entities.player.PlayerCharacter;
import com.alex.entities.enemies.*;
import java.util.Random;

public class CombatEngine {

    private Random random = new Random();

    /*
    // ================================================================================
    // OLD TERMINAL BATTLE METHOD
    // ================================================================================

    public static void battleTurn(PlayerCharacter character, Enemy enemy, int action) {

        // Enemy attack selection method
        int choice = enemy.pickAttack();
        handleAttack(enemy, character, (choice <= 2) ? 1 : 2);

        //Checks if Player Character is still alive
        if(character.getHp() <= 0) {
            System.out.println(character.getName() + " was defeated. Your journey ends here...");
            return;
        }

        handleAttack(character, enemy, action);
        battleStatus(character, enemy);

        // Checks if enemy is still alive
        if(enemy.getHp() <= 0) {
            System.out.println("The " + enemy.getName() + " was defeated!");
            return;
        }
    }
    */

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

    /*
    // ================================================================================
    // OLD TERMINAL STATUS OUTPUT
    // ================================================================================

    private static void battleStatus(PlayerCharacter character, Enemy enemy) {
        System.out.println("==========================================================\n");
        System.out.println(String.format("%-15s | HP: %d/%d | MP: %d/%d",
                character.getName().toUpperCase(),
                character.getHp(),
                character.getStartHp(),
                character.getMp(),
                character.getStartMp()));
        System.out.println(String.format("%-15s | HP: %d/%d",
                enemy.getName().toUpperCase(),
                enemy.getHp(),
                enemy.getStartHp()));
        System.out.println("");
        System.out.println("==========================================================\n");
    }
    */
}

