package com.alex.dungeon;

import com.alex.engines.EnemyFactory;
import com.alex.entities.player.Cleric;
import com.alex.entities.player.Mage;
import com.alex.entities.player.Rogue;
import com.alex.entities.player.Warrior;
import com.alex.items.Item;
import com.alex.items.ItemRegistry;
import java.util.Random;


public class Dungeon {

    private Field[][] fields;
    private Player player;
    private Random random = new Random();

    public Dungeon() {}

    /*
    * Hauptlogik: Erstellt und verbindet Räume linear.
    */

    public void buildDungeon(int size, int startX, int startY) {
        placeRooms(size);

        if (this.player != null) {
            // Wir setzen den existierenden Player auf das Startfeld
            this.player.setCurrentField(this.fields[1][1]);
        }

        // Festes Gegner Placement in den Räumen

        spawnEnemyInRoom(3, 5, 3, 3, 1);        // Raum 1
        spawnEnemyInRoom(4, 13, 3, 3, 2);       // Raum 2
        spawnEnemyInRoom(8, 2, 3, 4, 3);        // Raum 3
        spawnEnemyInRoom(15, 5, 4, 3, 4);       // Raum 4

        spawnEnemyInRoom(10, 11, 5, 5, 5);      // Raum 5 Boss

        // --- Der automatische Schlauch ---

        // 1. Von Start (1,1) zu Raum 1 (3,5)
        pathFinder(new Position(1, 1), new Position(3, 5));

        // 2. Von Raum 1 (wir nehmen eine Kachel im Raum: 4,6) zu Raum 2 (4,13)
        pathFinder(new Position(4, 6), new Position(4, 13));

        // 3. Von Raum 2 (5,14) zu Raum 3 (8,3)
        pathFinder(new Position(5, 14), new Position(8, 3));

        // 4. Von Raum 3 (9,5) zu Raum 4 (15,6)
        pathFinder(new Position(9, 5), new Position(15, 6));

        // 5. Von Raum 4 (16,7) zum Boss (13,15)
        pathFinder(new Position(16, 7), new Position(13, 15));

        if (this.player != null && this.player.getCurrentField() != null) {
            revealPath();
        }
        placeEvents();

    }

    public void placeRooms(int numberOfFields) {

        this.fields = new Field[numberOfFields][numberOfFields];

        // Start Raum 0
        this.fields[1][1] = new Room("Start", false);
        // Raum 1 (3x3)
        buildRect(3, 5, 3, 3);

        // Raum 2 (3x3)
        buildRect(4, 13, 3, 3);

        // Raum 3 (3x4)
        buildRect(8, 2, 3, 4);

        // Raum 4 (4x3)
        buildRect(15, 5, 4, 3);

        // Boss Raum
        buildRectBoss(10, 11, 5, 5);

        // Alle Räume intern begehbar machen
        connectRoomCells(3, 5, 3, 3);
        connectRoomCells(4, 13, 3, 3);
        connectRoomCells(8, 2, 3, 4);
        connectRoomCells(15, 5, 4, 3);
        connectRoomCells(10, 11, 5, 5);
    }

    // Hilfsmethode um Schreibarbeit zu sparen

    private void buildRect(int x, int y, int w, int h) {
        for(int i = y; i < y+h; i++) {
            for(int j = x; j < x+w; j++) {
                this.fields[i][j] = new Room("Room", false);
            }
        }
    }

    private void buildRectBoss(int x, int y, int w, int h) {
        for(int i = y; i < y+h; i++) {
            for(int j = x; j < x+w; j++) {
                this.fields[i][j] = new Room("Boss Chamber", true);
            }
        }
    }

    public void connectRoomCells(int startX, int startY, int width, int height) {
        for (int y = startY; y < startY + height; y++) {
            for (int x = startX; x < startX + width; x++) {
                Field current = fields[y][x];
                if (current == null) continue;

                // Nach rechts verbinden
                if (x + 1 < startX + width && fields[y][x + 1] != null) {
                    current.setEast(fields[y][x + 1]);
                    fields[y][x + 1].setWest(current);
                }

                // Nach unten verbinden
                if (y + 1 < startY + height && fields[y + 1][x] != null) {
                    current.setSouth(fields[y + 1][x]);
                    fields[y + 1][x].setNorth(current);
                }
            }
        }
    }

