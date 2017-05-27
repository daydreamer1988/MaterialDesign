package com.minicup.materialdesign.TabLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minicup.materialdesign.ActivityInfo;
import com.minicup.materialdesign.R;
import com.minicup.materialdesign.RecyclerView.LinearCustomItemDecoration;
import com.minicup.materialdesign.RecyclerviewAdapter;

public class TabLayoutListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.addItemDecoration(new LinearCustomItemDecoration(this, LinearLayoutManager.VERTICAL));

        mRecyclerView.setAdapter(new RecyclerviewAdapter(this, getData()));

    }

    private ActivityInfo[] getData() {
        ActivityInfo[] mData = {
            new ActivityInfo("TabLayout+ViewPager",
                    "AppBarLayout+CollapsingToolbarLayout+Toolbar+TabLayout+ViewPager\n" +
                            "setupWithViewPager(ViewPager)关联ViewPager\n" +
                            "Adapter.getPageTitle()设置Title",
                    TabLayout1Activity.class),


                new ActivityInfo("TabLayout+ViewPager",
                        "AppBarLayout+CollapsingToolbarLayout+Toolbar+TabLayout+ViewPager\n" +
                              "因为TabLayout在AppBarLayout中，收缩时无法完全收缩，所以FAB不会消失",
                        TabLayout2Activity.class),



        };
        return mData;
    }
}
