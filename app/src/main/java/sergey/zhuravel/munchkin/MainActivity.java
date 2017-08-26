package sergey.zhuravel.munchkin;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import sergey.zhuravel.munchkin.ui.base.BaseActivity;
import sergey.zhuravel.munchkin.ui.start.StartFragment;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(new StartFragment());
    }


    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_start, fragment).commit();
    }

}
