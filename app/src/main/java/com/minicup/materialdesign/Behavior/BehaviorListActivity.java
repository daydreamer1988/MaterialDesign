package com.minicup.materialdesign.Behavior;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.minicup.materialdesign.ActivityInfo;
import com.minicup.materialdesign.BottomSheet.BottomSheetBehaviorActivity;
import com.minicup.materialdesign.R;
import com.minicup.materialdesign.RecyclerView.MarginItemDecoration;
import com.minicup.materialdesign.RecyclerviewAdapter;

public class BehaviorListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerviewAdapter adapter = new RecyclerviewAdapter(this, mData);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(BehaviorListActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.addItemDecoration(new MarginItemDecoration(1));
    }

    ActivityInfo[] mData = {
            new ActivityInfo("BottomSheetBehavior",
                    "CoordinatorLayout与设置了Behavior的直接子类，通过设置peekHeight,状态来展开或是收缩或是隐藏\n\n" +
                            "BottomSheetDialogFragment原理为显示了BottomSheetDialog",
                    BottomSheetBehaviorActivity.class),

            new ActivityInfo("HeaderBehavior",
                    "",
                    null),
            new ActivityInfo("HeaderScrollingViewBehavior",
                    "AppBarLayout中的子类ScrollingViewBehavior",
                    null),
            new ActivityInfo("SwipeDismissBehavior",
                    "",
                    null),
            new ActivityInfo("ViewOffsetBehavior",
                    "",
                    null),
            new ActivityInfo("简单应用"),

            new ActivityInfo("BottomSheet展开与TopSheet重合",
                    "",
                    BottomSheetApplay1Activity.class),


    };
}
