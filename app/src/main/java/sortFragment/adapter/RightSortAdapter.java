package sortFragment.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jingdong.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import sortFragment.bean.LeftBean;
import sortFragment.bean.RightBean;

/**
 * Created by dream on 2017/12/7.
 */

public class RightSortAdapter extends RecyclerView.Adapter<RightSortAdapter.MyViewHolder> {
    Context context;
    List<RightBean.DataBean> list;

    public RightSortAdapter(Context context, List<RightBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RightSortAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.sort_right_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv.setText(list.get(position).getName());
        holder.rRecyclerview.setLayoutManager(new GridLayoutManager(context,3));
        RightSonAdapter rightSonAdapter = new RightSonAdapter(context, list.get(position).getList());
        holder.rRecyclerview.setAdapter(rightSonAdapter);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        RecyclerView rRecyclerview;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            rRecyclerview = itemView.findViewById(R.id.rRecyclerview);
        }
    }
}
