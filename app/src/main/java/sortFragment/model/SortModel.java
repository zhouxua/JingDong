package sortFragment.model;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sortFragment.bean.LeftBean;
import sortFragment.bean.RightBean;
import utils.ApiService;

/**
 * Created by dream on 2017/12/7.
 */

public class SortModel implements IsortModel {

    private LeftFinish leftFinish;


    public SortModel(LeftFinish leftFinish) {
        this.leftFinish = leftFinish;
    }

    public interface LeftFinish{
         void LeftFinished(LeftBean leftBean);
    }


    @Override
    public void getLeftUrl(String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);

        Observable<LeftBean> postpages = apiService.getLeft();

        postpages.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LeftBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LeftBean leftBean) {
                        leftFinish.LeftFinished(leftBean);
                    }
                });
    }
}
