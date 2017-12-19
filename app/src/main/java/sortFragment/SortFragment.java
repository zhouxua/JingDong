package sortFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.jingdong.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sortFragment.adapter.LeftSortAdapter;
import sortFragment.adapter.RightSonAdapter;
import sortFragment.adapter.RightSortAdapter;
import sortFragment.bean.LeftBean;
import sortFragment.bean.RightBean;
import sortFragment.presenter.IsortPresenter;
import sortFragment.presenter.IsortRightPresenter;
import sortFragment.view.IsortRightView;
import sortFragment.view.IsortView;
import utils.Api;
import utils.MessageEvent;
import utils.MyOnItemClickListener;

import static android.graphics.Color.RED;

/**
 * 周旋
 * 2017/11/29  21:07
 */

public class SortFragment extends Fragment implements IsortView,IsortRightView {

   IsortPresenter isortPresenter;
   IsortRightPresenter isortRightPresenter;
    @BindView(R.id.leftRecyclerView)
    RecyclerView leftRecyclerView;
    @BindView(R.id.rightRecyclerView)
    RecyclerView rightRecyclerView;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.sort_fragment_pager, null);
        unbinder = ButterKnife.bind(this, view);

        isortPresenter = new IsortPresenter(this);
        isortRightPresenter = new IsortRightPresenter(this);
        isortPresenter.getLeftUrl(Api.HOME_URL);
//        isortRightPresenter.getRightUrl(Api.HOME_URL);
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getLeftData(final LeftBean leftBean) {
        List<LeftBean.DataBean> data = leftBean.getData();
        LeftSortAdapter leftSortAdapter = new LeftSortAdapter(getActivity(), data);
        leftRecyclerView.setAdapter(leftSortAdapter);
         /*点击事件*/
        leftSortAdapter.setOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                isortRightPresenter.getRightUrl(Api.HOME_URL,
                        ""+leftBean.getData().get(position).getCid());
            }
        });
//        Toast.makeText(getActivity(),"123"+data,Toast.LENGTH_SHORT).show();
//        System.out.println("");

    }

    @Override
    public void getRightData(RightBean rightBean) {
        RightSortAdapter sortAdapter = new RightSortAdapter(getActivity(), rightBean.getData());
        rightRecyclerView.setAdapter(sortAdapter);
    }


}
