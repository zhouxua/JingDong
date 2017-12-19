package sortFragment.presenter;

import sortFragment.bean.RightBean;
import sortFragment.model.IsortRightModel;
import sortFragment.model.SortRightModel;
import sortFragment.view.IsortRightView;

/**
 * Created by dream on 2017/12/7.
 */

public class IsortRightPresenter implements SortRightModel.RightFinish {
    public final IsortRightView isortRightView;
    public final SortRightModel sortRightModel;

    public IsortRightPresenter(IsortRightView isortRightView) {
        this.isortRightView = isortRightView;
        sortRightModel = new SortRightModel(this);
    }
    public void getRightUrl(String url,String cid){
        sortRightModel.getRightUrl(url,cid);
    }

    @Override
    public void RightFinished(RightBean rightBean) {
        isortRightView.getRightData(rightBean);
    }
}
