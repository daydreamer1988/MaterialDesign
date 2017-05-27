package com.minicup.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.minicup.materialdesign.Behavior.BehaviorListActivity;
import com.minicup.materialdesign.BottomSheet.BottomSheetListActivity;
import com.minicup.materialdesign.FAB.FABActivity;
import com.minicup.materialdesign.RecyclerView.RecyclerViewListActivity;
import com.minicup.materialdesign.Snackbar.SnackbarActivity;
import com.minicup.materialdesign.TabLayout.TabLayoutListActivity;
import com.minicup.materialdesign.Toolbar.ToolbarListActivity;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new MyAdapter(this, mDatas));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mDatas[position].clz!=null)
                startActivity(new Intent(MainActivity.this, mDatas[position].clz));
            }
        });

    }

    public ActivityInfo[] mDatas = {

            new ActivityInfo("FAB",
                    "",
                    FABActivity.class),
            new ActivityInfo("Snackbar",
                    "",
                    SnackbarActivity.class),


            new ActivityInfo("Toolbar",
                    "",
                    ToolbarListActivity.class),

            new ActivityInfo("TabLayout",
                    "",
                    TabLayoutListActivity.class),

            new ActivityInfo("NavigationView",
                    "",
                    null),

            new ActivityInfo("RecyclerView",
                    "",
                    RecyclerViewListActivity.class),

            new ActivityInfo("BottomSheet",
                    "",
                    BottomSheetListActivity.class),

            new ActivityInfo("Behavior",
                    "",
                    BehaviorListActivity.class),
    };

}
