package com.minicup.materialdesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Austin on 2017-05-09.
 */
public class MyAdapter extends BaseAdapter {
    private final Context context;
    private final ActivityInfo[] mDatas;
    private final LayoutInflater layoutInflater;

    public MyAdapter(Context context, ActivityInfo[] mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.length;
    }

    @Override
    public Object getItem(int position) {
        return mDatas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item_list, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(mDatas[position].title);

        TextView desc = (TextView) view.findViewById(R.id.desc);
        desc.setText(mDatas[position].desc);

        return view;
    }
}
