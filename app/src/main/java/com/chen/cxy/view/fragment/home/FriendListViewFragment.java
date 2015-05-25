package com.chen.cxy.view.fragment.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.chen.cxy.R;
import com.chen.cxy.view.adapter.FriendAdapter;
import com.chen.cxy.view.list.DynamicListView;
import com.chen.cxy.view.list.FriendListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FriendListViewFragment extends Fragment {
    Context context;//上下文
    SimpleAdapter adapter;
    FriendListView listView;
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
    public static FriendListViewFragment newInstance(String param1, String param2) {
        FriendListViewFragment fragment = new FriendListViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FriendListViewFragment() {
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
        View view = inflater.inflate(R.layout.fragment_friend_listview, container, false);

        listView = (FriendListView)view.findViewById(R.id.friend_listview);
        //listView.setiReflashListener(this);//接口回调刷新

        showList(getData());
        return view;
    }

    private List<Map<String,Object>> getData(){

        //portrait_iv 头像
        // nickname_iv昵称
        //content_tv 文字内容
        //count_tv 未读数量
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("portrait_iv",R.mipmap.default_boy );
        map.put("nickname_tv", "神蕴的分身");
        map.put("content_tv", "纪念保罗");
        map.put("count_tv", 2);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("portrait_iv",R.mipmap.default_boy );
        map.put("nickname_tv", "蒙若多");
        map.put("content_tv", "我晋级黄金V了 哈哈哈啊哈哈哈啊哈");
        map.put("count_tv", 0);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("portrait_iv",R.mipmap.default_girl );
        map.put("nickname_tv", "辛德拉");
        map.put("content_tv", "是时候进攻提莫的要塞了!!");
        map.put("count_tv", 12);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("portrait_iv",R.mipmap.default_girl );
        map.put("nickname_tv", "安妮");
        map.put("content_tv", "好像被虚弱了");
        map.put("count_tv", 0);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("portrait_iv",R.mipmap.default_girl );
        map.put("nickname_tv", "豹女");
        map.put("content_tv", "我为什么叫豹女!!");
        map.put("count_tv", 1);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("portrait_iv",R.mipmap.default_boy );
        map.put("nickname_tv", "神若多");
        map.put("content_tv", "哈哈恭喜EDP获得LPL季中邀请赛冠军");
        map.put("count_tv", 0);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("portrait_iv",R.mipmap.default_girl );
        map.put("nickname_tv", "蒙若多的分身");
        map.put("content_tv", "蒙若多感觉晋级铂金!!");
        map.put("count_tv", "99+");
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("portrait_iv",R.mipmap.default_girl );
        map.put("nickname_tv", "幸福媳妇");
        map.put("content_tv", "要幸福");
        map.put("count_tv", "9");
        list.add(map);

        return list;
    }

    //关联适配器
    private void showList(List<Map<String,Object>> list){
        if(adapter == null){
            adapter = new FriendAdapter(context, list, R.layout.friend_list_item,
                    new String[]{"portrait_iv","nickname_tv","content_tv","count_tv"},
                    new int []{R.id.portrait_iv,R.id.nickname_tv,R.id.content_tv,R.id.count_tv});
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
