package homeFragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jingdong.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import homeFragment.bean.SelectBean;
import homeFragment.bean.XiangqingBean;
import homeFragment.homeUtils.GlideImageLoader;


/**
 * Created by dream on 2017/12/13.
 */

public class XiangqingAdapter extends RecyclerView.Adapter<XiangqingAdapter.staggerView>{


private List<XiangqingBean.DataBean> data;
private Context context;

public XiangqingAdapter(Context context, List<XiangqingBean.DataBean> data) {
        this.data = data;
        this.context = context;
        }

@Override
public staggerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_xinagqing_item, null, false);
        staggerView staggerView = new staggerView(view);
        return staggerView;
        }

@Override
public void onBindViewHolder(final staggerView holder, int position) {
        holder.tv.setText(data.get(position).getTitle());
        holder.price.setText("￥"+data.get(position).getPrice());
        holder.jieshao.setText(data.get(position).getSubhead());
   holder.banner.setImageLoader(new GlideImageLoader());
    String[] split = data.get(position).getImages().split("\\|");
    List<String> bannerList = new ArrayList<>();

    for (int i = 0; i < split.length; i++) {
        bannerList.add(split[i]);
    }
    holder.banner.setImages(bannerList);
    holder.banner.start();

        }

@Override
public int getItemCount() {

        return 1;
        }
public static class staggerView extends RecyclerView.ViewHolder{
    Banner banner;
    TextView tv,price,jieshao;
    public staggerView(View itemView) {
        super(itemView);
        tv=(TextView) itemView.findViewById(R.id.tv);
        price= (TextView)itemView.findViewById(R.id.price);
        jieshao= (TextView)itemView.findViewById(R.id.jieshao);
        banner = itemView.findViewById(R.id.banner);
    }
}
}
