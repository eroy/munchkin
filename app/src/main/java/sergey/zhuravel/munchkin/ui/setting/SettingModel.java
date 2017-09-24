package sergey.zhuravel.munchkin.ui.setting;


import sergey.zhuravel.munchkin.manager.SettingManager;

public class SettingModel implements SettingContract.Model{
    private SettingManager mSettingManager;

    public SettingModel(SettingManager mSettingManager) {
        this.mSettingManager = mSettingManager;
    }

    @Override
    public int getMaxLevelFight() {
        return mSettingManager.getMaxLevel();
    }

    @Override
    public void setMaxLevelFight(int maxLevel) {
        mSettingManager.saveMaxLevel(maxLevel);
    }

}
