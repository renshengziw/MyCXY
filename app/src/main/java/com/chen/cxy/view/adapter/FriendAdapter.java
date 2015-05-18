package com.chen.cxy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.chen.cxy.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/14.
 */
public class FriendAdapter extends SimpleAdapter {
    Context context;
    LayoutInflater inflater;
    TextView count_tv;

    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    public FriendAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = LayoutInflater.from(this.context);

        if(convertView == null){
            convertView = super.getView(position, convertView, parent);
        }

        count_tv = (TextView) convertView.findViewById(R.id.count_tv);

        String count = count_tv.getText().toString();
        if(count.equals("0")){
            count_tv.setVisibility(View.GONE);
        }

        return convertView;
    }
}
