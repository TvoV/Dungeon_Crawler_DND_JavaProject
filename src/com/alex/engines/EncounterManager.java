package com.alex.engines;

import java.util.Random;

// -------------------------------------------------------------------------
// Flavortext für das Combat Log
// -------------------------------------------------------------------------

public class EncounterManager {
    private static final Random random = new Random();
    private static final String[] Encounter_Texts = {
            " steps further into the hallway.",
            " steps further into the room.",
            " feels something lurching from the dark.",
            " hears a noise from behind."
    };

    public static String printRandomEvent(String characterName) {
        int index = random.nextInt(Encounter_Texts.length);
        return characterName + Encounter_Texts[index];
    }
}
