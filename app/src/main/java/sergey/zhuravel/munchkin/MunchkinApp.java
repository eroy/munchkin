package sergey.zhuravel.munchkin;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import sergey.zhuravel.munchkin.manager.RealmManager;
import sergey.zhuravel.munchkin.manager.SettingManager;


public class MunchkinApp extends Application {
    private static RealmManager sRealmManager;
    private static SettingManager sSettingManager;

    public static RealmManager getRealmManager() {
        return sRealmManager;
    }

    public static SettingManager getSettingManager(Context context) {
        if (sSettingManager == null) {
            sSettingManager = new SettingManager(context);
        }
        return sSettingManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration configuration = new RealmConfiguration
                .Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

        sRealmManager = new RealmManager();
    }
}
