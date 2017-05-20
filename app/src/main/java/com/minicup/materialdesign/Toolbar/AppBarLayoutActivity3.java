package com.minicup.materialdesign.Toolbar;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minicup.materialdesign.R;

import java.util.Locale;

public class AppBarLayoutActivity3 extends AppCompatActivity {
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout3);

        mAppBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        initRecyclerView();


    }

    private void initRecyclerView() {

        Locale[] availableLocales = Locale.getAvailableLocales();

        final String[] mData = new String[availableLocales.length];

        for (int i = 0; i < availableLocales.length; i++) {
            mData[i] = availableLocales[i].getDisplayLanguage();
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View inflate = LayoutInflater.from(AppBarLayoutActivity3.this).inflate(android.R.layout.simple_list_item_1, null);
                return new AppBarLayoutActivity3.VH(inflate);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((AppBarLayoutActivity3.VH)holder).itemView.setText(mData[position]);
            }

            @Override
            public int getItemCount() {
                return mData.length;
            }

        });

    }

    private class VH extends RecyclerView.ViewHolder {
        public TextView itemView;
        public VH(View inflate) {
            super(inflate);
            itemView = (TextView) inflate;
        }
    }
}
