package sergey.zhuravel.munchkin.ui.setting;


public interface SettingContract {

    interface Model {
        int getMaxLevelFight();

        void setMaxLevelFight(int maxLevel);

    }

    interface View {
        void setTextLevel(int level);

    }

    interface Presenter {
        void onDestroy();

        int getMaxLevel();

        void editLevel(int level);
    }


}
