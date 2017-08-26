package sergey.zhuravel.munchkin;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import sergey.zhuravel.munchkin.manager.RealmManager;


public class MunchkinApp extends Application {
    private static RealmManager sRealmManager;

    public static RealmManager getRealmManager() {
        return sRealmManager;
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
