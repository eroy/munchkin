package sergey.zhuravel.munchkin.win;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sergey.zhuravel.munchkin.R;
import sergey.zhuravel.munchkin.constant.Constant;
import sergey.zhuravel.munchkin.model.Game;
import sergey.zhuravel.munchkin.model.PlayerFight;
import sergey.zhuravel.munchkin.ui.base.BaseFragment;
import sergey.zhuravel.munchkin.ui.start.StartFragment;


public class WinFragment extends BaseFragment implements WinContract.View {

    private WinContract.Presenter mPresenter;
    private ImageView mImgWin;
    private TextView mTvWin;
    private List<PlayerFight> mPlayerFightList;
    private Game mGame;

    private Button mBtnReply;
    private Button mBtnEnd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_win, container, false);

        initView(view);
        mPresenter = new WinPresenter(this, new WinModel());
        mPlayerFightList = new ArrayList<>();

        mGame = getArguments().getParcelable(Constant.LIST_PLAYERS);
        mPlayerFightList.addAll(mGame.getPlayerFightList());


        mPresenter.setWinPlayer(mPlayerFightList);


        mBtnReply.setOnClickListener(v -> navigateToNextFragment(new StartFragment()));
        mBtnEnd.setOnClickListener(v -> getActivity().finish());

        return view;
    }

    private void initView(View view) {
        mImgWin = (ImageView) view.findViewById(R.id.imWin);
        mTvWin = (TextView) view.findViewById(R.id.tvWin);
        mBtnReply = (Button) view.findViewById(R.id.btnReply);
        mBtnEnd = (Button) view.findViewById(R.id.btnEnd);


    }

    @Override
    public void setImageWin() {
        int[] imgWin = {R.drawable.pic_win2, R.drawable.pic_win3, R.drawable.pic_win4, R.drawable.pic_win5};
        mImgWin.setImageResource(imgWin[new Random().nextInt(4)]);
    }

    @Override
    public void setImageDraw() {
        mImgWin.setImageResource(R.drawable.pic_win_draw);
    }

    @Override
    public void setTextPlayerWin(String winPlayer) {
        mTvWin.setText(getString(R.string.player_win) + " " + winPlayer + " " + getString(R.string.won_win));
    }

    @Override
    public void setTextDraw() {
        mTvWin.setText(R.string.draw);
    }


    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
