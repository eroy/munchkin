package sergey.zhuravel.munchkin.ui.start;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sergey.zhuravel.munchkin.R;
import sergey.zhuravel.munchkin.ui.base.BaseFragment;


public class StartFragment extends BaseFragment implements StartContract.View {

    private RecyclerView mRwPlayers;
    private Toolbar mToolbar;
    private StartContract.Presenter mPresenter;


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




        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initToolbar(mToolbar, getString(R.string.app_name), false);

        mRwPlayers = (RecyclerView) view.findViewById(R.id.rwPlayers);


    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
