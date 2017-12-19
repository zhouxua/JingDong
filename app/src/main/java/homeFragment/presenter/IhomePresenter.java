package homeFragment.presenter;

import homeFragment.bean.HomeBean;
import homeFragment.model.HomeModel;
import homeFragment.model.HomeModel.HomeFinish;
import homeFragment.view.IhomeView;

/**
 * Created by dream on 2017/12/6.
 */

public class IhomePresenter implements HomeFinish {

    private final IhomeView ihomeView;
    private final HomeModel homeModel;

    public IhomePresenter(IhomeView ihomeView) {
        this.ihomeView = ihomeView;
        homeModel = new HomeModel(this);
    }

    public void getHomeUrl(String url){
        homeModel.getUrl(url);
    }

    @Override
    public void HomeFinished(HomeBean homeBean) {
        ihomeView.getData(homeBean);
    }
}
