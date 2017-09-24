package sergey.zhuravel.munchkin.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import sergey.zhuravel.munchkin.R;
import sergey.zhuravel.munchkin.ui.base.BaseActivity;
import sergey.zhuravel.munchkin.ui.start.StartFragment;

public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        replaceFragment(new StartFragment());
    }


    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_start, fragment).commit();
    }


}
