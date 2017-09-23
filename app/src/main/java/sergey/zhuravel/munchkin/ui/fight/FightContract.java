package sergey.zhuravel.munchkin.ui.fight;


import java.util.List;

import rx.Observable;
import sergey.zhuravel.munchkin.model.Player;
import sergey.zhuravel.munchkin.model.PlayerFight;

public interface FightContract {

    interface Model {
        Observable<List<Player>> getMunchkin();

        int getMaxLevelFight();

    }

    interface View {
        void addPlayersToAdapter(List<PlayerFight> playerList);

        PlayerFight getCurrentPlayerFight();

        void notifyFightAdapter();

        void showDialogEndFight();

        void showErrorMinusLevelMessage();

        void setTextSubtitle(String name, int level, int strength);


    }

    interface Presenter {
        void onDestroy();

        void getAllMunchkin();

        void processingOptionsPlus(String type, String operation, int indexCount);

        void setTextSubTitle(PlayerFight player);
        void cancelEndGame();

    }
}
