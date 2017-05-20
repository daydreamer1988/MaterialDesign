package com.minicup.materialdesign.Toolbar;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minicup.materialdesign.R;

import java.util.Locale;

public class AppBarLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        initRecyclerView();


        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                Log.e("TAG", "verticalOffset:" + verticalOffset);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy<=0){
                    mAppBarLayout.setExpanded(true, true);
                }
//                Log.e("TAG", "dy:" + dy);
            }

        });


    }

    private void initRecyclerView() {

        Locale[] availableLocales = Locale.getAvailableLocales();

        final String[] mData = new String[availableLocales.length];

        for (int i = 0; i < availableLocales.length; i++) {
            mData[i] = availableLocales[i].getDisplayLanguage();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View inflate = LayoutInflater.from(AppBarLayoutActivity.this).inflate(android.R.layout.simple_list_item_1, null);
                return new VH(inflate);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((VH)holder).itemView.setText(mData[position]);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scrollmode, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.scroll:
               /* ViewGroup.LayoutParams layoutParams = mAppBarLayout.getLayoutParams();
                AppBarLayout.LayoutParams params = new AppBarLayout.LayoutParams(layoutParams);
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);*/

                mAppBarLayout.setExpanded(false, true);

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
