package com.alex.dungeon;

public class Room extends Field{
    private boolean isBossRoom = false;
    private String roomName;

    // Constructor for the regular Rooms
    public Room() {
        this("Standard Room", false);
    }

    // Constructor for the Boss Room
    public Room(String name, boolean isBoss) {
        this.roomName = name;
        this.isBossRoom = isBoss;
    }

    // Getters
    public boolean getBossRoom() {
        return isBossRoom;
    }

    public String getRoomName() {
        return roomName;
    }

    // Setters
    public void setBossRoom(boolean bossRoom) {
        isBossRoom = bossRoom;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}

class Floor extends Field {

}