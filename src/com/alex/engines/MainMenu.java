package com.alex.engines;

import com.alex.dungeon.Engine;
import com.alex.dungeon.Player;
import com.alex.entities.player.*;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Dungeon Crawler - Main Menu");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Haupt-Panel (Dunkles Design)

        JPanel panel = new JPanel();
        panel.setBackground(new Color(20, 20, 20));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("DUNGEON CRAWLER", SwingConstants.CENTER);
        title.setFont(new Font("Monospaced", Font.BOLD, 32));
        title.setForeground(new Color(200, 0, 0)); // Dunkelrot
        gbc.gridy = 0;
        panel.add(title, gbc);

        // -------------------------------------------------------------------------
        // Charakter-Auswahl-Buttons im Hauptmenü
        // -------------------------------------------------------------------------

        gbc.gridy = 1;
        panel.add(createClassButton("Warrior", "High HP & Damage"), gbc);
        gbc.gridy = 2;
        panel.add(createClassButton("Mage", "High Mana & strong Spells"), gbc);
        gbc.gridy = 3;
        panel.add(createClassButton("Rogue", "High Crit. Chance and Strong Crit. Damage"), gbc);
        gbc.gridy = 4;
        panel.add(createClassButton("Cleric", "Balance of Bulk and Mana"), gbc);

        add(panel);
        setVisible(true);
    }

    private JButton createClassButton(String name, String tooltip) {
        JButton btn = new JButton(name);
        btn.setToolTipText(tooltip);
        btn.setFont(new Font("Monospaced", Font.PLAIN, 18));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(45, 45, 45));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btn.setPreferredSize(new Dimension(250, 50));

        btn.addActionListener(e -> startGame(name));
        return btn;
    }

    // -------------------------------------------------------------------------
    // JPane Popout für Namensgebung
    // -------------------------------------------------------------------------

    private void startGame(String className) {
        String name = JOptionPane.showInputDialog(this, "Enter your name, hero:", className);
        int selection = switch (className) {
            case "Warrior" -> 1;
            case "Rogue"   -> 2;
            case "Mage"    -> 3;
            case "Cleric"  -> 4;
            default -> 1;
        };

        // 1. Das Stat-Objekt (PlayerCharacter) erstellen
        PlayerCharacter selectedStats = PlayerFactory.createCharacter(selection, "");

        if (selectedStats != null) {
            Player dungeonPlayer = new Player("", selectedStats);

            // 2. Engine starten
            new Engine(dungeonPlayer, this);
            this.setVisible(false);
        }
    }

}
