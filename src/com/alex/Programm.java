package com.alex;
import com.alex.entities.*;
import com.alex.items.Armor;
import com.alex.items.Weapon;

import java.util.Random;
import java.util.Scanner;

public class Programm {
    public static void main(String[] args) {

        // ----------------------------------------------------------------------
        // Objekt Initialisierung
        // ----------------------------------------------------------------------

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String characterName = "";
        PlayerCharacter character = null;
        Enemy enemy = null;

        // ----------------------------------------------------------------------
        // Weapon Objects (Strength Weapons)
        // ----------------------------------------------------------------------

        Weapon strweapon1 = new Weapon("Chipped Sword", 4, 1, false, 4, 0);
        Weapon strweapon2 = new Weapon("Rusty Sword", 8, 2, false, 6, 2);
        Weapon strweapon3 = new Weapon("Broadsword", 12, 3, false, 10, 4);
        Weapon strweapon4 = new Weapon("Claymore", 16, 4, false, 12, 8);

        Weapon strweapon5 = new Weapon("Old Mace", 4, 1, false, 4, 0);
        Weapon strweapon6 = new Weapon("Flail", 8, 2, false, 8, 0);
        Weapon strweapon7 = new Weapon("Warhammer", 12, 3, false, 10, 4);
        Weapon strweapon8 = new Weapon("Morningstar", 16, 4, false, 12, 6);

        // ----------------------------------------------------------------------
        // Weapon Objects (Dexterity Weapons)
        // ----------------------------------------------------------------------

        Weapon dexweapon1 = new Weapon("Dull Knife", 4, 1, false, 4, 0);
        Weapon dexweapon2 = new Weapon("Dagger", 8, 2, false, 6, 2);
        Weapon dexweapon3 = new Weapon("Stiletto", 12, 3, false, 8, 4);
        Weapon dexweapon4 = new Weapon("Ripper", 16, 4, false, 10, 6);

        // ----------------------------------------------------------------------
        // Weapon Objects (Magic Weapons)
        // ----------------------------------------------------------------------

        Weapon intweapon1 = new Weapon("Stick", 4, 1, false, 2, 0);
        Weapon intweapon2 = new Weapon("Spellbook", 8, 2, false, 4, 2);
        Weapon intweapon3 = new Weapon("Wand", 12, 3, false, 8, 4);
        Weapon intweapon4 = new Weapon("Grimoire", 16, 4, false, 8, 6);

        // ----------------------------------------------------------------------
        // Arrays of Objects (Weapons)
        // ----------------------------------------------------------------------

        Weapon[] strweapons = {strweapon1, strweapon2, strweapon3, strweapon4, strweapon5, strweapon6, strweapon7, strweapon8};
        Weapon[] dexweapons = {dexweapon1, dexweapon2, dexweapon3, dexweapon4};
        Weapon[] intweapons = {intweapon1, intweapon2, intweapon3, intweapon4};

        // ----------------------------------------------------------------------
        // Armor Objects (Plate Armor)
        // ----------------------------------------------------------------------

        Armor heavyarmor1 = new Armor("Old Chestplate", 4, 1, false, 4, 0);
        Armor heavyarmor2 = new Armor("Sturdy Plate", 8, 2, false, 8, 2);
        Armor heavyarmor3 = new Armor("Berserker Armor", 20, 5, false, 12, 8);
        Armor heavyarmor4 = new Armor("Divine Vestige", 20, 5, false, 20, 0);

        // ----------------------------------------------------------------------
        // Armor Objects (Leather Armor)
        // ----------------------------------------------------------------------

        Armor mediumarmor1 = new Armor("Worn Leather", 4, 1, false, 2, 0);
        Armor mediumarmor2 = new Armor("Reinforced Leather", 8, 2, false, 6, 4);
        Armor mediumarmor3 = new Armor("Studded Leather", 16, 4, false, 12, 6);
        Armor mediumarmor4 = new Armor("Combat Suit", 20, 5, false, 16, 8);

        // ----------------------------------------------------------------------
        // Armor Objects (Cloth Armor)
        // ----------------------------------------------------------------------

        Armor lightarmor1 = new Armor("Tattered Cloak", 4, 1, false, 2, 2);
        Armor lightarmor2 = new Armor("Robes", 8, 2, false, 4, 4);
        Armor lightarmor3 = new Armor("Padded Garments", 20, 5, false, 6, 8);
        Armor lightarmor4 = new Armor("Archmage Heraldry", 20, 5, false, 8, 12);

        // ----------------------------------------------------------------------
        // Arrays of Objects (Armors)
        // ----------------------------------------------------------------------

        Armor[] heavyarmors = {heavyarmor1, heavyarmor2, heavyarmor3, heavyarmor4};
        Armor[] mediumarmors = {mediumarmor1, mediumarmor2, mediumarmor3, mediumarmor4};
        Armor[] lightarmors = {lightarmor1, lightarmor2, lightarmor3, lightarmor4};


        // ----------------------------------------------------------------------
        // Charakter Auswahl Spieler
        // ----------------------------------------------------------------------

        do {
            System.out.println("Select your Character: 1. Warrior, 2. Mage, 3. Rogue, 4.Cleric");
            int select = Integer.valueOf( scanner.nextLine());

            if (select == 1) {
                System.out.println("You have selected the Warrior.");
                character = new Warrior(
                        characterName,
                        100,
                        100,
                        100,
                        20,
                        20,
                        20,
                        24,
                        16,
                        5,
                        25,
                        strweapons[0],
                        heavyarmors[0]
                );
            }
            if (select == 2) {
                System.out.println("You have selected the Mage.");
                character = new Mage(
                        characterName,
                        60,
                        60,
                        60,
                        100,
                        100,
                        100,
                        4,
                        12,
                        25,
                        15,
                        intweapons[0],
                        lightarmors[0]);
            }
            if (select == 3) {
                System.out.println("You have selected the Rogue.");
                character = new Rogue(
                        characterName,
                        72,
                        72,
                        72,
                        48,
                        48,
                        48,
                        8,
                        24,
                        12,
                        18,
                        dexweapons[0],
                        mediumarmors[0]);
            }
            if (select == 4) {
                System.out.println("You have selected the Cleric.");
                character = new Cleric(
                        characterName,
                        92,
                        92,
                        92,
                        64,
                        64,
                        64,
                        20,
                        4,
                        16,
                        23,
                        strweapons[5],
                        heavyarmors[0]);
            }
        } while (character == null);

        System.out.println("Name your Character: ");
        characterName = scanner.nextLine();

        System.out.println("");
        System.out.println(characterName + " wields " + character.getWeapon().getName() + " & " + character.getArmor().getName());

        // ----------------------------------------------------------------------
        // Encounter randomizer
        // ----------------------------------------------------------------------

        int select = random.nextInt(4) + 1;
        if (select == 1) {
            System.out.println("");
            System.out.println(characterName + " steps further into the hallway.");
            System.out.println("");
        }
        if (select == 2) {
            System.out.println("");
            System.out.println(characterName + " steps further into the room.");
            System.out.println("");
        }
        if (select == 3) {
            System.out.println("");
            System.out.println("Something lurches at " + characterName + " from the dark.");
            System.out.println("");
        }
        if (select == 4) {
            System.out.println("");
            System.out.println(characterName + " hears a noise from behind.");
            System.out.println("");
        }

        // ----------------------------------------------------------------------
        // Gegner Auswahl Computer
        // ----------------------------------------------------------------------


        select = random.nextInt(6) + 1;
        if (select == 1) {
            System.out.println(characterName + " has encountered a Skeleton.");
            System.out.println("");
            enemy = new Skeleton("Skeleton",
                    40,
                    40,
                    40,
                    0,
                    0,
                    0);
        }
        if (select == 2) {
            System.out.println(characterName + " has encountered a Goblin.");
            System.out.println("");
            enemy = new Goblin("Goblin",
                    45,
                    45,
                    45,
                    10,
                    10,
                    10);
        }
        if (select == 3) {
            System.out.println(characterName + " has encountered a Troglodyte.");
            System.out.println("");
            enemy = new Troglodyte("Troglodyte",
                    60,
                    60,
                    60,
                    5,
                    5,
                    5);
        }
        if (select == 4) {
            System.out.println(characterName + " has encountered a Master Thief.");
            System.out.println("");
            enemy = new Master_Thief("Master Thief",
                    50,
                    50,
                    50,
                    15,
                    15,
                    15);
        }
        if (select == 5) {
            System.out.println(characterName + " has encountered a Necromancer.");
            System.out.println("");
            enemy = new Necromancer("Necromancer",
                    40,
                    40,
                    40,
                    25,
                    25,
                    25);
        }
        if (select == 6) {
            System.out.println(characterName + " has encountered an Oathbreaker.");
            System.out.println("");
            enemy = new Oathbreaker("Oathbreaker",
                    80,
                    80,
                    80,
                    20,
                    20,
                    20);
        }

        // ----------------------------------------------------------------------
        // Kampfsystem Schleife
        // ----------------------------------------------------------------------

        int dmg = 0;
        int def = character.getArmor().getDefensestat();
        for (int i = 0; i < 1; i++) {
            do {
                int choice = enemy.pickattack();
                if (choice == 1 || choice == 2) {
                    enemy.attack();
                    dmg = enemy.attack() - def;
                    if(dmg <= 0){
                        dmg = 0;
                    }
                    character.setRestHp(character.getHp() - dmg);
                    System.out.println("The " + enemy.getName() + " did " + dmg + " points of Damage to " + characterName + "!");
                    character.setHp(character.getRestHp());
                    if (enemy.getHp() > 0) {
                        System.out.println(characterName + " has " + character.getHp() + " Health left!");
                    } else {
                        System.out.println(characterName + " has no more HP left!");
                    }
                } else {
                    if (enemy.getMp() >= 5) {
                        enemy.sattack();
                        dmg = enemy.attack() - def;
                        if(dmg <= 0){
                            dmg = 0;
                        }
                        character.setRestHp(character.getHp() - dmg);
                        System.out.println("The " + enemy.getName() + " did " + dmg + " points of Damage to " + characterName + "!");
                        character.setHp(character.getRestHp());
                        if (enemy.getHp() > 0) {
                            System.out.println(characterName + " has " + character.getHp() + " Health left!");
                        } else {
                            System.out.println(characterName + " has no more Health left!");
                        }
                        enemy.setRestMp(enemy.getMp() - 5);
                        enemy.setMp(enemy.getRestMp());
                    }
                    else {
                        System.out.println("The " + enemy.getName() + " is out of Mana and attacks " + characterName + "!");
                        enemy.attack();
                    }
                }

                System.out.println("Select Attack: (1 for base), (2 for special)");

                int attack = scanner.nextInt();

                if (attack == 1) {
                    dmg = character.attack();
                    enemy.setRestHp(enemy.getHp() - dmg);
                    System.out.println(characterName + " did " + dmg + " points of Damage to the " + enemy.getName() +"!");
                    enemy.setHp(enemy.getRestHp());
                    if (enemy.getHp() > 0) {
                        System.out.println("The " + enemy.getName() + " has " + enemy.getHp() + " Health left!");
                    } else {
                        System.out.println("The " + enemy.getName() + " has no more Health left!");
                    }
                } else {
                    if (character.getMp() >= 5){
                        dmg = character.sattack();
                        enemy.setRestHp(enemy.getHp() - dmg);
                        System.out.println(characterName + " did " + dmg + " points of Damage to the " + enemy.getName() +"!");
                        enemy.setHp(enemy.getRestHp());
                        if (enemy.getHp() > 0) {
                            System.out.println("The " + enemy.getName() + " has " + enemy.getHp() + " Health left!");
                        } else {
                            System.out.println("The " + enemy.getName() + " has no more HP left!");
                        }
                        character.setRestMp(character.getMp() - 5);
                        character.setMp(character.getRestMp());
                        System.out.println("");
                        System.out.println(characterName + " has " + character.getMp() + " of " + character.getStartMp() + " Mana left to use.");
                        System.out.println("");
                    }
                    else {
                        System.out.println("");
                        System.out.println(characterName + " has " + character.getMp() + " left to use.");
                        System.out.println("");
                    }
                }

                if (enemy.getHp() <= 0) {
                    System.out.println("The " + enemy.getName() + " has been defeated.");
                    System.out.println(characterName + " wins!");
                    System.out.println("");
                    System.out.println(" #################### Battle has finished! ##################### ");
                    System.out.println("");
                    break;
                }

                if (character.getRestHp() <= 0) {
                    System.out.println(character.getName() + " has perished.");
                    System.out.println(character.getName() + " lost!");
                    System.out.println("");
                    System.out.println(" #################### Battle has finished! ##################### ");
                    System.out.println("");
                    break;
                }

            } while (character.getHp() > 0 || enemy.getHp() > 0);
        }
        scanner.close();
    }
}