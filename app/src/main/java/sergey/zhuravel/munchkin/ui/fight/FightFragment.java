package sergey.zhuravel.munchkin.ui.fight;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sergey.zhuravel.munchkin.MunchkinApp;
import sergey.zhuravel.munchkin.R;
import sergey.zhuravel.munchkin.constant.Constant;
import sergey.zhuravel.munchkin.model.PlayerFight;
import sergey.zhuravel.munchkin.ui.base.BaseFragment;

public class FightFragment extends BaseFragment implements FightContract.View {

    private FightContract.Presenter mPresenter;
    private Toolbar mToolbar;
    private RecyclerView mRwPlayers;
    private FightAdapter mFightAdapter;


    private FloatingActionButton mFabLevelPlus;
    private FloatingActionButton mFabLevelDPlus;
    private FloatingActionButton mFabLevelMinus;
    private FloatingActionButton mFabLevelDMinus;
    private FloatingActionButton mFabStrengthPlus;
    private FloatingActionButton mFabStrengthDPlus;
    private FloatingActionButton mFabStrengthMinus;
    private FloatingActionButton mFabStrengthDMinus;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fight, container, false);
        initView(view);

        mPresenter = new FightPresenter(this, new FightModel(MunchkinApp.getRealmManager()));

        mFightAdapter = new FightAdapter(mPresenter, getActivity());
        mRwPlayers.setAdapter(mFightAdapter);

        mPresenter.getAllMunchkin();

        onClickFab();

        return view;
    }

    private void onClickFab() {
        mFabLevelPlus.setOnClickListener(v ->
                mPresenter.processingOptionsPlus(Constant.TYPE_LEVEL, Constant.OPERATION_PLUS, 1));
        mFabLevelMinus.setOnClickListener(v ->
                mPresenter.processingOptionsPlus(Constant.TYPE_LEVEL, Constant.OPERATION_MINUS, 1));
        mFabStrengthPlus.setOnClickListener(v ->
                mPresenter.processingOptionsPlus(Constant.TYPE_STRENGTH, Constant.OPERATION_PLUS, 1));
        mFabStrengthMinus.setOnClickListener(v ->
                mPresenter.processingOptionsPlus(Constant.TYPE_STRENGTH, Constant.OPERATION_MINUS, 1));

        mFabLevelDPlus.setOnClickListener(v -> showDialogDPlus(Constant.TYPE_LEVEL));
        mFabLevelDMinus.setOnClickListener(v -> {

        });
        mFabStrengthDPlus.setOnClickListener(v -> showDialogDPlus(Constant.TYPE_STRENGTH));
        mFabStrengthDMinus.setOnClickListener(v -> {

        });
    }

    @Override
    public PlayerFight getCurrentPlayerFight() {
        return mFightAdapter.getCurrentPlayerFight();
    }

    @Override
    public void notifyFightAdapter() {
        mFightAdapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initToolbar(mToolbar, getString(R.string.app_name), false);

        mRwPlayers = (RecyclerView) view.findViewById(R.id.rwPlayers);
        mRwPlayers.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRwPlayers.setItemAnimator(new DefaultItemAnimator());

        mFabLevelPlus = (FloatingActionButton) view.findViewById(R.id.plusLevel);
        mFabLevelDPlus = (FloatingActionButton) view.findViewById(R.id.dPlusLevel);
        mFabLevelMinus = (FloatingActionButton) view.findViewById(R.id.minusLevel);
        mFabLevelDMinus = (FloatingActionButton) view.findViewById(R.id.dMinusLevel);
        mFabStrengthPlus = (FloatingActionButton) view.findViewById(R.id.plusStrength);
        mFabStrengthDPlus = (FloatingActionButton) view.findViewById(R.id.dPlusStrength);
        mFabStrengthMinus = (FloatingActionButton) view.findViewById(R.id.minusStrength);
        mFabStrengthDMinus = (FloatingActionButton) view.findViewById(R.id.dMinusStrength);


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fight, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.finishGame:

                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addPlayersToAdapter(List<PlayerFight> playerList) {
        mFightAdapter.addPlayers(playerList);

    }

    @Override
    public void showDialogEndFight() {
        Snackbar.make(getView(), "End game ?",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMinusLevelMessage() {
        Snackbar.make(getView(), R.string.error_message_level,Snackbar.LENGTH_SHORT).show();
    }

    private void showDialogDPlus(String type) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        View viewDialog = getActivity().getLayoutInflater().inflate(R.layout.dialog_dplus, null);
        dialog.setView(viewDialog);

        FloatingActionButton plusTwo = (FloatingActionButton) viewDialog.findViewById(R.id.plusTwo);
        FloatingActionButton plusThree = (FloatingActionButton) viewDialog.findViewById(R.id.plusThree);
        FloatingActionButton plusFour = (FloatingActionButton) viewDialog.findViewById(R.id.plusFour);
        FloatingActionButton plusFive = (FloatingActionButton) viewDialog.findViewById(R.id.plusFive);
        FloatingActionButton plusSix = (FloatingActionButton) viewDialog.findViewById(R.id.plusSix);
        FloatingActionButton plusSeven = (FloatingActionButton) viewDialog.findViewById(R.id.plusSeven);
        FloatingActionButton plusEight = (FloatingActionButton) viewDialog.findViewById(R.id.plusEight);
        FloatingActionButton plusNine = (FloatingActionButton) viewDialog.findViewById(R.id.plusNine);
        FloatingActionButton plusTen = (FloatingActionButton) viewDialog.findViewById(R.id.plusTen);

        plusTwo.setOnClickListener(v ->  mPresenter.processingOptionsPlus(type, Constant.OPERATION_PLUS, 2));
        plusThree.setOnClickListener(v ->  mPresenter.processingOptionsPlus(type, Constant.OPERATION_PLUS, 3));
        plusFour.setOnClickListener(v ->  mPresenter.processingOptionsPlus(type, Constant.OPERATION_PLUS, 4));
        plusFive.setOnClickListener(v ->  mPresenter.processingOptionsPlus(type, Constant.OPERATION_PLUS, 5));
        plusSix.setOnClickListener(v ->  mPresenter.processingOptionsPlus(type, Constant.OPERATION_PLUS, 6));
        plusSeven.setOnClickListener(v ->  mPresenter.processingOptionsPlus(type, Constant.OPERATION_PLUS, 7));
        plusEight.setOnClickListener(v ->  mPresenter.processingOptionsPlus(type, Constant.OPERATION_PLUS, 8));
        plusNine.setOnClickListener(v ->  mPresenter.processingOptionsPlus(type, Constant.OPERATION_PLUS, 9));
        plusTen.setOnClickListener(v ->  mPresenter.processingOptionsPlus(type, Constant.OPERATION_PLUS, 10));


        dialog
                .setCancelable(true)
                .create()
                .show();


    }




    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
