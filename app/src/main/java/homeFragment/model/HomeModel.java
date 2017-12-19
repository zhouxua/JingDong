package homeFragment.model;

import homeFragment.bean.HomeBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.ApiService;

/**
 * Created by dream on 2017/12/6.
 */

public class HomeModel implements IhomeModel {

    private HomeFinish homeFinish;

    public HomeModel(HomeFinish homeFinish) {
        this.homeFinish = homeFinish;
    }

    public interface HomeFinish{
        void HomeFinished(HomeBean homeBean);
    }
    @Override
    public void getUrl(String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);

        Observable<HomeBean> postpages = apiService.getHome();

        postpages.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                       homeFinish.HomeFinished(homeBean);
                    }
                });
    }
}
