package homeFragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jingdong.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import homeFragment.activity.XiangqingActivity;
import homeFragment.bean.HomeBean;
import utils.MessageEvent;
import utils.MyOnItemClickListener;

/**
 * Created by dream on 2017/12/6.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.staggerView>{
    private MyOnItemClickListener itemClickListener;
    public void setOnItemClickListener(MyOnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private List<HomeBean.TuijianBean.ListBean> list;
    private Context context;

    public HomeRecyclerAdapter(Context context, List<HomeBean.TuijianBean.ListBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public staggerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item3_layout, null, false);
        staggerView staggerView = new staggerView(view);
        return staggerView;
    }

    @Override
    public void onBindViewHolder(final staggerView holder, int position) {
        holder.tv1.setText(list.get(position).getTitle());
        holder.tv2.setText("￥"+list.get(position).getPrice());
        String[] split = list.get(position).getImages().split("\\|");
        holder.img.setImageURI(split[0]);
        HomeRecyclerAdapter.this.setOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                int pid = list.get(position).getPid();
                EventBus.getDefault().postSticky(new MessageEvent(pid+""));
                context.startActivity(new Intent(HomeRecyclerAdapter.this.context, XiangqingActivity.class));
            }
        });
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.OnItemClickListener(holder.itemView, holder.getLayoutPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        System.out.println(list.size()+"");
        return list.size();
    }
    public static class staggerView extends RecyclerView.ViewHolder{
        SimpleDraweeView img;
        TextView tv1,tv2;
        public staggerView(View itemView) {
            super(itemView);
            tv1=(TextView) itemView.findViewById(R.id.title);
            tv2= (TextView)itemView.findViewById(R.id.price);
            img =(SimpleDraweeView) itemView.findViewById(R.id.sdv);
        }
    }
}
