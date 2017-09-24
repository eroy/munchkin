package sergey.zhuravel.munchkin.ui.start;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sergey.zhuravel.munchkin.model.Player;

public class StartPresenter implements StartContract.Presenter {

    private StartContract.View mView;
    private StartContract.Model mModel;
    private Subscription subscription;
    private int mTime = 3;

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
            mView.showDialogLevelMaxFight();
        } else {
            mView.showErrorMessage();
        }
    }

    @Override
    public int getMaxLevelFight() {
        return mModel.getMaxLevelFight();
    }



    @Override
    public void setTimeFight(int timeFight) {
        mTime = timeFight;
        mView.setTextButton(timeFight);
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(timeFight)
                .doOnCompleted(() -> {
                    mView.navigateToFight();
                    mView.setDismissDialogLevel();
                })
                .subscribe(aLong -> mView.setTextButton(--mTime));
    }


    public void unSubscribeTimer() {
        subscription.unsubscribe();
    }

    @Override
    public void onDestroy() {
        mView = null;
        mModel = null;
    }
}
