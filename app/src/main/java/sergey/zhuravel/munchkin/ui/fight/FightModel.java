package sergey.zhuravel.munchkin.ui.fight;

import java.util.List;

import rx.Observable;
import sergey.zhuravel.munchkin.manager.RealmManager;
import sergey.zhuravel.munchkin.model.Player;

/**
 * Created by serj on 8/26/17.
 */

public class FightModel implements FightContract.Model {

    private RealmManager mRealmManager;

    public FightModel(RealmManager mRealmManager) {
        this.mRealmManager = mRealmManager;
    }

    @Override
    public Observable<List<Player>> getMunchkin() {
        return mRealmManager.getMunchkin();
    }
}
