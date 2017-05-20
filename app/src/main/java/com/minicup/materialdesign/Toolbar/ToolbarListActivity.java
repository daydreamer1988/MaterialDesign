package com.minicup.materialdesign.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.minicup.materialdesign.ActivityInfo;
import com.minicup.materialdesign.MyAdapter;
import com.minicup.materialdesign.R;

public class ToolbarListActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_list);

        mListView = (ListView) findViewById(R.id.listView);

        mListView.setAdapter(new MyAdapter(this, mData));


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(mData[position].clz!=null)
                startActivity(new Intent(ToolbarListActivity.this, mData[position].clz));

            }
        });

    }

    ActivityInfo[] mData = {
        new ActivityInfo("Toolbar",
                "Toolbar简单应用",
                ToolbarActivity.class),

            new ActivityInfo("AppBarLayout+Toolbar+RecyclerView",
                    "AppBarLayout所有子View->app:layout_scrollFlags=\"scroll\"",
                    AppBarLayoutActivity.class),

            new ActivityInfo("AppBarLayout+Toolbar+RecyclerView",
                    "AppBarLayout所有子View->app:layout_scrollFlags=\"scroll|enterAlways\"",
                    AppBarLayoutActivity2.class),

            new ActivityInfo("AppBarLayout+Toolbar+RecyclerView",
                    "AppBarLayout没有设置ScrollFlag的子View会固定到屏幕最上方",
                    AppBarLayoutMultiViewActivity.class),

            new ActivityInfo("AppBarLayout+Toolbar+RecyclerView",
                    "AppBarLayout所有子View->app:layout_scrollFlags=\"scroll|exitUntilCollapsed\"",
                    AppBarLayoutActivity3.class),

            new ActivityInfo("AppBarLayout+Toolbar+RecyclerView",
                    "AppBarLayout所有子View->app:layout_scrollFlags=\"scroll|enterAlways|enterAlwaysCollapsed\"",
                    AppBarLayoutActivity4.class),
    };
}
