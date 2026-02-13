package com.alex;

import com.alex.engines.CombatEngine;
import com.alex.engines.EncounterManager;
import com.alex.engines.EnemyFactory;
import com.alex.engines.PlayerFactory;
import com.alex.entities.enemies.Enemy;
import com.alex.entities.player.PlayerCharacter;
import com.alex.items.ItemRegistry;
import java.util.Scanner;

public class Main_App {
    public static void main(String[] args) {
    /*

    // ================================================================================
    // OLD TERMINAL BATTLE SYSTEM
    // ================================================================================

        Scanner scanner = new Scanner(System.in);

        // Item Initialisierung
        ItemRegistry.initialize();

        // Name your Character
        System.out.println("Name your Character: ");
        String characterName = scanner.nextLine();

        PlayerCharacter character = null;

        // Character Selection Loop
        while (character == null) {
            System.out.println("Select your Character: 1. Warrior, 2. Rogue, 3. Mage, 4. Cleric");
            int select = Integer.valueOf(scanner.nextLine());

            character = PlayerFactory.createCharacter(select, characterName);

            if(character == null){
                System.out.println("Invalid choice. Choose again...");
            }
            else {
                System.out.println("You have selected the " + character.getName() + " the " + character.getClass().getSimpleName());
            }
        }

        // Check Stats and Gear
        System.out.println("Would you like to check your stats? 1. Yes, 2. No");
        if (Integer.valueOf(scanner.nextLine()) == 1) {
            character.checkstats();
        }
        System.out.println("Would you like to check your gear? 1. Yes, 2. No");
        if (Integer.valueOf(scanner.nextLine()) == 1) {
            System.out.println(character.getName() + " wields " + character.getWeapon().getName() + " & " + character.getArmor().getName());
        }

        System.out.println("Ready to enter the dungeon? 1. Yes, 2. No");
        int start = Integer.valueOf(scanner.nextLine());

        if(start == 1){
            System.out.println("");
            System.out.println("You enter the Dungeon.");

            // Type of encounter message gets selected
            EncounterManager.printRandomEvent(character.getName());

            // Random enemy gets selected
            Enemy enemy = EnemyFactory.getRandomEnemy();
            System.out.println("A " + enemy.getName() + " appears in front of " + character.getName() + "!");

            while(character.getHp() > 0 && enemy.getHp() > 0){
                System.out.println("\n --- Your Move ---");
                System.out.println("Select your attack: 1. Base Attack, 2. Special Attack");
                int action = Integer.parseInt(scanner.nextLine());

                CombatEngine.battleTurn(character, enemy, action);
            }

            if(character.getHp() <= 0) {
                System.out.println("You perished!");
            }
            else {
                System.out.println("Victory!");
                System.out.println("The enemy dropped some loot!");
                System.out.println("The battle has finished!");
            }
        }
        else {
            System.out.println("You fled like a coward.");
        }
    */
    }
}

