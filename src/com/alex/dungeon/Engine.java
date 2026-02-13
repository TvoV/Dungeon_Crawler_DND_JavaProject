package com.alex.dungeon;

import com.alex.engines.CombatWindow;
import com.alex.engines.EnemyFactory;
import com.alex.engines.MainMenu;
import com.alex.entities.enemies.Enemy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.*;

public class Engine extends JFrame{

    // -------------------------------------------------------------------------
    // Engine class Attribute
    // -------------------------------------------------------------------------

    private MainMenu mainMenu;
    private static final int NUM_FIELDS = 20;
    private LinkedList<BufferedImage> grasTiles = new LinkedList<>();
    private Dungeon dungeon;
    private Player player;
    private DungeonCanvas canvas;

    // -------------------------------------------------------------------------
    // Engine class Constructor
    // -------------------------------------------------------------------------

    public Engine(Player selectedPlayer, MainMenu mainMenu) {
        // 1. Daten übernehmen
        this.player = selectedPlayer;
        this.mainMenu = mainMenu;
        this.dungeon = new Dungeon();

        completeSetup();
    }

    // KONSTRUKTOR 2: Damit deine Main.java weiterhin kompiliert
    public Engine(Player selectedPlayer) {
        this(selectedPlayer, null); // Ruft den oberen Konstruktor mit null für das Menü auf
    }

    private void completeSetup() {
        // 2. Dungeon vorbereiten
        this.dungeon.setPlayer(this.player);
        this.dungeon.buildDungeon(Engine.NUM_FIELDS, 1, 1);

        // 3. GUI Fenster-Einstellungen
        this.setSize(1080, 1080);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.canvas = new DungeonCanvas();
        this.add(this.canvas);

        // 5. KeyListener hinzufügen (direkt an 'this' binden)
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (!isEnabled()) return;

                // Navigation
                if (key.getKeyChar() == 'w') dungeon.getPlayer().move(0);
                else if (key.getKeyChar() == 'a') dungeon.getPlayer().move(3);
                else if (key.getKeyChar() == 's') dungeon.getPlayer().move(2);
                else if (key.getKeyChar() == 'd') dungeon.getPlayer().move(1);

                dungeon.revealPath();
                Field current = dungeon.getPlayer().getCurrentField();

                // Event Handling
                if (current.getEventType() != null && !current.getEventResolved()) {
                    if (current.getEventType().equals("Enemy")) {
                        setEnabled(false);
                        Enemy e = current.getEnemy();

                        // NOTFALL-CHECK: Falls das Feld leer ist (verhindert den Absturz)
                        if (e == null) {
                            //System.out.println("Warning: Feld had no Enemy! Emergency-Enemy!");
                            e = EnemyFactory.getRandomEnemy();
                            current.setEnemy(e);
                        }

                        new CombatWindow(Engine.this, current, e);
                        new CombatWindow(Engine.this, current, current.getEnemy());
                    }
                    else if (current.getEventType().equals("Loot")) {
                        JOptionPane.showMessageDialog(Engine.this, "You found a chest! \n1 x Omni Potion");
                        current.setEventResolved(true);
                        current.setEventType("Empty");
                    }
                }
                repaint();
            }
        });
        this.setVisible(true);
    }


    public MainMenu getMainMenu() {
        return mainMenu;
    }

    private class DungeonCanvas extends JPanel {
        @Override
        public void paint(Graphics g) {

            // 1. Sicherheits-Checks
            if (dungeon == null) return;
            Field[][] fields = dungeon.getFields();
            if (fields == null) return;

            super.paint(g);

            int width = this.getWidth();
            int height = this.getHeight();

            int fieldWidth = width / Engine.NUM_FIELDS;
            int fieldHeight = height / Engine.NUM_FIELDS;


            // Hintergrund füllen (Alles was nicht gesetzt ist, bleibt Schwarz)
            g.setColor(new Color(0, 0, 0));
            g.fillRect(0, 0, width, height);

            for (int y_coord = 0; y_coord < Engine.NUM_FIELDS; y_coord++) {
                for (int x_coord = 0; x_coord < Engine.NUM_FIELDS; x_coord++) {

                    Field currentField = fields[y_coord][x_coord];

                    if (currentField == null || !currentField.getIsDiscovered()) {
                        continue;
                    }

                    int x = x_coord * fieldWidth;
                    int y = y_coord * fieldHeight;

                    // Zeichnen der Felder
                    if (currentField instanceof Room) {
                        Room room = (Room) currentField;

                        // Normaler Raum: Dunkelgrau
                        g.setColor(new Color(80, 80, 80));
                        g.fillRect(x, y, fieldWidth, fieldHeight);

                        // Boss-Raum: Mit rötlichem Boden
                        if (room.getBossRoom()) {
                            g.setColor(new Color(90, 0, 0));
                            g.fillRect(x, y, fieldWidth, fieldHeight);
                        }
                    } else if (currentField instanceof Floor) {
                        // Gänge helles Grau
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(x, y, fieldWidth, fieldHeight);
                    }

                    if (currentField.getEventType() != null && !currentField.getEventResolved()) {
                        if (currentField.getEventType().equals("Enemy")) {
                            // Gegner orangenes Viereck
                            g.setColor(new Color(197, 78, 0));
                            // Kleines Viereck in der Mitte des Feldes
                            g.fillRect(x + fieldWidth/4, y + fieldHeight/4, fieldWidth/2, fieldHeight/2);
                        }
                        else if (currentField.getEventType().equals("Loot")) {
                            // Schatz grünes Oval
                            g.setColor(new Color(37, 202, 0));
                            g.fillOval(x + fieldWidth/4, y + fieldHeight/4, fieldWidth/2, fieldHeight/2);
                        }
                    }

                    // Gitter zur Orientierung
                    g.setColor(new Color(40, 40, 40));
                    g.drawRect(x, y, fieldWidth, fieldHeight);

                    // Zeichnen des SpielerIcons
                    if (dungeon.getPlayer() != null && currentField == dungeon.getPlayer().getCurrentField()) {
                        g.setColor(new Color(250, 250, 250));
                        // Ein Kreis hebt den Spieler besser von den eckigen Feldern ab
                        g.fillOval(x + 5, y + 5, fieldWidth - 10, fieldHeight - 10);
                    }
                }
            }
        }
    }

    public Dungeon getDungeon() {
        return this.dungeon;
    }
}
