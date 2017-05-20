package com.minicup.materialdesign.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minicup.materialdesign.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAnimationActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mData = new ArrayList<>();
    private LocalAdapter adapter;



    private StaggeredGridLayoutManager staggeredGridLayoutManagerVertical;

    private StaggeredItemDecoration staggeredItemDecoration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_animation);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initLayoutManager();
        initData();
        mRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
        adapter = new LocalAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(staggeredItemDecoration);
    }

    private void initLayoutManager() {

        staggeredItemDecoration = new StaggeredItemDecoration(10);

        staggeredGridLayoutManagerVertical = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    }

    private void initData() {
        for (int i = 'A'; i <= 'z'; i++) {
            mData.add(String.valueOf((char) i));
        }
    }

    private class LocalAdapter extends RecyclerView.Adapter<ItemAnimationActivity.VH> {
        @Override
        public ItemAnimationActivity.VH onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(ItemAnimationActivity.this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = getRandomHeight();
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(Color.parseColor("#f3aead"));
            return new ItemAnimationActivity.VH(textView);
        }

        @Override
        public void onBindViewHolder(ItemAnimationActivity.VH holder, int position) {
            holder.textView.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void addItem(int position) {
            mData.add(position, "One Added");
            adapter.notifyItemInserted(position);

        }

        public void deleteItem(int position){
            if(mData.size()>position) {
                mData.remove(position);
                adapter.notifyItemRemoved(position);
            }
        }
    }

    private int getRandomHeight() {
        return (int) (Math.random() * 400 + 300);
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
        getMenuInflater().inflate(R.menu.recyclerview_add_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                adapter.addItem(1);
                break;
            case R.id.delete:
                adapter.deleteItem(1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
