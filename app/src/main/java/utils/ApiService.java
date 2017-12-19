package utils;

import android.view.ViewDebug;

import cartFragment.bean.CartBean;
import homeFragment.bean.AddCartBean;
import homeFragment.bean.HomeBean;

import homeFragment.bean.SelectBean;
import homeFragment.bean.XiangqingBean;
import mineFragment.bean.dengluBean;
import mineFragment.bean.zhuceBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import sortFragment.bean.LeftBean;
import sortFragment.bean.RightBean;

/**
 * Created by dream on 2017/12/6.
 */

public interface ApiService {
    //首页
    @GET("ad/getAd")
    Observable<HomeBean> getHome();

    //分类左
    @GET("product/getCatagory")
    Observable<LeftBean> getLeft();
    //分类右
    @FormUrlEncoded
    @POST("product/getProductCatagory")
    Observable<RightBean> getRight(@Field("cid") String cid);
    //查询购物车
   // http://120.27.23.105/product/getCarts
    @GET("product/getCarts")
    Observable<CartBean> getCart(@Query("uid") String uid);

    @FormUrlEncoded
    @POST("user/login")
    Observable<dengluBean> login(@Field("mobile") String mobile, @Field("password") String password);

    @FormUrlEncoded
    @POST("user/reg")
    Observable<zhuceBean> logina(@Field("mobile") String mobile, @Field("password") String password);

    //http://120.27.23.105/product/searchProducts?keywords=%E6%89%8B%E6%9C%BA&page=1&source=android
    @GET("product/searchProducts")
    Observable<SelectBean> getSelect(@Query("keywords") String keywords, @Query("page") String page,@Query("source") String source);

    //商品详情接口
    //http://120.27.23.105/product/getProductDetail?pid=1
    @GET("product/getProductDetail")
    Observable<XiangqingBean> getXiangqing(@Query("pid") int pid);

    //添加购物车
    //http://120.27.23.105/product/addCart?pid=56&uid=983&source=android
    @GET("product/addCart")
    Observable<AddCartBean> getAddCart(@Query("pid") String pid,@Query("uid") String uid,@Query("source") String source);
}
