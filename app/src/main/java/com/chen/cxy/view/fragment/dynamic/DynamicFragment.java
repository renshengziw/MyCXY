package com.chen.cxy.view.fragment.dynamic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.cxy.R;
import com.chen.cxy.view.list.DynamicListView;


public class DynamicFragment extends Fragment implements View.OnClickListener{


    DynamicListViewFragment listViewFragment;
    DynamicListView2Fragment listView2Fragment;
    TextView dynamicLeft; //目前是同城
    TextView dynamicRight; //目前是全球

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER 多余
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters 多余
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
    public static DynamicFragment newInstance(String param1, String param2) {
        //多余的
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DynamicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //多余的
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);

        init(view);

        select(1);//默认显示第一页同城动态

        return view;
    }

    private void init(View view){
        dynamicLeft = (TextView) view.findViewById(R.id.dynamic_left);
        dynamicRight = (TextView) view.findViewById(R.id.dynamic_right);
        dynamicLeft.setOnClickListener(this);
        dynamicRight.setOnClickListener(this);



    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    private void select(int id){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        hideFragment(beginTransaction);
        switch (id){
            case 1 : //left
                if(listViewFragment == null){
                    listViewFragment = new DynamicListViewFragment();
                    beginTransaction.add(R.id.dynamic_frame,listViewFragment);
                }else{
                    beginTransaction.show(listViewFragment);
                }
                break;
            case 2 : //right
                if(listView2Fragment == null){
                    listView2Fragment = new DynamicListView2Fragment();
                    beginTransaction.add(R.id.dynamic_frame,listView2Fragment);
                }else{
                    beginTransaction.show(listView2Fragment);
                }
                break;
        }

        beginTransaction.commit();
    }

    /**
     * 隐藏Fragment
     * @param beginTransaction
     */
    private void hideFragment(FragmentTransaction beginTransaction) {
        if(listViewFragment != null){
            beginTransaction.hide(listViewFragment);
        }

        if(listView2Fragment != null){
            beginTransaction.hide(listView2Fragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dynamic_left :
                select(1);
                break;
            case R.id.dynamic_right :
                select(2);
                break;
        }

    }



}
