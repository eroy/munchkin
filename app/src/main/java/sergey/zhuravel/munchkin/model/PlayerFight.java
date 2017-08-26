package sergey.zhuravel.munchkin.model;

import sergey.zhuravel.munchkin.Utils;


public class PlayerFight {
    private String name;
    private int level;
    private int strength;
    private int summary;
    private int id;

    public PlayerFight() {
    }

    public PlayerFight(String name) {
        this.name = name;
        this.level = 1;
        this.strength = 0;
        this.summary = this.level + this.strength;
        this.id = Utils.generateId();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSummary() {
        return this.level + this.strength;
    }

    public void setSummary(int summary) {
        this.summary = summary;
    }
}
