package mineFragment.bean;

/**
 * 周旋
 * 2017/11/13  19:04
 */

public class User {
    private String mobile;
    private String password;

    public User(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
