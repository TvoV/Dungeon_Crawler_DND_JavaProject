package com.alex;

import com.alex.dungeon.Engine;
import com.alex.dungeon.Player;
import com.alex.engines.MainMenu;
import com.alex.engines.PlayerFactory;
import com.alex.entities.player.PlayerCharacter;

import javax.swing.*;

import static com.alex.items.ItemRegistry.initialize;

public class Main {
    public static void main(String[] args) {

        initialize();

        // 5. Die Engine mit dem fertigen Player starten
        SwingUtilities.invokeLater(() -> {
            new MainMenu();
        });
    }
}
