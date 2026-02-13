package com.alex.dungeon;

import java.util.Scanner;

public class Old_Dungeon {

    private int numberOfFields;

    private Field[][] fields;

    private Player player;

    // private Engine engine;

    public Old_Dungeon() {
        //throw new RuntimeException("Not implemented");
    }

    // Place rooms around maze
    public void placeRooms(int numberOfFields) {

        this.fields = new Field[numberOfFields][numberOfFields];
        this.fields[3][5] = new  Room();
        this.fields[3][6] = new  Room();
        this.fields[3][7] = new  Room();
        this.fields[4][5] = new  Room();
        this.fields[4][6] = new  Room();
        this.fields[4][7] = new  Room();

        this.fields[4][13] = new  Room();
        this.fields[4][14] = new  Room();
        this.fields[5][13] = new  Room();
        this.fields[5][14] = new  Room();

        this.fields[8][2] = new  Room();
        this.fields[8][3] = new  Room();
        this.fields[8][4] = new  Room();
        this.fields[9][2] = new  Room();
        this.fields[9][3] = new  Room();
        this.fields[9][4] = new  Room();
        this.fields[10][2] = new  Room();
        this.fields[10][3] = new  Room();
        this.fields[10][4] = new  Room();
        this.fields[11][2] = new  Room();
        this.fields[11][3] = new  Room();
        this.fields[11][4] = new  Room();

        this.fields[15][5] = new  Room();
        this.fields[15][6] = new  Room();
        this.fields[15][7] = new  Room();
        this.fields[16][5] = new  Room();
        this.fields[16][6] = new  Room();
        this.fields[16][7] = new  Room();
        this.fields[17][5] = new  Room();
        this.fields[17][6] = new  Room();
        this.fields[17][7] = new  Room();

        this.fields[11][15] = new  Room();
        this.fields[11][16] = new  Room();
        this.fields[11][17] = new  Room();
        this.fields[12][15] = new  Room();
        this.fields[12][16] = new  Room();
        this.fields[12][17] = new  Room();
        this.fields[13][12] = new  Room();
        this.fields[13][13] = new  Room();
        this.fields[13][14] = new  Room();
        this.fields[13][15] = new  Room();
        this.fields[13][16] = new  Room();
        this.fields[13][17] = new  Room();
        this.fields[14][12] = new  Room();
        this.fields[14][13] = new  Room();
        this.fields[14][14] = new  Room();
        this.fields[14][15] = new  Room();
        this.fields[14][16] = new  Room();
        this.fields[14][17] = new  Room();
        this.fields[15][12] = new  Room();
        this.fields[15][13] = new  Room();
        this.fields[15][14] = new  Room();
        this.fields[15][15] = new  Room();
        this.fields[15][16] = new  Room();
        this.fields[15][17] = new  Room();

        connectRoomCells(3, 5, 3, 2);   // Erster Raum
        connectRoomCells(4, 13, 2, 2);  // Zweiter Raum
        connectRoomCells(8, 2, 3, 4);   // Dritter Raum
        connectRoomCells(15, 5, 3, 3);  // Vierter Raum
        connectRoomCells(11, 12, 7, 6); // Boss Raum
    }

    /*
    * Verbindet ein spezifisches Feld eines Raums mit einem benachbarten Floor-Feld.
    * @param roomX X-Koordinate der Raum-Kachel
    * @param roomY Y-Koordinate der Raum-Kachel
    * @param direction 0=North, 1=East, 2=South, 3=West
    */

    // Create Doors Methode zum hinzufügen von Türen zu den Räumen im Dungeon

