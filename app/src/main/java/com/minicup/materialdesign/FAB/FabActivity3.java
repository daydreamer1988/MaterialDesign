package com.minicup.materialdesign.FAB;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.minicup.materialdesign.R;

import java.util.ArrayList;
import java.util.List;

public class FabActivity3 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab3);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        initData();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new LocalAdapter());

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "fuck", 2000).setAction("go", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FabActivity3.this, "yeah", Toast.LENGTH_SHORT).show();
                    }
                }).setActionTextColor(getResources().getColor(R.color.colorAccent)).show();
            }
        });

    }

    private void initData() {
        for (int i = 0; i < 50; i++) {
            mDatas.add("index " + i);
        }
    }

    private class LocalAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(FabActivity3.this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = 100;
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            return new LocalHolder(textView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            String s = mDatas.get(position);
            ((TextView)holder.itemView).setText(s);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }

    private class LocalHolder extends RecyclerView.ViewHolder {
        private View itemView;

        public LocalHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