    private void pathFinder(Position start, Position end) {
        Position current = new Position(start.getX(), start.getY());

        while (current.getX() != end.getX() || current.getY() != end.getY()) {
            if (Math.random() < 0.5 && current.getX() != end.getX()) {
                int step = (end.getX() > current.getX()) ? 1 : -1;
                connectAndNext(current, current.getX() + step, current.getY());
                current.setX(current.getX() + step);
            } else if (current.getY() != end.getY()) {
                int step = (end.getY() > current.getY()) ? 1 : -1;
                connectAndNext(current, current.getX(), current.getY() + step);
                current.setY(current.getY() + step);
            } else {
                int step = (end.getX() > current.getX()) ? 1 : -1;
                if(current.getX() != end.getX()) {
                    connectAndNext(current, current.getX() + step, current.getY());
                    current.setX(current.getX() + step);
                }
            }
        }
    }

    private void connectAndNext(Position oldPos, int nextX, int nextY) {

        if (fields[oldPos.getY()][oldPos.getX()] == null) fields[oldPos.getY()][oldPos.getX()] = new Floor();
        if (fields[nextY][nextX] == null) fields[nextY][nextX] = new Floor();

        Field f1 = fields[oldPos.getY()][oldPos.getX()];
        Field f2 = fields[nextY][nextX];

        if (nextX > oldPos.getX()) { f1.setEast(f2); f2.setWest(f1); }
        else if (nextX < oldPos.getX()) { f1.setWest(f2); f2.setEast(f1); }
        else if (nextY > oldPos.getY()) { f1.setSouth(f2); f2.setNorth(f1); }
        else if (nextY < oldPos.getY()) { f1.setNorth(f2); f2.setSouth(f1); }
    }

    public Field[][] getFields() { return this.fields; }
    public Player getPlayer() { return this.player; }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void revealPath() {
        Field current = player.getCurrentField();
        if (player == null || player.getCurrentField() == null) {
            System.out.println("Reveal fehlgeschlagen: Spieler oder Feld null!");
            return;
        }

        player.getCurrentField().setIsDiscovered(true);

        if (current.getNorth() != null) current.getNorth().setIsDiscovered(true);
        if (current.getEast() != null) current.getEast().setIsDiscovered(true);
        if (current.getSouth() != null) current.getSouth().setIsDiscovered(true);
        if (current.getWest() != null) current.getWest().setIsDiscovered(true);
    }

    public void placeEvents() {
        for (int y = 0; y < fields.length; y++) {
            for (int x = 0; x < fields[0].length; x++) {
                Field f = fields[y][x]; // Zuweisung der X und Y Koordinaten auf dem Raster

                // Events im Gang oder in Räumen platzieren, niemals auf dem Startfeld
                if (f != null && f.getEventType() == null) {
                    // 15% Chance auf einen Gegner
                    if (Math.random() < 0.15) {
                        f.setEventType("Enemy");
                        f.setEventResolved(false);
                    }

                    // 5% Chance auf Loot (falls kein Gegner da ist)
                    else if (Math.random() < 0.05) {
                        f.setEventType("Loot");
                        f.setEventResolved(false);
                    }
                }
            }
        }
    }

