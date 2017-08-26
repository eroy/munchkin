package sergey.zhuravel.munchkin.model;


public class Player {

    private String name;
    private int level;
    private int strength;
    private int summary;

    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.strength = 0;
        this.summary = this.level + this.strength;
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
        return summary;
    }

    public void setSummary(int summary) {
        this.summary = summary;
    }
}
