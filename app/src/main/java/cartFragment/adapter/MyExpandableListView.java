package cartFragment.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.jingdong.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cartFragment.bean.CartBean;


/**
 * 周旋
 * 2017/11/22  19:22
 */

public class MyExpandableListView extends BaseExpandableListAdapter {
    public boolean ischeck;
    private Context context;
    private LayoutInflater inflater;
    CartBean cartBean;
    private CheckInterface checkInterface;

    public MyExpandableListView(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }
    //    private String[] groups = {"A", "B", "C"};
    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}*/
//    private String[][] childs={{"A1","A2","A3","A4"},{"A1","A2","A3", "B4"},{"A1","A2","A3","C4"}};


    public MyExpandableListView(Context context, CartBean cartBean) {
        this.context = context;
        this.cartBean = cartBean;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    //返回一级列表的个数
    @Override
    public int getGroupCount() {
        return cartBean.getData().size();
    }

    //返回每个二级列表的个数
    @Override
    public int getChildrenCount(int groupPosition) { //参数groupPosition表示第几个一级列表
        Log.d("smyhvae", "-->" + groupPosition);
        return cartBean.getData().get(groupPosition).getList().size();
    }

    //返回一级列表的单个item（返回的是对象）
    @Override
    public Object getGroup(int groupPosition) {

        return cartBean.getData().get(groupPosition);
    }

    //返回二级列表中的单个item（返回的是对象）
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return cartBean.getData().get(groupPosition).getList().get(childPosition);  //不要误写成groups[groupPosition][childPosition]
    }

    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return childPosition;
    }

    //每个item的id是否是固定？一般为true
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //【重要】填充一级列表
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderG viewHolderG;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_group, null);
            viewHolderG = new ViewHolderG();
            viewHolderG.tv_group = convertView.findViewById(R.id.tv_group);
            viewHolderG.checkshop = convertView.findViewById(R.id.checkshop);
            convertView.setTag(viewHolderG);
        }else{
            viewHolderG = (ViewHolderG) convertView.getTag();
        }

            viewHolderG.tv_group.setText(cartBean.getData().get(groupPosition).getSellerName());



        return convertView;
    }

    //【重要】填充二级列表
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_child, null);
            viewHolder = new ViewHolder();
            viewHolder.price = convertView.findViewById(R.id.price);
            viewHolder.tv_child = convertView.findViewById(R.id.tv_child);
            viewHolder.sdv = convertView.findViewById(R.id.main_simple_drawee_view);
            viewHolder.checkitem = convertView.findViewById(R.id.checkitem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.price.setText("￥" + cartBean.getData().get(groupPosition).getList().get(childPosition).getPrice());
        viewHolder.tv_child.setText(cartBean.getData().get(groupPosition).getList().get(childPosition).getTitle());
        String[] images = cartBean.getData().get(groupPosition).getList().get(childPosition).getImages().split("\\|");
        Uri uri = Uri.parse(images[0]);
        viewHolder.sdv.setImageURI(uri);






        return convertView;

    }

    //二级列表中的item是否能够被选中？可以改为true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolder{
        TextView price;
        TextView tv_child;
        SimpleDraweeView sdv;
        CheckBox checkitem;

    }
    class ViewHolderG{
        TextView tv_group;
        CheckBox checkshop;
    }
    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        public void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        public void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }
}

