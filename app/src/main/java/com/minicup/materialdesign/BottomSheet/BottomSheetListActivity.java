package com.minicup.materialdesign.BottomSheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minicup.materialdesign.ActivityInfo;
import com.minicup.materialdesign.R;
import com.minicup.materialdesign.RecyclerView.MarginItemDecoration;
import com.minicup.materialdesign.RecyclerviewAdapter;

public class BottomSheetListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new RecyclerviewAdapter(this, getData()));

        mRecyclerView.addItemDecoration(new MarginItemDecoration(1));

    }

    private ActivityInfo[] getData() {
        ActivityInfo[] info = {
                new ActivityInfo("BottomSheetDialog",
                        "与Dialog使用一般无异\n" +
                                "Dialog的ContentView为RecyclerView时，设置PeekHeight及MaxHeight\n" +
                                "设置Dialog沉浸状态栏",
                        BottomSheetDialogActivity.class),


                new ActivityInfo("BottomSheetBehavior",
                        "CoordinatorLayout与设置了Behavior的直接子类，通过设置peekHeight,状态来展开或是收缩或是隐藏\n\n" +
                                "BottomSheetDialogFragment原理为显示了BottomSheetDialog",
                        BottomSheetBehaviorActivity.class),

        };
        return info;
    }
}
