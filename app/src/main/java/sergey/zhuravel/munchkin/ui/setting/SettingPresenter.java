package sergey.zhuravel.munchkin.ui.setting;


public class SettingPresenter implements SettingContract.Presenter {

    private SettingContract.View mView;
    private SettingContract.Model mModel;

    public SettingPresenter(SettingContract.View mView, SettingContract.Model mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }



    public void editLevel(int level) {
        mModel.setMaxLevelFight(level);
        mView.setTextLevel(level);
    }

    @Override
    public int getMaxLevel(){
        return mModel.getMaxLevelFight();
    }


    @Override
    public void onDestroy() {
        mView = null;
        mModel = null;
    }
}
