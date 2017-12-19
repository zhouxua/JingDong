package cartFragment.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.health.UidHealthStats;

import cartFragment.bean.CartBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sortFragment.bean.LeftBean;
import utils.ApiService;

/**
 * Created by dream on 2017/12/7.
 */

public class CartModel implements IcartModel{
    CartFinish cartFinish;

    public CartModel(CartFinish cartFinish) {
        this.cartFinish = cartFinish;
    }

    public interface CartFinish{
        void CaertFinished(CartBean cartBean);
    }

        @Override
    public void getCartUrl(String url,String uid) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);
        Observable<CartBean> postpages = apiService.getCart(uid);

        postpages.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CartBean cartBean) {
                        cartFinish.CaertFinished(cartBean);
                    }
                });
    }
}
