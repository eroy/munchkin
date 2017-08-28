package sergey.zhuravel.munchkin.model;

import android.os.Parcel;
import android.os.Parcelable;

import sergey.zhuravel.munchkin.Utils;


public class PlayerFight implements Parcelable {
    public static final Creator<PlayerFight> CREATOR = new Creator<PlayerFight>() {
        @Override
        public PlayerFight createFromParcel(Parcel in) {
            return new PlayerFight(in);
        }

        @Override
        public PlayerFight[] newArray(int size) {
            return new PlayerFight[size];
        }
    };
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

    protected PlayerFight(Parcel in) {
        name = in.readString();
        level = in.readInt();
        strength = in.readInt();
        summary = in.readInt();
        id = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(level);
        dest.writeInt(strength);
        dest.writeInt(summary);
        dest.writeInt(id);
    }
}
