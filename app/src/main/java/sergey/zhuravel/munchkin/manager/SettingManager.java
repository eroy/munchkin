package sergey.zhuravel.munchkin.manager;


import android.content.Context;
import android.content.SharedPreferences;

public class SettingManager {

    public static final String APP_PREFERENCES = "preferences";
    public static final String MAX_LEVEL = "maxLevel";


    private SharedPreferences mSharedPreferences;

    private int mMaxLevel;

    public SettingManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mMaxLevel = mSharedPreferences.getInt(MAX_LEVEL, 10);
    }

    public int getMaxLevel() {
        return mMaxLevel;
    }

    public void saveMaxLevel(int maxLevel) {
        this.mMaxLevel = maxLevel;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(MAX_LEVEL, maxLevel);
        editor.apply();
    }
}
