package sergey.zhuravel.munchkin.ui.start;


import java.util.List;

import rx.Observable;
import sergey.zhuravel.munchkin.manager.RealmManager;
import sergey.zhuravel.munchkin.model.Player;

public class StartModel implements StartContract.Model {
    private RealmManager mRealmManager;

    public StartModel(RealmManager mRealmManager) {
        this.mRealmManager = mRealmManager;
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

}
