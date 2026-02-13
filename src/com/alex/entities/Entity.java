package com.alex.entities;

public abstract class Entity {

    // ----------------------------------------------------------------------
    // Attributes
    // ----------------------------------------------------------------------

    protected String name = "";
    protected int hp = 0;
    protected int startHp = 0;
    protected int restHp = 0;
    protected int mp = 0;
    protected int startMp = 0;
    protected int restMp = 0;
    private String currentState;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public void Entity(){};

    public Entity(String name,
                  int hp,
                  int startHp,
                  int restHp,
                  int mp,
                  int startMp,
                  int restMp) {
        this.name = name;
        this.hp = hp;
        this.startHp = startHp;
        this.restHp = restHp;
        this.mp = mp;
        this.startMp = startMp;
        this.restMp = restMp;
        this.currentState = "";
    };

    // ----------------------------------------------------------------------
    // Basic Methods
    // ----------------------------------------------------------------------

    public abstract int attack();

    public abstract int sattack();

    public abstract int getDefense();

    public int takeDamage(int amount){
        int actualDamage = Math.max(0, amount - getDefense());
        this.hp = Math.max(0, this.hp - actualDamage);
        return actualDamage;
    }

    // ----------------------------------------------------------------------
    // Getter
    // ----------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getStartHp() {
        return startHp;
    }

    public int getRestHp() {
        return restHp;
    }

    public int getMp() {
        return mp;
    }

    public int getStartMp() {
        return startMp;
    }

    public int getRestMp() {
        return restMp;
    }

    public String getCurrentState() {
        return this.currentState;
    }


    // ----------------------------------------------------------------------
    // Setter
    // ----------------------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setStartHp(int startHp) {
        this.startHp = startHp;
    }

    public void setRestHp(int restHp) {
        this.restHp = restHp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setStartMp(int startMp) {
        this.startMp = startMp;
    }

    public void setRestMp(int restMp) {
        this.restMp = restMp;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

}
