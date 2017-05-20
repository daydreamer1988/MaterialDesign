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

public class FabActivity4 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;

    private List<String> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab4);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        for (int i = 0; i < 40; i++) {
            mData.add("index " + i);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(FabActivity4.this));
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView textView = new TextView(FabActivity4.this);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.height = 100;
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(params);
                return new LocalHolder(textView);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                String s = mData.get(position);
                ((TextView) holder.itemView).setText(s);

            }

            @Override
            public int getItemCount() {
                return mData.size();
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "fuck", 1000).setAction("go", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FabActivity4.this, "yeah", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

    }

    class LocalHolder extends RecyclerView.ViewHolder {
        private View itemView;

        public LocalHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