    public void createDoor(int roomX, int roomY, int direction) {
        Field roomField = fields[roomY][roomX];

        try {
            switch(direction) {
                case 0: // North
                    if (fields[roomY-1][roomX] != null) roomField.setNorth(fields[roomY-1][roomX]);
                    break;
                case 1: // East
                    if (fields[roomY][roomX+1] != null) roomField.setEast(fields[roomY][roomX+1]);
                    break;
                case 2: // South
                    if (fields[roomY+1][roomX] != null) roomField.setSouth(fields[roomY+1][roomX]);
                    break;
                case 3: // West
                    if (fields[roomY][roomX-1] != null) roomField.setWest(fields[roomY][roomX-1]);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Tür konnte nicht am Rand platziert werden.");
        }
    }

    // Methode zum Platzieren der Türen in den Räumen entsprechend der angrenzenden Floors

    public void connectRoomCells(int startX, int startY, int width, int height) {
        for (int y = startY; y < startY + height; y++) {
            for (int x = startX; x < startX + width; x++) {
                Field current = fields[y][x];

                if (current == null) continue;

                // Verbinde horizontal mit dem rechten Nachbarn, wenn dieser noch im Raum-Rechteck liegt
                if (x + 1 < startX + width) {
                    Field eastNeighbor = fields[y][x + 1];
                    if (eastNeighbor != null) {
                        current.setEast(eastNeighbor);
                    }
                }

                // Verbinde vertikal mit dem unteren Nachbarn, wenn dieser noch im Raum-Rechteck liegt
                if (y + 1 < startY + height) {
                    Field southNeighbor = fields[y + 1][x];
                    if (southNeighbor != null) {
                        current.setSouth(southNeighbor);
                    }
                }
            }
        }
    }

    private void pathFinder(Position start, Position end) {
        Position current = new Position(start.getX(), start.getY());

        while (current.getX() != end.getX() || current.getY() != end.getY()) {

            // Entscheide: Bewegen wir uns in X oder Y Richtung?

            if (Math.random() < 0.5 && current.getX() != end.getX()) {
                int step = (end.getX() > current.getX()) ? 1 : -1;
                connectAndNext(current, current.getX() + step, current.getY(), 1); // 1 = Ost/West Logik
                current.setX(current.getX() + step);
            } else if (current.getY() != end.getY()) {
                int step = (end.getY() > current.getY()) ? 1 : -1;
                connectAndNext(current, current.getX(), current.getY() + step, 2); // 2 = Nord/Süd Logik
                current.setY(current.getY() + step);
            } else {

                // Falls X schon fertig war, aber Y noch nicht (oder umgekehrt)

                int step = (end.getX() > current.getX()) ? 1 : -1;
                if(current.getX() != end.getX()) {
                    connectAndNext(current, current.getX() + step, current.getY(), 1);
                    current.setX(current.getX() + step);
                }
            }
        }
    }

    private void connectAndNext(Position oldPos, int nextX, int nextY, int type) {

        // Wenn das Zielfeld noch leer ist, mache es zum Floor

        if (fields[nextY][nextX] == null) {
            fields[nextY][nextX] = new Floor();
        }

        Field f1 = fields[oldPos.getY()][oldPos.getX()];
        Field f2 = fields[nextY][nextX];

        // Manuelle Verknüpfung basierend auf der Richtung

        if (nextX > oldPos.getX()) f1.setEast(f2);
        else if (nextX < oldPos.getX()) f1.setWest(f2);
        else if (nextY > oldPos.getY()) f1.setSouth(f2);
        else if (nextY < oldPos.getY()) f1.setNorth(f2);
    }

    public void drawMaze() {

        throw new RuntimeException("Not implemented");
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        String input = "";
        while(!input.equals("exit")) {

            System.out.print("[nesw]: ");
            input = sc.nextLine();
            boolean moved = false;

            switch(input) {
                case "n":
                    moved = player.move( 0 ); break;
                case "e":
                    moved = player.move( 1 ); break;
                case "s":
                    moved = player.move( 2 ); break;
                case "w":
                    moved = player.move( 3 ); break;
                default:
                    continue;
            }

            if(!moved) {
                System.err.println("Du kannst nicht dort hin!");
            }
            else {
                Field field = player.getCurrentField();
                if(field instanceof Room) {
                    System.out.println("Du befindest dich in einem Raum" );
                }
                System.out.println(field);
            }
        }
    }

    public void move() {
        throw new RuntimeException("Not implemented");
    }

    public Field[][] getFields() {
        return this.fields;
    }

    public void randomReconnect(double prob) {
        for( int y = 0; y < this.fields.length; y++) {
            for( int x = 0; x < this.fields[y].length; x++) {
                if( Math.random() < prob) {
                    int direction = (int)(Math.random() * 4);

                    try {
                        switch( direction  ) {
                            case 0:
                                this.fields[y][x].setNorth(this.fields[y-1][x]);
                                break;
                            case 1:
                                this.fields[y][x].setEast(this.fields[y][x+1]);
                                break;
                            case 2:
                                this.fields[y][x].setSouth(this.fields[y+1][x]);
                                break;
                            case 3:
                                this.fields[y][x].setWest(this.fields[y][x-1]);
                                break;
                        }
                    }
                    catch( ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
        }
    }
    public Player getPlayer() {
        return this.player;
    }
}
