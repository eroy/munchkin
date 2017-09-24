package sergey.zhuravel.munchkin.ui.start;


import java.util.List;

import rx.Observable;
import sergey.zhuravel.munchkin.model.Player;

public interface StartContract {

    interface Model {
        void addMunchkin(Player player);
        Observable<List<Player>> getMunchkin();
        void deleteMunchkin(int id);
        int getCountMunchkin();

        int getMaxLevelFight();
        void setMaxLevelFight(int maxLevel);

    }

    interface View {
        void addItemToAdapter(Player player);;
        void showDialogEditMunchkin(Player player);
        void showMessageAvailable(boolean show);
        void addPlayersToAdapter(List<Player> playerList);
        void navigateToFight();
        void showErrorMessage();
        void showDialogLevelMaxFight();


        void setTextButton(long count);
        void setDismissDialogLevel();
    }

    interface Presenter {
        void addMunchkin(Player player);
        void getAllMunchkin();
        void deleteMunchkin(int id);
        void editMunchkin(Player player);
        void onClickFight();

        void onDestroy();
        int getMaxLevelFight();

        void setTimeFight(int timeFight);

        void unSubscribeTimer();
    }
}
