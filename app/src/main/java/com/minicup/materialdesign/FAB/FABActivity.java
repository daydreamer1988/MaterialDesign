package com.minicup.materialdesign.FAB;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.minicup.materialdesign.ActivityInfo;
import com.minicup.materialdesign.MyAdapter;
import com.minicup.materialdesign.R;

public class FABActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);

        mListView = (ListView) findViewById(R.id.listView);

        mListView.setAdapter(new MyAdapter(this, mDatas));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(FABActivity.this, mDatas[position].clz));
            }
        });

    }

    ActivityInfo[] mDatas = {
      new ActivityInfo("Sample1",
              "普通父布局",
              FABActivity1.class),

            new ActivityInfo("Sample2",
                    "父布局：CoordinatorLayout",
                    FABActivity2.class ),
            new ActivityInfo("Sample3",
                    "RecyclerView+FAB+Behavior",
                    FabActivity3.class),

            new ActivityInfo("Sample4",
                    "Behavior自定义动画",
                    FabActivity4.class),

            new ActivityInfo("Sample5",
                    "ListView + FAB\n" +
                            "BUG:快速滑动动画执行多次",
                    FABActivity5.class),
            new ActivityInfo("FabMenu",
                    "FloatingActionMenu+子菜单FloatingActionButton",
                    FabMenuActivity.class),
    };
}
