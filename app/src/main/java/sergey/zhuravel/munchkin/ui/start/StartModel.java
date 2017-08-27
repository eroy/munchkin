package sergey.zhuravel.munchkin.ui.start;


import java.util.List;

import rx.Observable;
import sergey.zhuravel.munchkin.manager.RealmManager;
import sergey.zhuravel.munchkin.manager.SettingManager;
import sergey.zhuravel.munchkin.model.Player;

public class StartModel implements StartContract.Model {
    private RealmManager mRealmManager;
    private SettingManager mSettingManager;

    public StartModel(RealmManager mRealmManager,SettingManager mSettingManager) {
        this.mRealmManager = mRealmManager;
        this.mSettingManager = mSettingManager;
    }


    @Override
    public void addMunchkin(Player player) {
        mRealmManager.addMunchkin(player);
    }

    @Override
    public Observable<List<Player>> getMunchkin() {
        return mRealmManager.getMunchkin();
    }

    @Override
    public void deleteMunchkin(int id) {
        mRealmManager.deleteMunchkin(id);
    }

    @Override
    public int getCountMunchkin() {
        return mRealmManager.getCountMunchkin();
    }

    @Override
    public int getMaxLevelFight() {
        return mSettingManager.getMaxLevel();
    }

    @Override
    public void setMaxLevelFight(int maxLevel) {
        mSettingManager.saveMaxLevel(maxLevel);
    }

}
