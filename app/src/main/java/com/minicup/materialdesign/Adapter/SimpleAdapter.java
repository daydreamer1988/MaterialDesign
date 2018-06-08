package com.minicup.materialdesign.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minicup.materialdesign.Holder.SimpleViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Austin on 2017-06-08.
 */

public abstract class SimpleAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> mData = new ArrayList<>();

    public SimpleAdapter(Context context) {
        this.context = context;
        initData();
    }

    private List<String> initData() {
        Locale[] availableLocales = Locale.getAvailableLocales();
        for (Locale availableLocale : availableLocales) {
            mData.add(availableLocale.getDisplayLanguage());
        }
        return mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        inflate.setLayoutParams(layoutParams);
        return new SimpleViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((SimpleViewHolder) holder).itemView.setText(mData.get(position));
        ((SimpleViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, position);
            }
        });
    }

    public abstract void onItemClick(View v, int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
