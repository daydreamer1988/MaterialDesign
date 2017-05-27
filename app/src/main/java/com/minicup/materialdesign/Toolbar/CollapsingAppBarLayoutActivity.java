package com.minicup.materialdesign.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minicup.materialdesign.R;
import com.minicup.materialdesign.RecyclerView.MarginItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class CollapsingAppBarLayoutActivity extends AppCompatActivity {
    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mToolbarBackgroundImageView;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private List<String> mData = new ArrayList<>();
    private LocalAdapter adapter = new LocalAdapter();
    private int statusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_app_bar_layout);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        mToolbarBackgroundImageView = (ImageView) findViewById(R.id.toolbarBackgroundImageView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initData();

        initRecyclerView();


    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.addItemDecoration(new MarginItemDecoration(3));

        mRecyclerView.setAdapter(adapter);

    }

    private void initData() {
        for (int i = 'A'; i < 'z'; i++) {
            mData.add(String.valueOf((char) i));
        }
    }


    private class LocalAdapter extends RecyclerView.Adapter<CollapsingAppBarLayoutActivity.VH> {
        @Override
        public CollapsingAppBarLayoutActivity.VH onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(CollapsingAppBarLayoutActivity.this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = 200;
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(Color.parseColor("#f3aead"));
            return new CollapsingAppBarLayoutActivity.VH(textView);
        }

        @Override
        public void onBindViewHolder(CollapsingAppBarLayoutActivity.VH holder, int position) {
            holder.textView.setText(mData.get(position));
        }



        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void addItem(int pos) {
            mData.add(pos, "One Added");
            adapter.notifyItemInserted(pos);
        }

        public void deleteItem(int pos){
            if(mData.size()>pos){
                mData.remove(pos);
                adapter.notifyItemRemoved(pos);
            }

        }
    }

    private class VH extends RecyclerView.ViewHolder {
        public TextView textView;

        public VH(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
