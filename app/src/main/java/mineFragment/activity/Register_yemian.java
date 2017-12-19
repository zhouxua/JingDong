package mineFragment.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.jingdong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mineFragment.bean.User;
import mineFragment.presenter.ZuserPresenter;
import mineFragment.view.zview;

public class Register_yemian extends AppCompatActivity implements zview{

    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_denglu)
    Button btnDenglu;
    private ZuserPresenter userPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_yemian);
        ButterKnife.bind(this);
        userPresenter = new ZuserPresenter(this);
    }

    @OnClick(R.id.btn_denglu)
    public void onViewClicked() {
        String username = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        userPresenter.login(new User(username, password));
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(Register_yemian.this, "注册成功:", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onLoginFailed(String error) {
        Toast.makeText(Register_yemian.this, "注册失败:", Toast.LENGTH_SHORT).show();

    }
}
