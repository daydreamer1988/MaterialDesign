package com.minicup.materialdesign.Navigation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minicup.materialdesign.ActivityInfo;
import com.minicup.materialdesign.R;
import com.minicup.materialdesign.RecyclerView.LinearCustomItemDecoration;
import com.minicup.materialdesign.RecyclerviewAdapter;

public class NavigationListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new LinearCustomItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new RecyclerviewAdapter(this, getData()));
    }

    private ActivityInfo[] getData() {
        ActivityInfo[] mData = {
            new ActivityInfo("系统NavigationView",
                    "DrawerLayout+NavigationView",
                    NavigationSystemActivity.class),
        };
        return mData;
    }
}
