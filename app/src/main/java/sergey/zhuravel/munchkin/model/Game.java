package sergey.zhuravel.munchkin.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Game implements Parcelable {

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
    private List<PlayerFight> playerFightList;

    protected Game(Parcel in) {
        playerFightList = in.createTypedArrayList(PlayerFight.CREATOR);
    }

    public Game(List<PlayerFight> playerFightList) {
        this.playerFightList = playerFightList;
    }

    public List<PlayerFight> getPlayerFightList() {
        return playerFightList;
    }

    public void setPlayerFightList(List<PlayerFight> playerFightList) {
        this.playerFightList = playerFightList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeTypedList(playerFightList);
    }
}
