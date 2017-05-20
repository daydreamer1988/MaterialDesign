package com.minicup.materialdesign.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minicup.materialdesign.R;

import java.util.ArrayList;
import java.util.List;

public class StaggeredVerticalActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_vertical);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        initData();

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new GridItemDecoration(this, StaggeredGridLayoutManager.VERTICAL));

        mRecyclerView.setAdapter(new LocalAdapter());
    }

    private void initData() {
        for (int i = 'A'; i <= 'z'; i++) {
            mData.add(String.valueOf((char) i));
        }
    }

    private class LocalAdapter extends RecyclerView.Adapter<StaggeredVerticalActivity.VH> {
        @Override
        public StaggeredVerticalActivity.VH onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(StaggeredVerticalActivity.this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = 200;
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(Color.parseColor("#f3aead"));
            return new StaggeredVerticalActivity.VH(textView);
        }

        @Override
        public void onBindViewHolder(StaggeredVerticalActivity.VH holder, int position) {
            holder.textView.setText(mData.get(position));
        }



        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class VH extends RecyclerView.ViewHolder {
        public TextView textView;

        public VH(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }
}
