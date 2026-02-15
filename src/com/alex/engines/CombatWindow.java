package com.alex.engines;

import com.alex.dungeon.Engine;
import com.alex.dungeon.Field;
import com.alex.entities.enemies.Beholder;
import com.alex.entities.enemies.Enemy;
import com.alex.entities.player.PlayerCharacter;
import com.alex.items.*;

import javax.swing.*;
import java.awt.*;

    // ----------------------------------------------------------------------------
    // JFrame Generierung für Combat Window  // Hauptapplikation
    // ----------------------------------------------------------------------------

public class CombatWindow extends JFrame {

    // ----------------------------------------------------------------------------
    // Attribute
    // ----------------------------------------------------------------------------

    private Engine parentEngine;
    private Field currentField;
    private PlayerCharacter player;
    private Enemy enemy;
    private JProgressBar playerHealthBar, playerManaBar, enemyHealthBar;
    private JTextArea combatLog;
    private JLabel enemySpriteLabel;

    // ----------------------------------------------------------------------------
    // Buttons fürs CombatWindow
    // ----------------------------------------------------------------------------

    private JButton btnAttack;
    private JButton btnSpecial;
    private JButton btnCheckGear;
    private JButton btnUsePotion;
    private JButton btnFlee;

    public CombatWindow(Engine engine, Field field, Enemy enemy) {
        this.parentEngine = engine;
        this.currentField = field;

        if (enemy == null) {
            // Verteidigungslinie gegen NullPointer
            this.enemy = EnemyFactory.getRandomEnemy();
        } else {
            this.enemy = enemy;
        }

        // ----------------------------------------------------------------------------
        // Initialisierung der Entities
        // ----------------------------------------------------------------------------

        this.player = engine.getDungeon().getPlayer().getCharacter();

        // ----------------------------------------------------------------------------
        // Fenster-Setup
        // ----------------------------------------------------------------------------

        setTitle("Dungeon Combat - " + (this.enemy != null ? this.enemy.getName() : "Unknown"));
        //setTitle("Dungeon Combat - " + enemy.getName());
        setSize(1280, 720);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(new Color(20, 20, 20));

        // ----------------------------------------------------------------------------
        // 1. Status Panel (Oben)
        // ----------------------------------------------------------------------------

        JPanel statusPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        statusPanel.setBackground(Color.BLACK);

        playerHealthBar = createBar(player.getHp(), player.getStartHp(), new Color(10, 125, 0));
        playerManaBar = createBar(player.getMp(), player.getStartMp(), new Color(0, 100, 200));
        enemyHealthBar = createBar(this.enemy.getHp(), this.enemy.getStartHp(), new Color(185, 0, 0));

        JLabel enemyName = new JLabel(this.enemy.getName().toUpperCase(), SwingConstants.CENTER);
        enemyName.setForeground(Color.WHITE);
        enemyName.setFont(new Font("Monospaced", Font.PLAIN, 20));

        statusPanel.add(playerHealthBar);
        statusPanel.add(enemyHealthBar);
        statusPanel.add(playerManaBar);
        statusPanel.add(enemyName);
        add(statusPanel, BorderLayout.NORTH);

        // ----------------------------------------------------------------------------
        // 2. Fenster für Gegner Sprite & Hintergrund
        // ----------------------------------------------------------------------------

        enemySpriteLabel = new JLabel("", SwingConstants.CENTER);
        enemySpriteLabel.setPreferredSize(new Dimension(600, 600));
        add(enemySpriteLabel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(Color.BLACK);

        // ----------------------------------------------------------------------------
        // 3. Das Kampf-Log (über den Buttons)
        // ----------------------------------------------------------------------------

        combatLog = new JTextArea(6, 30); // 6 Zeilen Sichtfeld
        combatLog.setEditable(false);
        combatLog.setBackground(new Color(30, 30, 30)); // Backgroundfarbe
        combatLog.setForeground(new Color(200, 140, 20)); // Textfarbe
        combatLog.setFont(new Font("Monospaced", Font.PLAIN, 13));
        combatLog.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane logScrollPane = new JScrollPane(combatLog);
        logScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        southPanel.add(logScrollPane, BorderLayout.CENTER);

        // ----------------------------------------------------------------------------
        // 4. Button Panel (Unten) Styling
        // ----------------------------------------------------------------------------

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.BLACK);

        JButton btnAttack = new JButton("Attack");
        JButton btnSpecial = new JButton("Specialattack");
        JButton btnCheckGear = new JButton("Equipment");
        JButton btnUsePotion = new JButton("Use Potion");
        JButton btnFlee = new JButton("Escape");

        styleButton(btnAttack);
        styleButton(btnSpecial);
        styleButton(btnCheckGear);
        styleButton(btnUsePotion);
        styleButton(btnFlee);

        buttonPanel.add(btnAttack);
        buttonPanel.add(btnSpecial);
        buttonPanel.add(btnCheckGear);
        buttonPanel.add(btnUsePotion);
        buttonPanel.add(btnFlee);

        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);

