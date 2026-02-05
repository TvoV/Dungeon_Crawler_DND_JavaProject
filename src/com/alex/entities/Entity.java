package com.alex.entities;

public class Entity {

    // ----------------------------------------------------------------------
    // Attributes
    // ----------------------------------------------------------------------

    private String name = "";
    private int hp = 0;
    private int startHp = 0;
    private int restHp = 0;
    private int mp = 0;
    private int startMp = 0;
    private int restMp = 0;

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
    };

    // ----------------------------------------------------------------------
    // Basic Methods
    // ----------------------------------------------------------------------
    /*
    public void attack() {
        System.out.println("Is attacking");
    };

    public void sattack() {
        System.out.println("Is attacking");
    };

    public void defend() {
        System.out.println("Is defending");
    };

    public void takeAction() {
        System.out.println("Takes action");
    };
    */
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

}
