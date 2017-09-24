package sergey.zhuravel.munchkin.ui.setting;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import sergey.zhuravel.munchkin.MunchkinApp;
import sergey.zhuravel.munchkin.R;
import sergey.zhuravel.munchkin.ui.base.BaseFragment;


public class SettingFragment extends BaseFragment implements SettingContract.View {

    private Toolbar mToolbar;
    private SettingContract.Presenter mPresenter;
    private LinearLayout mLlLevel;
    private LinearLayout mLlCreator;
    private LinearLayout mLlWebsite;
    private LinearLayout mLlVersion;
    private TextView mTextLevel;


    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(view);

        mPresenter = new SettingPresenter(this, new SettingModel(MunchkinApp.getSettingManager(getActivity())));

        setTextLevel(mPresenter.getMaxLevel());

        onClick();


        return view;
    }

    private void onClick() {
        mLlLevel.setOnClickListener(v -> showDialogEditLevel());
        mLlCreator.setOnClickListener(v -> showSnackMessage(getString(R.string.sergey_zhuravel)));
        mLlWebsite.setOnClickListener(v -> {
            String uri = "http://" + getString(R.string.app_website);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
        });
        mLlVersion.setOnClickListener(v -> showSnackMessage(getString(R.string.app_version)));
    }


    private void showSnackMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initToolbar(mToolbar, getString(R.string.settings), true);

        mLlLevel = (LinearLayout) view.findViewById(R.id.llLevel);
        mLlCreator = (LinearLayout) view.findViewById(R.id.llCreator);
        mLlWebsite = (LinearLayout) view.findViewById(R.id.llWebsite);
        mLlVersion = (LinearLayout) view.findViewById(R.id.llVersion);
        mTextLevel = (TextView) view.findViewById(R.id.textMaxLevel);


    }


    private void showDialogEditLevel() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.dialog_set_max_level);
        View viewDialog = getActivity().getLayoutInflater().inflate(R.layout.dialog_set_level_max, null);
        dialog.setView(viewDialog);

        AppCompatEditText etLevel = (AppCompatEditText) viewDialog.findViewById(R.id.et_level);
        etLevel.setText(String.valueOf(mPresenter.getMaxLevel()));

        dialog.setPositiveButton(R.string.set, (dialog1, which) -> {
            mPresenter.editLevel(Integer.parseInt(etLevel.getText().toString()));
        });

        dialog.setNegativeButton(R.string.cancel, (dialog1, which) ->
                dialog1.dismiss());


        dialog
                .setCancelable(false)
                .create()
                .show();

    }

    @Override
    public void setTextLevel(int level) {
        mTextLevel.setText(String.valueOf(level));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }


}
