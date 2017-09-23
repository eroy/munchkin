package sergey.zhuravel.munchkin.win;


import java.util.List;

import sergey.zhuravel.munchkin.model.PlayerFight;

public interface WinContract {

    interface Model {


    }

    interface View {

        void setImageWin();
        void setImageDraw();

        void setTextPlayerWin(String winPlayer);
        void setTextDraw();

    }

    interface Presenter {

        void onDestroy();
        void setWinPlayer(List<PlayerFight> mPlayerFightList);



    }
}
