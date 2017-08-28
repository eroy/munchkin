package sergey.zhuravel.munchkin.win;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import sergey.zhuravel.munchkin.model.PlayerFight;

public class WinPresenter implements WinContract.Presenter {

    private WinContract.View mView;
    private WinContract.Model mModel;


    public WinPresenter(WinContract.View mView, WinContract.Model mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }


    @Override
    public void setWinPlayer(List<PlayerFight> playerFightList) {
        List<Integer> integerList = new ArrayList<>();
        Observable.from(playerFightList).map(PlayerFight::getLevel).subscribe(integerList::add);
        int maxLevel = Collections.max(integerList);

        List<String> listName = new ArrayList<>();
        Observable.from(playerFightList).filter(playerFight -> playerFight.getLevel() == maxLevel)
                .map(PlayerFight::getName)
                .subscribe(listName::add);

        if (listName.size() == 1) {
            mView.setTextPlayerWin(listName.get(0));
            mView.setImageWin();
        }
        else {
            mView.setTextDraw();
            mView.setImageDraw();
        }
    }



    @Override
    public void navigateToFinish() {
        Observable
                .timer(3, TimeUnit.SECONDS)
                .subscribe(aLong -> mView.navigateToFinish());


    }

    @Override
    public void onDestroy() {
        mView = null;
        mModel = null;
    }
}
