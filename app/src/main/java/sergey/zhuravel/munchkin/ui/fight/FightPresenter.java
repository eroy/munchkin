package sergey.zhuravel.munchkin.ui.fight;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import sergey.zhuravel.munchkin.constant.Constant;
import sergey.zhuravel.munchkin.model.PlayerFight;

public class FightPresenter implements FightContract.Presenter {

    private FightContract.View mView;
    private FightContract.Model mModel;

    private List<PlayerFight> mPlayerFightList;


    public FightPresenter(FightContract.View mView, FightContract.Model mModel) {
        this.mView = mView;
        this.mModel = mModel;

        mPlayerFightList = new ArrayList<>();
    }

    @Override
    public void getAllMunchkin() {
        mModel.getMunchkin()
                .filter(playerList -> playerList.size() > 0)
                .subscribe(players -> {
                            Observable.from(players).subscribe(player -> {
                                mPlayerFightList.add(new PlayerFight(player.getName()));
                            });

                            mView.addPlayersToAdapter(mPlayerFightList);
                        },
                        e -> Log.e("Start", e.getMessage()));

    }

    @Override
    public void setTextSubTitle(PlayerFight playerFight) {
        mView.setTextSubtitle(playerFight.getName(), playerFight.getLevel(), playerFight.getStrength());

    }


    @Override
    public void processingOptionsPlus(String type, String operation, int indexCount) {
        PlayerFight currentPlayerFight = mView.getCurrentPlayerFight();

        switch (operation) {
            case Constant.OPERATION_PLUS:
                if (type.equals(Constant.TYPE_LEVEL)) {
                    if (currentPlayerFight.getLevel() == (mModel.getMaxLevelFight() - 1)) {
                        currentPlayerFight.setLevel(currentPlayerFight.getLevel() + indexCount);
                        mView.showDialogEndFight();
                    } else {
                        currentPlayerFight.setLevel(currentPlayerFight.getLevel() + indexCount);
                    }

                } else {
                    currentPlayerFight.setStrength(currentPlayerFight.getStrength() + indexCount);
                }
                currentPlayerFight.setListSummary();
                break;
            case Constant.OPERATION_MINUS:
                if (type.equals(Constant.TYPE_LEVEL)) {
                    if (currentPlayerFight.getLevel() == 1) {
                        mView.showErrorMinusLevelMessage();
                    } else {
                        currentPlayerFight.setLevel(currentPlayerFight.getLevel() - indexCount);
                    }

                } else {
                    if (indexCount == 0) {
                        currentPlayerFight.setStrength(indexCount);
                    } else {
                        currentPlayerFight.setStrength(currentPlayerFight.getStrength() - indexCount);
                    }
                }
                currentPlayerFight.setListSummary();
                break;
        }


        mView.notifyFightAdapter();
    }

    @Override
    public void cancelEndGame() {
        PlayerFight currentPlayerFight = mView.getCurrentPlayerFight();
        if (currentPlayerFight.getLevel() == (mModel.getMaxLevelFight())) {
            currentPlayerFight.setLevel(currentPlayerFight.getLevel() - 1);
        }
        mView.notifyFightAdapter();
    }

    @Override
    public void onDestroy() {
        mView = null;
        mModel = null;

    }
}
