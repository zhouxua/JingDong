package homeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.jingdong.R;

import java.nio.channels.SelectableChannel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import homeFragment.activity.SelectActivity;
import homeFragment.adapter.HomeAdapter;
import homeFragment.bean.HomeBean;
import homeFragment.presenter.IhomePresenter;
import homeFragment.view.IhomeView;
import utils.Api;

/**
 * 周旋
 * 2017/11/29  21:06
 */

public class HomeFragment extends Fragment implements IhomeView {
    @BindView(R.id.Recyclerview)
    RecyclerView mRecyclerview;
    Unbinder unbinder;

    IhomePresenter ihomePresenter;
    @BindView(R.id.erweima)
    ImageView erweima;
    @BindView(R.id.sousuokuang)
    EditText sousuokuang;
    @BindView(R.id.xiaoxi)
    ImageView xiaoxi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_fragment_pager, null);
        unbinder = ButterKnife.bind(this, view);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        ihomePresenter = new IhomePresenter(this);
        ihomePresenter.getHomeUrl(Api.HOME_URL);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void getData(HomeBean homeBean) {
//        Toast.makeText(getActivity(),"输出结果："+homeBean,Toast.LENGTH_SHORT).show();
        HomeAdapter adapter = new HomeAdapter(getActivity(), homeBean);
        mRecyclerview.setAdapter(adapter);
    }

    @OnClick({R.id.erweima, R.id.sousuokuang, R.id.xiaoxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.erweima:
                Toast.makeText(getActivity(),"扫描二维码明日开启",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sousuokuang:
                Toast.makeText(getActivity(),"搜索",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), SelectActivity.class));
                break;
            case R.id.xiaoxi:
                Toast.makeText(getActivity(),"敬请期待",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
