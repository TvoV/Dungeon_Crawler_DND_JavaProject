package com.alex.items;

import com.alex.entities.player.*;

public class ItemRegistry {

    public static Weapon[] strWeapons;
    public static Weapon[] dexWeapons;
    public static Weapon[] intWeapons;

    public static Armor[] heavyArmors;
    public static Armor[] mediumArmors;
    public static Armor[] lightArmors;

    public static Potion[] potions;

    public static void initialize() {

        // ----------------------------------------------------------------------
        // Weapons
        // ----------------------------------------------------------------------

        // Strength Weapons
        strWeapons = new Weapon[] {
                new Weapon("Chipped Sword", 4, 1, false, 4, 0),
                new Weapon("Rusty Sword", 8, 2, false, 6, 2),
                new Weapon("Broadsword", 12, 3, false, 10, 4),
                new Weapon("Claymore", 16, 4, false, 12, 8),
                new Weapon("Old Mace", 4, 1, false, 4, 0),
                new Weapon("Flail", 8, 2, false, 8, 0),
                new Weapon("Warhammer", 12, 3, false, 10, 4),
                new Weapon("Morningstar", 16, 4, false, 12, 6)
        };

        // Dexterity Weapons
        dexWeapons = new Weapon[] {
                new Weapon("Dull Knife", 4, 1, false, 4, 0),
                new Weapon("Dagger", 8, 2, false, 6, 2),
                new Weapon("Stiletto", 12, 3, false, 8, 4),
                new Weapon("Ripper", 16, 4, false, 10, 6)
        };

        // Magic Weapons
        intWeapons = new Weapon[] {
                new Weapon("Stick", 4, 1, false, 2, 0),
                new Weapon("Spellbook", 8, 2, false, 4, 2),
                new Weapon("Wand", 12, 3, false, 8, 4),
                new Weapon("Grimoire", 16, 4, false, 8, 6)
        };

        // ----------------------------------------------------------------------
        // Armors
        // ----------------------------------------------------------------------

        // Plate Armor
        heavyArmors = new Armor[] {
                new Armor("Old Chestplate", 4, 1, false, 2, 0),
                new Armor("Sturdy Plate", 8, 2, false, 6, 2),
                new Armor("Berserker Armor", 20, 5, false, 10, 8),
                new Armor("Divine Vestige", 20, 5, false, 20, 2)
        };

        // Leather Armor
        mediumArmors = new Armor[] {
                new Armor("Worn Leather", 4, 1, false, 2, 0),
                new Armor("Reinforced Leather", 8, 2, false, 4, 4),
                new Armor("Studded Leather", 16, 4, false, 8, 6),
                new Armor("Combat Suit", 20, 5, false, 12, 8)
        };

        // Cloth Armors
        lightArmors = new Armor[] {
                new Armor("Tattered Cloak", 4, 1, false, 2, 2),
                new Armor("Robes", 8, 2, false, 4, 4),
                new Armor("Padded Garments", 20, 5, false, 6, 6),
                new Armor("Archmage Heraldry", 20, 5, false, 8, 8)
        };

        // ----------------------------------------------------------------------
        // Potions
        // ----------------------------------------------------------------------

        potions = new Potion[] {
                new Potion("Omni Potion", 50, 25, true, 40,20),
        };
    }



    // Methoden zum Zuweisen des Korrekten Lootpools and die Charaktere

    public static Item getLoot(String name, PlayerCharacter player) {


        // Weapons basierend auf Klasse

        if (player instanceof Warrior || player instanceof Cleric) {
            for (Weapon weapon : strWeapons) if (weapon.getName().equalsIgnoreCase(name)) return weapon;
        }
        else if (player instanceof Rogue) {
            for (Weapon weapon : dexWeapons) if (weapon.getName().equalsIgnoreCase(name)) return weapon;
        }
        else if (player instanceof Mage) {
            for (Weapon weapon : intWeapons) if (weapon.getName().equalsIgnoreCase(name)) return weapon;
        }


        // Armors basierend auf Klasse

        if (player instanceof Warrior || player instanceof Cleric) {
            for (Armor armor : heavyArmors) if (armor.getName().equalsIgnoreCase(name)) return armor;
        }
        if (player instanceof Rogue) {
            for (Armor armor : mediumArmors) if (armor.getName().equalsIgnoreCase(name)) return armor;
        }
        if (player instanceof Mage) {
            for (Armor armor : lightArmors) if (armor.getName().equalsIgnoreCase(name)) return armor;
        }
        return null;
    }
}
