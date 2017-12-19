package cartFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.jingdong.R;

import java.util.List;

import cartFragment.adapter.MyExpandableListView;
import cartFragment.adapter.RecyAdapter;
import cartFragment.bean.CartBean;
import cartFragment.presenter.IcartPresenter;
import cartFragment.view.IcartView;
import mineFragment.activity.Denglu_yemian;
import utils.Api;

/**
 * 周旋
 * 2017/11/29  21:08
 */

public class CartFragment extends Fragment implements IcartView{
  IcartPresenter icartPresenter;
    private RecyclerView recyclerView;
    private TextView total_price;
    private TextView total_num;
    private CheckBox quanxuan;
    private RecyAdapter recyAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        Toast.makeText(getActivity(),"onAttach",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.cart_fragment_pager,null);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_View);
        total_price = (TextView) view.findViewById(R.id.total_price);
        total_num = (TextView) view.findViewById(R.id.total_num);
        quanxuan = (CheckBox) view.findViewById(R.id.quanxuan);
        quanxuan.setTag(1);//1为不选中
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        //new出适配器
        recyAdapter = new RecyAdapter(getActivity());

        icartPresenter = new IcartPresenter(this);

        SharedPreferences sp = getActivity().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);

        String name = sp.getString("uid", null);
        System.out.println("SharedPreferences-------"+name);

           if (name==null){
               Intent intent = new Intent(getActivity(), Denglu_yemian.class);
               startActivity(intent);
           }



//       if (name==null){
//           Intent intent = new Intent(getActivity(), Denglu_yemian.class);
//           startActivity(intent);
//           Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
//           if (name==null){
//               Intent intent1 = new Intent(getActivity(), Denglu_yemian.class);
//               startActivity(intent1);
//               Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
//
//           }
//       }
        icartPresenter.getCartUrl(Api.HOME_URL,name);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recyAdapter);
        //recyAdapter里面的接口,设置 全选按钮 总价 总数量
        recyAdapter.setUpdateListener(new RecyAdapter.UpdateListener() {
            @Override
            public void setTotal(String total, String num, boolean allCheck) {
                //设置ui的改变
                total_num.setText("共"+num+"件商品");//总数量
                total_price.setText("总价 :¥"+total+"元");//总价
                if(allCheck){
                    quanxuan.setTag(2);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_selected);
                }else{
                    quanxuan.setTag(1);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_unselected);
                }
                quanxuan.setChecked(allCheck);
            }
        });

        //这里只做ui更改, 点击全选按钮,,调到adapter里面操作
        quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用adapter里面的方法 ,,把当前quanxuan状态传递过去

                int tag = (int) quanxuan.getTag();
                if(tag==1){
                    quanxuan.setTag(2);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_selected);
                }else{
                    quanxuan.setTag(1);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_unselected);
                }

                recyAdapter.quanXuan(quanxuan.isChecked());
            }
        });

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void getData(CartBean cartBean) {
        recyAdapter.add(cartBean);
    }
}
