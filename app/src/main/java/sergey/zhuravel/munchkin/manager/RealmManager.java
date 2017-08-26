package sergey.zhuravel.munchkin.manager;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import sergey.zhuravel.munchkin.model.Player;

public class RealmManager {

    private Realm mRealm;

    public RealmManager() {
        mRealm = Realm.getDefaultInstance();
    }

    public Realm getRealm() {
        return mRealm;
    }

    public void addMunchkin(Player player) {
        mRealm.executeTransaction(realm -> mRealm.copyToRealmOrUpdate(player));
    }

    public Observable<List<Player>> getMunchkin() {
        return Observable.fromCallable(() -> mRealm.where(Player.class).findAll());

    }

    public int getCountMunchkin() {
        return mRealm.where(Player.class).findAll().size();
    }

    public void deleteMunchkin(int id) {
        mRealm.executeTransaction(realm -> {
            RealmResults<Player> results = realm.where(Player.class).equalTo("id", id).findAll();
            results.deleteAllFromRealm();
        });
    }

}
