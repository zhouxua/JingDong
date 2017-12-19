package homeFragment.activity;

import android.app.Service;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bwie.jingdong.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import homeFragment.adapter.SelectAdapter;
import homeFragment.bean.SelectBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.ApiService;

public class SelectActivity extends AppCompatActivity {

    @BindView(R.id.call_back)
    ImageView callBack;
    @BindView(R.id.select_goods)
    EditText selectGoods;
    @BindView(R.id.select)
    Button select;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);
        recyclerView = (RecyclerView) findViewById(R.id.rEcyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick({R.id.call_back, R.id.select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.call_back:
                SelectActivity.this.finish();
                break;
            case R.id.select:
                String trim = selectGoods.getText().toString().trim();
                if (trim==null||trim.equals("")){
                    trim="笔记本";
                }
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://120.27.23.105")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                ApiService service = retrofit.create(ApiService.class);
                service.getSelect(trim,"1","android")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<SelectBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(SelectBean selectBean) {
                                List<SelectBean.DataBean> data = selectBean.getData();
                                SelectAdapter selectAdapter = new SelectAdapter(SelectActivity.this, data);
                                 recyclerView.setAdapter(selectAdapter);
                            }
                        });
                break;
        }
    }
}
