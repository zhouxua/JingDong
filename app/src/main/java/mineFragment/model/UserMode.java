package mineFragment.model;


import android.util.Log;


import mineFragment.bean.User;
import mineFragment.bean.dengluBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.Api;
import utils.ApiService;

/**
 * 周旋
 * 2017/11/13  18:39
 */

public class UserMode implements Imodel {

    private OnFinish onFinish;

    public interface OnFinish{
        void OnFinishListener(dengluBean dengluBean);
    }
    public void setOnFinish(OnFinish finish){
        this.onFinish=finish;
    }
    @Override
    public void login(User user) {
//        if (dengluBean.getCode().equals("0")){
//            return "true";
//        }else{
//            return "false";
//        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.HOME_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
//        Map<String ,String> map = new HashMap<>();
//        map.put("mobile",user.getMobile());
//        map.put("password",user.getPassword());
        Observable<dengluBean> home = apiService.login(user.getMobile(),user.getPassword());
        home.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<dengluBean>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(dengluBean dengluBean) {

                        Log.e("main","code:"+dengluBean.getCode());
                        onFinish.OnFinishListener(dengluBean);

                    }
                });
    }
}


