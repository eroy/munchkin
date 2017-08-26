package sergey.zhuravel.munchkin.ui.start;


import android.util.Log;

import sergey.zhuravel.munchkin.model.Player;

public class StartPresenter implements StartContract.Presenter {

    private StartContract.View mView;
    private StartContract.Model mModel;

    public StartPresenter(StartContract.View mView, StartContract.Model mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }


    @Override
    public void addMunchkin(Player player) {
        mModel.addMunchkin(player);
        checkCountMunchkin();
    }

    @Override
    public void getAllMunchkin() {
        mModel.getMunchkin()
                .filter(playerList -> playerList.size() > 0)
                .subscribe(players -> mView.addPlayersToAdapter(players),
                        e -> Log.e("Start", e.getMessage()));

        checkCountMunchkin();
    }

    @Override
    public void deleteMunchkin(int id) {
        mModel.deleteMunchkin(id);
        checkCountMunchkin();
    }

    @Override
    public void editMunchkin(Player player) {
        mView.showDialogEditMunchkin(player);
    }

    private void checkCountMunchkin() {
        if (mModel.getCountMunchkin() == 0) {
            mView.showMessageAvailable(true);
        } else {
            mView.showMessageAvailable(false);
        }
    }

    @Override
    public void onClickFight() {
        if (mModel.getCountMunchkin() > 1) {
            mView.navigateToFight();
        }
        else {
            mView.showErrorMessage();
        }
    }

    @Override
    public void onDestroy() {
        mView = null;
        mModel = null;
    }
}
