package com.chen.cxy.view.fragment.dynamic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import com.chen.cxy.R;
import com.chen.cxy.view.list.DynamicListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 显示全球动态
 */
public class DynamicListView2Fragment extends Fragment {
    Context context;//上下文
    SimpleAdapter adapter;
    DynamicListView listView;
    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DynamicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DynamicListView2Fragment newInstance(String param1, String param2) {
        DynamicListView2Fragment fragment = new DynamicListView2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DynamicListView2Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = inflater.getContext().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment_dynamic_listview, container, false);

        listView = (DynamicListView)view.findViewById(R.id.dynamic_listview);
        //listView.setiReflashListener(this);//接口回调刷新

        showList(getData());
        return view;
    }

    private List<Map<String,Object>> getData(){

        //head_iv 头像
        // nickname_iv昵称
        //address_tv 地址
        //content_tv 文字内容
        //content_iv 图片内容
        //like_count 喜欢的数量
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("portrait_iv",R.mipmap.default_boy );
        map.put("nickname_tv", "CCTV5");
        map.put("address_tv", "北京");
        map.put("content_tv", "余竹安成为2020年NBA选修状元");
        map.put("content_iv", R.mipmap.del_nba);
        map.put("like_count", 100);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("portrait_iv",R.mipmap.default_boy );
        map.put("nickname_tv", "神蕴");
        map.put("address_tv", "北京");
        map.put("content_tv", "以前去故宫的照片");
        map.put("content_iv", R.mipmap.del_cat);
        map.put("like_count", 1000);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("portrait_iv",R.mipmap.default_boy );
        map.put("nickname_tv", "蒙若多");
        map.put("address_tv", "厦门");
        map.put("content_tv", "升级到黄金4了 哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        map.put("content_iv", R.mipmap.del_tm);
        map.put("like_count", 1000);
        list.add(map);

        return list;
    }

    //关联适配器
    private void showList(List<Map<String,Object>> list){
        if(adapter == null){
            adapter = new SimpleAdapter(context, list, R.layout.dynamic_list_item,
                    new String[]{"portrait_iv","nickname_tv","address_tv","content_tv","content_iv","like_count"},
                    new int []{R.id.portrait_iv,R.id.nickname_tv,R.id.address_tv,R.id.content_tv,R.id.content_iv,R.id.like_count});
        }else{
            this.list = list;
            adapter.notifyDataSetChanged();
        }

        listView.setAdapter(adapter);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }





}
