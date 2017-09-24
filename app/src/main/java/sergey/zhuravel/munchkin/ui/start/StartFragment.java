package sergey.zhuravel.munchkin.ui.start;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import sergey.zhuravel.munchkin.MunchkinApp;
import sergey.zhuravel.munchkin.R;
import sergey.zhuravel.munchkin.constant.Constant;
import sergey.zhuravel.munchkin.model.Player;
import sergey.zhuravel.munchkin.ui.base.BaseFragment;
import sergey.zhuravel.munchkin.ui.fight.FightFragment;
import sergey.zhuravel.munchkin.ui.setting.SettingFragment;


public class StartFragment extends BaseFragment implements StartContract.View {

    private RecyclerView mRwPlayers;
    private Toolbar mToolbar;
    private StartContract.Presenter mPresenter;
    private StartAdapter mStartAdapter;
    private TextView mTvMessage;
    private ImageView mIvFight;
    private Button mBtnFightFight;
    private AlertDialog mAlertDialogFight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        initView(view);

        mPresenter = new StartPresenter(this,
                new StartModel(MunchkinApp.getRealmManager(), MunchkinApp.getSettingManager(getActivity())));

        mStartAdapter = new StartAdapter(mPresenter);
        mRwPlayers.setAdapter(mStartAdapter);

        mPresenter.getAllMunchkin();

        mIvFight.setOnClickListener(v -> mPresenter.onClickFight());

        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initToolbar(mToolbar, getString(R.string.app_name), false);

        mRwPlayers = (RecyclerView) view.findViewById(R.id.rwPlayers);
        mRwPlayers.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRwPlayers.setItemAnimator(new DefaultItemAnimator());

        mTvMessage = (TextView) view.findViewById(R.id.tvMessage);
        mIvFight = (ImageView) view.findViewById(R.id.ivFight);

        int[] imgFight = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
                R.drawable.img6, R.drawable.img7, R.drawable.img8};

        mIvFight.setImageResource(imgFight[new Random().nextInt(8)]);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_start, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addMunchkin:
                showDialogAddMunchkin();
                break;
            case R.id.settings:
                navigateToNextFragmentBack(new SettingFragment());
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void showDialogAddMunchkin() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.dialog_add_munchkin);
        View viewDialog = getActivity().getLayoutInflater().inflate(R.layout.dialog_munchkin, null);
        dialog.setView(viewDialog);

        AppCompatEditText etName = (AppCompatEditText) viewDialog.findViewById(R.id.et_name);


        dialog.setPositiveButton(R.string.add, (dialog1, which) -> {
            Player player = new Player(etName.getText().toString());
            mPresenter.addMunchkin(player);
            addItemToAdapter(player);


        });

        dialog.setNegativeButton(R.string.cancel, (dialog1, which) ->
                dialog1.dismiss());


        dialog
                .setCancelable(false)
                .create()
                .show();


    }

    @Override
    public void showDialogEditMunchkin(Player player) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.dialog_edit_munchkin);
        View viewDialog = getActivity().getLayoutInflater().inflate(R.layout.dialog_munchkin, null);
        dialog.setView(viewDialog);

        AppCompatEditText etName = (AppCompatEditText) viewDialog.findViewById(R.id.et_name);
        etName.setText(player.getName());

        dialog.setPositiveButton(R.string.edit, (dialog1, which) -> {
            Player editPlayer = new Player();
            editPlayer.setName(etName.getText().toString());
            editPlayer.setId(player.getId());
            mPresenter.addMunchkin(editPlayer);
            mPresenter.getAllMunchkin();
        });

        dialog.setNegativeButton(R.string.cancel, (dialog1, which) ->
                dialog1.dismiss());

        dialog.setNeutralButton(R.string.delete, (dialog1, which) -> {
            deleteItemFromAdapter(player);
            mPresenter.deleteMunchkin(player.getId());

        });


        dialog
                .setCancelable(false)
                .create()
                .show();

    }

    @Override
    public void showDialogLevelMaxFight() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View viewDialog = getActivity().getLayoutInflater().inflate(R.layout.dialog_max_level, null);
        builder.setView(viewDialog);

        mBtnFightFight = (Button) viewDialog.findViewById(R.id.btnFight);
        Button btnCancel = (Button) viewDialog.findViewById(R.id.btnCancel);


        String text = getString(R.string.max_level_fight_is) + " <b>" +
                String.valueOf(mPresenter.getMaxLevelFight()) +
                "</b>" + "<br/><br/>" + getString(R.string.you_can_change_ml);

        builder.setMessage(Html.fromHtml(text));

        mAlertDialogFight = builder.create();


        mPresenter.setTimeFight(Constant.TIME_FIGHT);


        mBtnFightFight.setOnClickListener(v -> {
            mPresenter.unSubscribeTimer();
            navigateToFight();
            mAlertDialogFight.dismiss();
        });
        btnCancel.setOnClickListener(v -> {
            mPresenter.unSubscribeTimer();
            mAlertDialogFight.dismiss();
        });


        mAlertDialogFight.setCancelable(false);
        mAlertDialogFight.show();

    }

    @Override
    public void setDismissDialogLevel() {
        mAlertDialogFight.dismiss();
    }

    @Override
    public void setTextButton(long count) {
        mBtnFightFight.setText(getString(R.string.dialog_level_fight) + " " + count);
    }

    @Override
    public void addItemToAdapter(Player player) {
        mStartAdapter.addPlayer(player);
    }

    public void deleteItemFromAdapter(Player player) {
        mStartAdapter.deletePlayer(player);


    }

    @Override
    public void navigateToFight() {
        navigateToNextFragment(new FightFragment());
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(getView(), R.string.error_message_person, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void addPlayersToAdapter(List<Player> playerList) {
        mStartAdapter.addPlayers(playerList);

    }


    @Override
    public void showMessageAvailable(boolean show) {
        if (show) {
            mTvMessage.setVisibility(View.VISIBLE);
        } else {
            mTvMessage.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
