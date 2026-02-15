package com.alex.dungeon;

import com.alex.entities.player.PlayerCharacter;


public class Player {
    private String name;
    private Field currentField;
    private PlayerCharacter character;

    public Player(String name, PlayerCharacter character) {
        this.name = name;
        this.character = character;
    }

    public void setCurrentField(Field currentField) {
        this.currentField = currentField;
    }

    public boolean move(int direction) {

        switch(direction) {

            case 0:
                if(this.currentField.getNorth() != null) {
                    this.currentField = this.currentField.getNorth();
                    return true;
                }
                break;
            case 1:
                if(this.currentField.getEast() != null) {
                    this.currentField = this.currentField.getEast();
                    return true;
                }
                break;
            case 2:
                if(this.currentField.getSouth() != null) {
                    this.currentField = this.currentField.getSouth();
                    return true;
                }
                break;
            case 3:
                if(this.currentField.getWest() != null) {
                    this.currentField = this.currentField.getWest();
                    return true;
                }
                break;
        }
        return false;
    }
    public Field getCurrentField() {
        return this.currentField;
    }

    public PlayerCharacter getCharacter() {
        return this.character;
    }

    public String getName() {
        return name;
    }

}

