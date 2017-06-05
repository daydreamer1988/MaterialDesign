package com.minicup.materialdesign.NotificationCompat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minicup.materialdesign.ActivityInfo;
import com.minicup.materialdesign.R;
import com.minicup.materialdesign.RecyclerviewAdapter;

public class NotificationCompatListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_compat_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new RecyclerviewAdapter(this, getData()));
    }

    private ActivityInfo[] getData() {
        ActivityInfo[] mData = {
            new ActivityInfo("BigTextStyle",
                    "",
                    BitTextStyleActivity.class),

                new ActivityInfo("BigPictureStyle",
                        "",
                        BigPictureStyleActivity.class),

                new ActivityInfo("InboxStyle",
                        "",
                        InboxStyleActivity.class),
        };

        return mData;
    }


}
