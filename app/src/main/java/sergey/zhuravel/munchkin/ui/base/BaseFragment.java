package sergey.zhuravel.munchkin.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import sergey.zhuravel.munchkin.R;
import sergey.zhuravel.munchkin.constant.Constant;
import sergey.zhuravel.munchkin.model.Game;


public class BaseFragment extends Fragment {

    public void initToolbar(Toolbar mToolbar, String title, boolean homeEnable) {

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(homeEnable);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(homeEnable);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
//            mToolbar.setTitleTextColor(getResources().getColor(R.color.textPrimary));
//            mToolbar.setSubtitleTextColor(getResources().getColor(R.color.textPrimary));

        }

    }
    public void navigateToNextFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_start, fragment).commit();

    }

    public void navigateToNextFragmentBack(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_start, fragment)
                .addToBackStack(null).commit();

    }

    public void navigateToNextFragmentBundle(Fragment fragment, Game game) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.LIST_PLAYERS, game);
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_start, fragment).commit();

    }

    public void setTextSubTitle(String textSubTitle) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(textSubTitle);
    }

}
