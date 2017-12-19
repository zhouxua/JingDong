package mineFragment.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.jingdong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mineFragment.bean.User;
import mineFragment.bean.dengluBean;
import mineFragment.presenter.UserPresenter;
import mineFragment.view.Iview;

public class Denglu_yemian extends AppCompatActivity implements Iview{

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_denglu)
    Button btnDenglu;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    private UserPresenter userPresenter;
    private String username;
    private String password;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu_yemian2);
        ButterKnife.bind(this);
         userPresenter = new UserPresenter(this);


    }


    @OnClick({R.id.btn_denglu, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_denglu:
                username = etUserName.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                userPresenter.login(new User(username, password));
                break;
            case R.id.tv_register:
                Intent intent = new Intent(Denglu_yemian.this, Register_yemian.class);
                startActivity(intent);

                break;
        }
    }

    @Override
    public void onLoginSuccess(dengluBean deng) {
        String uid = deng.getData().getUid();
        sp = getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        sp.edit().putString("uid", uid).commit();
     Toast.makeText(Denglu_yemian.this,"登录成功，三hao秒后跳转",Toast.LENGTH_SHORT).show();
      new Thread(new Runnable() {
          @Override
          public void run() {
              try {
                  Thread.sleep(300);
                  Denglu_yemian.this.finish();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }).start();
    }

    @Override
    public void onLoginFailed(String error) {
        Toast.makeText(Denglu_yemian.this,"登录失败",Toast.LENGTH_SHORT).show();

    }
}
