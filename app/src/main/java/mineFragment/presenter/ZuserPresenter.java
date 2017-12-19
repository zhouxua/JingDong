package mineFragment.presenter;


import mineFragment.bean.User;
import mineFragment.bean.zhuceBean;
import mineFragment.model.ZuserModel;
import mineFragment.view.zview;

/**
 * 周旋
 * 2017/11/13  20:27
 */

public class ZuserPresenter implements ZuserModel.OnFinish{
    private final zview userView;
    private final ZuserModel userMode;

    public ZuserPresenter(zview iview) {
        this.userView = iview;
        this.userMode = new ZuserModel(this);
    }
    public void login(final User user) {
        userMode.login(user);
    }

    @Override
    public void OnFinishListener(zhuceBean zhuceBean) {
        String code = zhuceBean.getCode();
        if (code.equals("0")) {
            userView.onLoginSuccess();
        } else {
            userView.onLoginFailed("请求失败");

        }
    }
}
