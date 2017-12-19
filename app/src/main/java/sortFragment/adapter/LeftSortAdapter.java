package sortFragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jingdong.R;

import java.util.List;

import sortFragment.bean.LeftBean;
import utils.MyOnItemClickListener;

/**
 * Created by dream on 2017/12/7.
 */

public class LeftSortAdapter extends RecyclerView.Adapter<LeftSortAdapter.MyViewHolder> {
    private MyOnItemClickListener itemClickListener;
   /* 列表点击事件
     *
             * @param itemClickListener
     */
    public void setOnItemClickListener(MyOnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    Context context;
    List<LeftBean.DataBean> list;

    public LeftSortAdapter(Context context, List<LeftBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.sort_left_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getName());
        /*自定义item的点击事件不为null，设置监听事件*/
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
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
