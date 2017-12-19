package sortFragment.model;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sortFragment.bean.RightBean;
import utils.ApiService;
import utils.MessageEvent;

/**
 * Created by dream on 2017/12/7.
 */

public class SortRightModel implements IsortRightModel {

    private RightFinish rightFinish;

    public SortRightModel(){


    }
    public SortRightModel(RightFinish rightFinish) {
        this.rightFinish = rightFinish;
    }

    public interface RightFinish{
        void RightFinished(RightBean rightBean);
    }
        @Override
    public void getRightUrl(String url,String cid) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

            ApiService apiService = retrofit.create(ApiService.class);
            //接收eventbus传过来的值
            Observable<RightBean> postpages = apiService.getRight(cid);

            postpages.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RightBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(RightBean rightBean) {
                            rightFinish.RightFinished(rightBean);
                        }
                    });
        }

}
