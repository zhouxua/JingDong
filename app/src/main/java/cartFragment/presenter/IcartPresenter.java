package cartFragment.presenter;

import cartFragment.bean.CartBean;
import cartFragment.model.CartModel;
import cartFragment.view.IcartView;
import findFragment.FindFragment;

/**
 * Created by dream on 2017/12/7.
 */

public class IcartPresenter implements CartModel.CartFinish{
    private final IcartView icartView;
    private final CartModel cartModel;

    public IcartPresenter(IcartView icartView) {
        this.icartView = icartView;
        cartModel = new CartModel(this);
    }

    public void getCartUrl(String url,String uid){
        cartModel.getCartUrl(url,uid);
    }
    @Override
    public void CaertFinished(CartBean cartBean) {
        icartView.getData(cartBean);
    }
}
