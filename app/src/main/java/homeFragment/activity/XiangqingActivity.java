package homeFragment.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.jingdong.R;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import homeFragment.adapter.XiangqingAdapter;
import homeFragment.bean.AddCartBean;
import homeFragment.bean.SelectBean;
import homeFragment.bean.XiangqingBean;
import homeFragment.homeUtils.GlideImageLoader;
import mineFragment.activity.Denglu_yemian;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.Api;
import utils.ApiService;
import utils.MessageEvent;

public class XiangqingActivity extends AppCompatActivity {

    String pid;
RecyclerView recyclerView;
    TextView tv,price,jieshao;
    Banner banner;
    SharedPreferences sp;

    @BindView(R.id.addCart)
    Button addCart;
    @BindView(R.id.goShopping)
    Button goShopping;
    private String uid;

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void ononMoonStickyEvent(MessageEvent messageEvent) {
        System.out.println("传值：" + messageEvent.getMessage());
        pid = messageEvent.getMessage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        ButterKnife.bind(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        tv = (TextView) findViewById(R.id.tv);
        price = (TextView) findViewById(R.id.price);
        jieshao = (TextView) findViewById(R.id.jieshao);
        banner = (Banner) findViewById(R.id.banner);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //注册事件
        EventBus.getDefault().register(XiangqingActivity.this);
        if (pid != null) {
            getData();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.HOME_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            ApiService service = retrofit.create(ApiService.class);
            service.getXiangqing(Integer.parseInt(pid))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<XiangqingBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                        @Override
                        public void onNext(XiangqingBean xiangqingBean) {

                            tv.setText(xiangqingBean.getData().getTitle());
                            price.setText("￥"+xiangqingBean.getData().getPrice());
                            jieshao.setText(xiangqingBean.getData().getSubhead());
                            banner.setImageLoader(new GlideImageLoader());
                            String[] split = xiangqingBean.getData().getImages().split("\\|");
                            List<String> bannerList = new ArrayList<>();

                            for (int i = 0; i < split.length; i++) {
                                bannerList.add(split[i]);
                            }
                            banner.setImages(bannerList);
                            banner.start();
                            refresh();
                        }
                    });

        }


    }
    public void refresh() {
        onCreate(null);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(XiangqingActivity.this);
    }

    @OnClick({R.id.addCart, R.id.goShopping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addCart:
                sp = getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
                uid = sp.getString("uid", null);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.HOME_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                ApiService apiService = retrofit.create(ApiService.class);
                Observable<AddCartBean> android = apiService.getAddCart(pid, uid, "android");
                android.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<AddCartBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(AddCartBean addCartBean) {
                                String code = addCartBean.getCode();

                                if (code.equals("0")) {
                                    Toast.makeText(XiangqingActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(XiangqingActivity.this, "添加失败，请先登录购物车", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(XiangqingActivity.this, Denglu_yemian.class));
                                }
                            }
                        });
                break;
            case R.id.goShopping:
                Toast.makeText(XiangqingActivity.this, "此功能暂不开放！", Toast.LENGTH_SHORT).show();
                break;
        }

    }
    public void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.HOME_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        service.getXiangqing(Integer.parseInt(pid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XiangqingBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(XiangqingBean xiangqingBean) {

                        tv.setText(xiangqingBean.getData().getTitle());
                        price.setText("￥"+xiangqingBean.getData().getPrice());
                        jieshao.setText(xiangqingBean.getData().getSubhead());
                        banner.setImageLoader(new GlideImageLoader());
                        String[] split = xiangqingBean.getData().getImages().split("\\|");
                        List<String> bannerList = new ArrayList<>();

                        for (int i = 0; i < split.length; i++) {
                            bannerList.add(split[i]);
                        }
                        banner.setImages(bannerList);
                        banner.start();
                        refresh();
                    }
                });
    }
}
