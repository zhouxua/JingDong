package com.bwie.jingdong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import cartFragment.CartFragment;
import findFragment.FindFragment;
import homeFragment.HomeFragment;
import mineFragment.MineFragment;
import mineFragment.activity.Denglu_yemian;
import sortFragment.SortFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home_rb)
    RadioButton homeRb;
    @BindView(R.id.sort_rb)
    RadioButton sortRb;
    @BindView(R.id.find_rb)
    RadioButton findRb;
    @BindView(R.id.cart_rb)
    RadioButton cartRb;
    @BindView(R.id.mine_rb)
    RadioButton mineRb;

    FragmentManager fm;
    HomeFragment homeFragment;
    SortFragment sortFragment;
    FindFragment findFragment;
    CartFragment cartFragment;
    MineFragment mineFragment;
    private String name;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sp = getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        name = sp.getString("uid", null);

//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }


         homeFragment = new HomeFragment();
         sortFragment = new SortFragment();
         findFragment = new FindFragment();
         cartFragment = new CartFragment();
         mineFragment = new MineFragment();

        fm = getSupportFragmentManager();
       fm.beginTransaction().add(R.id.content, homeFragment).commit();
// .add(R.id.content, sortFragment).add(R.id.content, findFragment)
//                .add(R.id.content, cartFragment).add(R.id.content, mineFragment).commit();
//        fm.beginTransaction().show(homeFragment)
//                .hide(sortFragment).hide(findFragment).hide(cartFragment).hide(mineFragment).commit();

    }
    
    @OnClick({R.id.home_rb, R.id.sort_rb, R.id.find_rb, R.id.cart_rb, R.id.mine_rb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_rb:
               fm.beginTransaction().replace(R.id.content,homeFragment).commit();
//                fm.beginTransaction().show(homeFragment)
//                        .hide(sortFragment).hide(findFragment).hide(cartFragment).hide(mineFragment).commit();

                break;
            case R.id.sort_rb:

               fm.beginTransaction().replace(R.id.content,sortFragment).commit();
//                fm.beginTransaction().hide(homeFragment)
//                        .show(sortFragment).hide(findFragment).hide(cartFragment).hide(mineFragment).commit();

                break;
            case R.id.find_rb:

               fm.beginTransaction().replace(R.id.content,findFragment).commit();
//                fm.beginTransaction().hide(homeFragment)
//                        .hide(sortFragment).show(findFragment).hide(cartFragment).hide(mineFragment).commit();

                break;
            case R.id.cart_rb:
                System.out.println("name的值为********："+name);
//                if (name==null){
//                    Intent intent = new Intent(MainActivity.this, Denglu_yemian.class);
//                    startActivity(intent);
//                    fm.beginTransaction().replace(R.id.content,homeFragment).commit();
//                    homeRb.setChecked(true);
//                    break;
//                }
               fm.beginTransaction().replace(R.id.content,cartFragment).commit();
//                fm.beginTransaction().hide(homeFragment)
//                        .hide(sortFragment).hide(findFragment).show(cartFragment).hide(mineFragment).commit();

                break;
            case R.id.mine_rb:

               fm.beginTransaction().replace(R.id.content,mineFragment).commit();
//                fm.beginTransaction().hide(homeFragment)
//                        .hide(sortFragment).hide(findFragment).hide(cartFragment).show(mineFragment).commit();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        sp.edit().clear().commit();

    }
    //实现沉浸式的方法
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }

}
