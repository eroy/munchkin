package sergey.zhuravel.munchkin.ui.base;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import sergey.zhuravel.munchkin.R;


public class BaseFragment extends Fragment {

    public void initToolbar(Toolbar mToolbar, String title, boolean homeEnable) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(homeEnable);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(homeEnable);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        }

    }
    public void navigateToNextFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_start, fragment).addToBackStack(null).commit();

    }
}
