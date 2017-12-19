package sortFragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jingdong.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import sortFragment.bean.RightBean;

/**
 * Created by dream on 2017/12/7.
 */

public class RightSonAdapter extends RecyclerView.Adapter<RightSonAdapter.MyViewHolder> {
    Context context;
    List<RightBean.DataBean.ListBean> list;

    public RightSonAdapter(Context context, List<RightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.sort_right_item_son, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.sdv.setImageURI(list.get(position).getIcon());
        holder.tv.setText(list.get(position).getName());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        private SimpleDraweeView sdv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            sdv = itemView.findViewById(R.id.sdv);
        }
    }
}
