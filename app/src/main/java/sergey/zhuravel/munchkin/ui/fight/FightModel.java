package sergey.zhuravel.munchkin.ui.fight;

import java.util.List;

import rx.Observable;
import sergey.zhuravel.munchkin.manager.RealmManager;
import sergey.zhuravel.munchkin.manager.SettingManager;
import sergey.zhuravel.munchkin.model.Player;



public class FightModel implements FightContract.Model {

    private RealmManager mRealmManager;
    private SettingManager mSettingManager;

    public FightModel(RealmManager mRealmManager,SettingManager mSettingManager) {
        this.mRealmManager = mRealmManager;
        this.mSettingManager = mSettingManager;
    }

    @Override
    public Observable<List<Player>> getMunchkin() {
        return mRealmManager.getMunchkin();
    }
    @Override
    public int getMaxLevelFight() {
        return mSettingManager.getMaxLevel();
    }
}
