package com.minicup.materialdesign.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minicup.materialdesign.R;

import java.util.Locale;

public class RecyclerViewSimpleActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private String[] mData;
    private LinearLayoutManager linearLayoutVerticalManager;
    private LinearLayoutManager linearLayoutHorizontalManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_simple);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mData = initData();
        initRecyclerView();
    }


    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.addItemDecoration(new LinearCustomItemDecoration(this, LinearLayoutManager.VERTICAL));

        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View inflate = LayoutInflater.from(RecyclerViewSimpleActivity.this).inflate(android.R.layout.simple_list_item_1, null);
                return new RecyclerViewSimpleActivity.VH(inflate);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((RecyclerViewSimpleActivity.VH) holder).itemView.setText(mData[position]);
            }

            @Override
            public int getItemCount() {
                return mData.length;
            }

        });

    }

    @NonNull
    private String[] initData() {
        Locale[] availableLocales = Locale.getAvailableLocales();
        final String[] mData = new String[availableLocales.length];
        for (int i = 0; i < availableLocales.length; i++) {
            mData[i] = availableLocales[i].getDisplayLanguage();
        }
        return mData;
    }

    private class VH extends RecyclerView.ViewHolder {
        public TextView itemView;
        public VH(View inflate) {
            super(inflate);
            itemView = (TextView) inflate;
        }
    }





}
