package homeFragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import homeFragment.bean.SelectBean;
import utils.MessageEvent;
import utils.MyOnItemClickListener;

/**
 * Created by dream on 2017/12/11.
 */

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.staggerView>{
    private MyOnItemClickListener itemClickListener;
    public void setOnItemClickListener(MyOnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

        private List<SelectBean.DataBean> list;
        private Context context;

        public SelectAdapter(Context context, List<SelectBean.DataBean> list) {
            this.list = list;
            this.context = context;
        }

        @Override
        public SelectAdapter.staggerView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_select_item, null, false);
            SelectAdapter.staggerView staggerView = new SelectAdapter.staggerView(view);
            return staggerView;
        }

        @Override
        public void onBindViewHolder(final SelectAdapter.staggerView holder, int position) {
            holder.tv2.setText("￥"+list.get(position).getPrice());
            holder.tv.setText(list.get(position).getTitle());
            holder.img.setImageURI(Uri.parse(list.get(position).getImages()));
            SelectAdapter.this.setOnItemClickListener(new MyOnItemClickListener() {
                @Override
                public void OnItemClickListener(View view, int position) {
                    int pid = list.get(position).getPid();
                    EventBus.getDefault().postSticky(new MessageEvent(pid+""));
                    context.startActivity(new Intent(SelectAdapter.this.context, XiangqingActivity.class));
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
            TextView tv;TextView tv2;
            public staggerView(View itemView) {
                super(itemView);
                tv= (TextView)itemView.findViewById(R.id.title);
                tv2= (TextView)itemView.findViewById(R.id.price);
                img =(SimpleDraweeView) itemView.findViewById(R.id.img);
            }
        }
    }

