package homeFragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jingdong.R;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import homeFragment.activity.XiangqingActivity;
import homeFragment.bean.HomeBean;
import homeFragment.homeUtils.GlideImageLoader;
import utils.MessageEvent;

/**
 * Created by dream on 2017/12/6.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    HomeBean homeBean;
    LayoutInflater inflater;
    private final  int TYPE_1 = 0;
    private final  int TYPE_2 = 1;
    private final  int TYPE_6 = 5;

    public HomeAdapter(Context context,  HomeBean homeBean) {
        this.context = context;
        this.homeBean = homeBean;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
      if (position==0){
          return TYPE_1;
      }else if (position==1){
          return TYPE_2;
      }else {
         return TYPE_6;
      }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_1:
                View view1 = inflater.inflate(R.layout.home_item_banner,parent,false);
                MyViewHolder1 holder1 = new MyViewHolder1(view1);
                return holder1;
                case TYPE_2:
                View view2 = inflater.inflate(R.layout.home_item2,parent,false);
                    MyViewHolde2 holder2 = new MyViewHolde2(view2);
                return holder2;
            case TYPE_6:
                View view = inflater.inflate(R.layout.home_item3,null,false);
                MyViewHolder6 holder = new MyViewHolder6(view);
                return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case TYPE_1:
                MyViewHolder1 holder1 = (MyViewHolder1) holder;
                holder1.mbanner.setImageLoader(new GlideImageLoader());
                final List<String> bannerList = new ArrayList<>();
                for (int i = 0; i < homeBean.getData().size(); i++) {
                    bannerList.add(homeBean.getData().get(i).getIcon());
                }
                holder1.mbanner.setImages(bannerList);
                holder1.mbanner.start();
                ((MyViewHolder1) holder).mbanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        int pid = homeBean.getData().get(position).getAid();
                        EventBus.getDefault().postSticky(new MessageEvent(pid+""));
                        context.startActivity(new Intent(HomeAdapter.this.context, XiangqingActivity.class));
                    }
                });
                break;
            case TYPE_2:
                MyViewHolde2 myViewHolder2 =(MyViewHolde2) holder;
                myViewHolder2.title_title.setText(homeBean.getMiaosha().getName());
                myViewHolder2.mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                HomeMiaoshaAdapter staggerView = new HomeMiaoshaAdapter(context, homeBean.getMiaosha().getList());
                myViewHolder2.mRecyclerView.setAdapter(staggerView);
                break;
                case TYPE_6:
                MyViewHolder6 myViewHolder =(MyViewHolder6) holder;
                myViewHolder.title_title.setText(homeBean.getTuijian().getName());
                myViewHolder.mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                HomeRecyclerAdapter adapter = new HomeRecyclerAdapter(context,homeBean.getTuijian().getList());
                myViewHolder.mRecyclerView.setAdapter(adapter);
                break;
        }
    }

    @Override
    public int getItemCount() {

        return 3;
    }

    class MyViewHolder6 extends RecyclerView.ViewHolder{
        RecyclerView mRecyclerView;
        TextView title_title;
        public MyViewHolder6(View itemView) {
            super(itemView);
            title_title = itemView.findViewById(R.id.title_title);
            mRecyclerView = (RecyclerView)itemView.findViewById(R.id.mRecyclerview);
        }
    }
    class MyViewHolde2 extends RecyclerView.ViewHolder{
        RecyclerView mRecyclerView;
        TextView title_title;
        public MyViewHolde2(View itemView) {
            super(itemView);
            title_title = itemView.findViewById(R.id.title_title);
            mRecyclerView = (RecyclerView)itemView.findViewById(R.id.mRecyclerview);
        }
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder{
        private Banner mbanner;
        public MyViewHolder1(View itemView) {
            super(itemView);
            mbanner = (Banner)itemView.findViewById(R.id.banner);
        }
    }
}
