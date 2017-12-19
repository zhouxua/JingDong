package sortFragment.presenter;

import sortFragment.bean.LeftBean;
import sortFragment.bean.RightBean;
import sortFragment.model.SortModel;
import sortFragment.view.IsortView;

/**
 * Created by dream on 2017/12/7.
 */

public class IsortPresenter implements SortModel.LeftFinish{
    public final IsortView isortView;
    public final SortModel sortModel;

    public IsortPresenter(IsortView isortView) {
        this.isortView = isortView;
        sortModel = new SortModel(this);
    }
    public void getLeftUrl(String url){
        sortModel.getLeftUrl(url);
    }
    @Override
    public void LeftFinished(LeftBean leftBean) {
      isortView.getLeftData(leftBean);
    }

}