        // ----------------------------------------------------------------------------
        // Logik Verknüpfung
        // ----------------------------------------------------------------------------

        btnAttack.addActionListener(e -> executeTurn(1));
        btnSpecial.addActionListener(e -> executeTurn(2));
        btnCheckGear.addActionListener(e -> player.checkstats());
        btnUsePotion.addActionListener(e -> {
                player.usePotion(ItemRegistry.potions[0]);
                log("You used a potion!");
                updateBars();
        });
        btnFlee.addActionListener(e -> {
            log("You managed to escape!");
            closeCombat();
        });
        setEnemySprite(this.enemy);
        this.setVisible(true);

        // Der Encounter-Text beim Start
        log("=========================================== <");
        String intro = EncounterManager.printRandomEvent(player.getName());
        log(intro);
        log("\nAn enemy " + this.enemy.getName() + " appears!");
        log("=========================================== <");
    }

    // ----------------------------------------------------------------------------
    // Kampf Loop Turn Mechanik
    // ----------------------------------------------------------------------------

    private void executeTurn(int action) {
        // Spieler-Phase
        String pMsg = CombatEngine.handleAttack(player, enemy, action);
        updateBars();
        log(pMsg); // Schreibt den Text ins Log

        if (enemy.getHp() <= 0) {
            enemy.setHp(0);
            updateBars();
            enemySpriteLabel.setEnabled(false);         // kleines Death Highlight, weißes Highlight
            Item item = currentField.getLootItem();
            if (item != null) {
                player.addToInventory(item);
                log("The " + enemy.getName() + " dropped: " + item.getName());

                // Automatisches Ausrüsten für Waffen/Rüstung
                if (item instanceof Weapon) {
                    player.equipWeapon((Weapon) item);
                    log("You have equipped the " + item.getName() + "!");
                } else if (item instanceof Armor) {
                    player.equipArmor((Armor) item);
                    log("You have equipped the " + item.getName() + "!");
                }
            }
            checkVictory();
            return;
        }

        // Gegner-Phase
        // Timer für verzögerte Ausgabe nach der Spielerattacke
        Timer enemyTimer = new Timer(800, e -> {
            int enemyChoice = (enemy.pickAttack() <= 2) ? 1 : 2;
            String eMsg = CombatEngine.handleAttack(enemy, player, enemyChoice);
            updateBars();
            log(eMsg);

            if (player.getHp() <= 0) {
                disableButtons();
                handlePlayerDeath();
            }
        });
        enemyTimer.setRepeats(false);
        enemyTimer.start();
    }

    // ----------------------------------------------------------------------------
    // Player Death Methode (GAMEOVER)
    // ----------------------------------------------------------------------------

    private void handlePlayerDeath() {
        // 1. Letzten Eintrag im Kampf-Log setzen
        log("Deadly hit! Your can't go on...");
        updateBars(); // Sicherstellung, dass der HP Balken leer ist

        // 2. Einen Timer starten, der 2 Sekunden (2000ms) wartet
        Timer delayTimer = new Timer(2000, e -> {

            // Dieser Code wird erst nach 2 Sekunden ausgeführt:
            JOptionPane.showMessageDialog(this,
                    "You were defeated by the " + enemy.getName() + "...",
                    "Your journey ends here.",
                    JOptionPane.ERROR_MESSAGE);

            // Aufräumen und Reset zu Main Menu
            returnToMainMenu();
        });

        delayTimer.setRepeats(false); // Der Timer soll nur einmal feuern
        delayTimer.start();
    }

    // ----------------------------------------------------------------------------
    // Game Reset Methode (GAMEOVER)
    // ----------------------------------------------------------------------------

    private void returnToMainMenu() {
        // Schließe das Kampf-Fenster
        this.dispose();

        // Nutze die Referenz zur Engine, um das Menü wiederzuholen
        if (parentEngine != null) {
            // Wir holen das MainMenu aus der Engine
            if (parentEngine.getMainMenu() != null) {
                parentEngine.getMainMenu().setVisible(true);
            }
            // Schließe das Dungeon-Fenster
            parentEngine.dispose();
        }
    }

    // ----------------------------------------------------------------------------
    // update Healthbars
    // ----------------------------------------------------------------------------

    private void updateBars() {
        playerHealthBar.setValue(player.getHp());
        playerHealthBar.setString("HP: " + player.getHp() + "/" + player.getStartHp());
        playerManaBar.setValue(player.getMp());
        playerManaBar.setString("MP: " + player.getMp() + "/" + player.getStartMp());
        enemyHealthBar.setValue(enemy.getHp());
        enemyHealthBar.setString("Enemy HP: " + enemy.getHp() + "/" + enemy.getStartHp());
    }

    private JProgressBar createBar(int val, int max, Color c) {
        JProgressBar bar = new JProgressBar(0, max);
        bar.setValue(val);
        bar.setStringPainted(true);
        bar.setForeground(c);
        bar.setBackground(Color.DARK_GRAY);
        bar.setFont(new Font("Monospaced", Font.PLAIN, 12));
        return bar;
    }

    private void disableButtons() {
        // Ersetze diese Namen durch die tatsächlichen Variablennamen deiner Buttons
        if (btnAttack != null) btnAttack.setEnabled(false);
        if (btnSpecial != null) btnSpecial.setEnabled(false);
        if (btnCheckGear != null) btnCheckGear.setEnabled(false);
        if (btnFlee != null) btnFlee.setEnabled(false);
        // Falls du weitere Buttons hast (z.B. Inventar, Flucht), auch diese hier deaktivieren
    }

    // ----------------------------------------------------------------------------
    // Styling der Buttons
    // ----------------------------------------------------------------------------

    private void styleButton(JButton btn) {
        btn.setPreferredSize(new Dimension(150, 40));
        btn.setFocusPainted(false);
    }

    // ----------------------------------------------------------------------------
    // Methode zum Beenden des Combat Window JFrames
    // ----------------------------------------------------------------------------

    private void closeCombat() {
        if (currentField != null) {
            currentField.setEventResolved(true);
        }
        this.dispose();
        if (parentEngine != null) {
            parentEngine.setEnabled(true);
            parentEngine.requestFocus();
        }
    }

    // ----------------------------------------------------------------------------
    // Combat log für Kampfnachrichten
    // ----------------------------------------------------------------------------

    public void log(String message) {
        combatLog.append("> " + message + "\n");

        // Automatisches Scrollen nach unten
        combatLog.setCaretPosition(combatLog.getDocument().getLength());
    }

    // ----------------------------------------------------------------------------
    // Spielsieg Check Methode
    // ----------------------------------------------------------------------------

    private void checkVictory() {
        if (enemy.getHp() <= 0) {
            enemy.setHp(0); // Optik: Keine negativen Zahlen
            updateBars();   // Balken aktualisieren
            log(enemy.getName() + " wurde vernichtet!");
            disableButtons();

            if (enemy instanceof Beholder) {
                Timer victoryTimer = new Timer(2000, e -> {
                    JOptionPane.showMessageDialog(this,
                            "You're a legend!\nYou struck down the Beholder.\n" +
                                    "You cleared the first dungeon floor!",
                            "VICTORY", JOptionPane.INFORMATION_MESSAGE);
                    returnToMainMenu();
                });
                victoryTimer.setRepeats(false);
                victoryTimer.start();
            } else {
                // Hier kommt dein bisheriger Sieg-Code rein (Loot vergeben etc.)
                handleNormalVictory();
            }
        }
    }

    private void handleNormalVictory() {
        // Dem Spieler gratulieren
        log("The " + enemy.getName() + " has been slayed. Victory!");

        // Ein kleiner Timer, damit der Spieler den Loot-Text noch lesen kann
        Timer closeTimer = new Timer(1500, e -> {
            // Das Event im Dungeon als "erledigt" markiert, damit der Gegner von der Karte verschwindet
            if (currentField != null) {
                currentField.setEventResolved(true);
            }

            // Das Kampf-Fenster schließen
            this.dispose();

            // Die Engine (Karte) wieder klickbar machen
            if (parentEngine != null) {
                parentEngine.setEnabled(true);
                parentEngine.requestFocus(); // Fokus zurück auf die Karte für die Steuerung (W,A,S,D)
            }
        });
        closeTimer.setRepeats(false);
        closeTimer.start();
    }

    // ----------------------------------------------------------------------------
    // Sprite Loader Method
    // ----------------------------------------------------------------------------

    private void setEnemySprite(Enemy enemy) {
        try {

            String fileName = "/img/" + enemy.getName() + ".png";
            java.net.URL imgURL = getClass().getResource(fileName);

            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                // Bild auf eine schöne Größe skalieren
                Image img = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                enemySpriteLabel.setIcon(new ImageIcon(img));
            } else {
                System.err.println("Sprite nicht gefunden: " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}