    private void spawnEnemyInRoom(int x, int y, int w, int h, int roomNumber) {
        // Berechne die Mitte des Raumes
        int centerX = x + (w / 2);
        int centerY = y + (h / 2);
        Field f = fields[centerY][centerX];

        if (f != null) {
            f.setEventType("Enemy");
            f.setEventResolved(false);

            if (roomNumber == 5) {
                f.setEnemy(EnemyFactory.getBoss()); // Hier wird der Beholder gesetzt
            } else {
                f.setEnemy(EnemyFactory.getRandomEnemy());
            }

            Item loot = null;

            // ---------------------------------------------------------
            // RAUM 1: Erste Waffe (Tier 1)
            // ---------------------------------------------------------
            if (roomNumber == 1) {
                if (player.getCharacter() instanceof Warrior || player.getCharacter() instanceof Cleric) {
                    int choice = 0;
                    choice = random.nextInt(1, 3);
                    if (choice == 1) {
                        loot = ItemRegistry.strWeapons[1];
                    } else {
                        loot = ItemRegistry.strWeapons[5];
                    }
                } else if (player.getCharacter() instanceof Rogue) {
                    loot = ItemRegistry.dexWeapons[1]; // Dagger
                } else if (player.getCharacter() instanceof Mage) {
                    loot = ItemRegistry.intWeapons[1]; // Spellbook
                }
            }

            // ---------------------------------------------------------
            // RAUM 2: Erste Rüstung (Tier 1)
            // ---------------------------------------------------------
            else if (roomNumber == 2) {
                if (player.getCharacter() instanceof Warrior || player.getCharacter() instanceof Cleric) {
                    loot = ItemRegistry.heavyArmors[1]; // Sturdy Plate
                } else if (player.getCharacter() instanceof Rogue) {
                    loot = ItemRegistry.mediumArmors[1]; // Reinforced Leather
                } else if (player.getCharacter() instanceof Mage) {
                    loot = ItemRegistry.lightArmors[1]; // Reinforced Robes/Cloth
                }
            }

            // ---------------------------------------------------------
            // RAUM 3: Stärkere Waffen (Tier 2)
            // ---------------------------------------------------------
            else if (roomNumber == 3) {
                if (player.getCharacter() instanceof Warrior || player.getCharacter() instanceof Cleric) {
                    int choice = 0;
                    choice = random.nextInt(1, 3);
                    if (choice == 1) {
                        loot = ItemRegistry.strWeapons[2];
                    } else {
                        loot = ItemRegistry.strWeapons[6];
                    }
                } else if (player.getCharacter() instanceof Rogue) {
                    loot = ItemRegistry.dexWeapons[2];
                } else if (player.getCharacter() instanceof Mage) {
                    loot = ItemRegistry.intWeapons[2];
                }
            }

            // ---------------------------------------------------------
            // RAUM 4: Stärkere Rüstung (Tier 2) oder (Tier 3) mit 25% Chance
            // ---------------------------------------------------------
            else if (roomNumber == 4) {
                if (player.getCharacter() instanceof Warrior || player.getCharacter() instanceof Cleric) {
                    int choice = 0;
                    choice = random.nextInt(1, 5);
                    if (choice <= 3) {
                        loot = ItemRegistry.heavyArmors[2];
                    } else {
                        loot = ItemRegistry.heavyArmors[3];
                    }
                } else if (player.getCharacter() instanceof Rogue) {
                    int choice = 0;
                    choice = random.nextInt(1, 5);
                    if (choice <= 3) {
                        loot = ItemRegistry.mediumArmors[2];
                    } else {
                        loot = ItemRegistry.mediumArmors[3];
                    }
                } else if (player.getCharacter() instanceof Mage) {
                    int choice = 0;
                    choice = random.nextInt(1, 5);
                    if (choice <= 3) {
                        loot = ItemRegistry.lightArmors[2];
                    } else {
                        loot = ItemRegistry.lightArmors[3];
                    }
                }
            }

            // ---------------------------------------------------------
            // RAUM 5: Stärkste Waffe (Tier 3) & Stärkste Rüstung (Tier 3)
            // ---------------------------------------------------------
            else if (roomNumber == 5) {
                if (player.getCharacter() instanceof Warrior || player.getCharacter() instanceof Cleric) {
                        loot = ItemRegistry.heavyArmors[3];
                } else if (player.getCharacter() instanceof Rogue) {
                        loot = ItemRegistry.mediumArmors[3];
                } else if (player.getCharacter() instanceof Mage) {
                        loot = ItemRegistry.lightArmors[3];
                }
            }
            f.setLootItem(loot);
        }
    }

}