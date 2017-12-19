package mineFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.jingdong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mineFragment.activity.Denglu_yemian;

/**
 * 周旋
 * 2017/11/29  21:09
 */

public class MineFragment extends Fragment {


    @BindView(R.id.login)
    ImageView login;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.allpay)
    TextView allpay;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.mine_fragment_pager, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.login, R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                Intent intent = new Intent(getActivity(), Denglu_yemian.class);
                startActivity(intent);
                break;
            case R.id.ll_back:

                break;
        }
    }
}
