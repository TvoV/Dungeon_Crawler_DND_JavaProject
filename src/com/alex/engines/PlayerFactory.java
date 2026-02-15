package com.alex.engines;

import com.alex.entities.player.*;
import com.alex.items.ItemRegistry;

public class PlayerFactory {

    // -------------------------------------------------------------------------
    // Instanziierung der Spielercharaktere
    // -------------------------------------------------------------------------

    public static PlayerCharacter createCharacter(int selection, String name) {
        return switch (selection) {
            case 1 -> new Warrior(name,
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
                    ItemRegistry.strWeapons[0],
                    ItemRegistry.heavyArmors[0]
            );
            case 2 -> new Rogue(name,
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
                    ItemRegistry.dexWeapons[0],
                    ItemRegistry.mediumArmors[0]);
            case 3 -> new Mage(name,
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
                    ItemRegistry.intWeapons[0],
                    ItemRegistry.lightArmors[0]);
            case 4 -> new Cleric(name,
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
                    ItemRegistry.strWeapons[4],
                    ItemRegistry.heavyArmors[0]
            );
            default -> null;
        };
    }
}
