package com.minicup.materialdesign.Transition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minicup.materialdesign.ActivityInfo;
import com.minicup.materialdesign.R;
import com.minicup.materialdesign.RecyclerviewAdapter;

public class TransitionListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerviewAdapter(this, getData()));
    }

    private ActivityInfo[] getData() {
        return new ActivityInfo[]{

                new ActivityInfo("说明",
                        "",
                        TransitionDescActivity.class),

                new ActivityInfo("应用1",
                        "https://github.com/Trisaa/MaterialTranstion\n" +
                                "代码中通过slide.excludeTarget(android.R.id.navigationBarBackground,true);将不需要参与动画的View排除在外\n" +
                                "xml中\n" +
                                "<transitionSet xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                                "    <slide android:duration=\"500\">\n" +
                                "        <targets >\n" +
                                "            <!--表示除了状态栏-->\n" +
                                "            <target android:excludeId=\"@android:id/statusBarBackground\"/>\n" +
                                "            <!--表示只针对状态栏-->\n" +
                                "            <!--<target android:targetId=\"@android:id/statusBarBackground\"/>-->\n" +
                                "        </targets>\n" +
                                "    </slide>\n" +
                                "</transitionSet>",
                        SimpleTransitionActivity.class),
                new ActivityInfo("应用2",
                        "TransitionManager, Scene的使用",
                        SimpleTransition2Activity.class),

                new ActivityInfo("TransitionDrawable",
                        "",
                        TransitionDrawableActivity.class),

                new ActivityInfo("自定义Transition",
                        "自定义的Transition:ChangeColor, TransitionManager.go(scene, transition)",
                        CustomTransitionActivity.class),
        };
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
