package com.minicup.materialdesign.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minicup.materialdesign.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewVerticalActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mData = new ArrayList<>();
    private LocalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_horizontal);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initData();



        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mRecyclerView.addItemDecoration(new LinearCustomItemDecoration(this, LinearLayoutManager.VERTICAL));

        adapter = new LocalAdapter();
        mRecyclerView.setAdapter(adapter);

    }

    private void initData() {
        for (int i = 'A'; i <= 'z'; i++) {
            mData.add((String.valueOf((char)i)));
        }
    }

    private class LocalAdapter extends RecyclerView.Adapter<VH> {
        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(RecyclerViewVerticalActivity.this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = 200;
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(Color.parseColor("#f3aead"));
            return new VH(textView);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
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
        getMenuInflater().inflate(R.menu.recyclerview_add_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
