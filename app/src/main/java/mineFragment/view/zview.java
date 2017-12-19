package mineFragment.view;

/**
 * 周旋
 * 2017/11/13  20:24
 */

public interface zview {
    /**
     * 登录成功
     */
    void onLoginSuccess();

    /**
     * 登录失败
     *
     * @param error
     */
    void onLoginFailed(String error);
}
