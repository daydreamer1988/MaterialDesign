package com.minicup.materialdesign.BottomNavigationView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minicup.materialdesign.ActivityInfo;
import com.minicup.materialdesign.R;
import com.minicup.materialdesign.RecyclerviewAdapter;

public class BottomNavigationViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.addItemDecoration(new LinearCustomItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new RecyclerviewAdapter(this, getData()));
    }

    private ActivityInfo[] getData() {
        ActivityInfo[] mData = {

                new ActivityInfo("Basic",
                        "基本用法",
                        BasicBottomNavigationViewActivity.class),

                new ActivityInfo("BottomNavigationView与ViewPager",
                        "itemIconTint用selector设置选择选中与不选中的状态\n" +
                                "ViewPager,BNV都未设置background,并且BNV未显示阴影效果时，ripple会完全扩展",
                        BottomNavigationViewPagerActivity.class),

                new ActivityInfo("BottomNavigationView与ViewPager",
                        "通过反射，不带ShiftMode\n" +
                                "通过ripple drawable设置itemBackground,实现点击ripple效果(drawable-v21)\n" +
                                "如果上面的ViewPager与下面的BNV颜色不一样，并有阴影效果时，ripple只会在控件里面，不会超出控件的范围",
                        BottomNavigationViewPagerActivity2.class),

                new ActivityInfo("LuseenBottomNavigation",
                        "com.github.armcha:LuseenBottomNavigation:1.8.2\n" +
                                "通过在AndroidMenifest.xml中设置：\n<uses-sdk\n" +
                                "        tools:overrideLibrary=\"com.luseen.luseenbottomnavigation\"\n" +
                                "/>\n" +
                                "可以解决因为minsdk而编译不通过",
                        LuseenBottomNavigationActivity.class),

        };



        return mData;

    }
}
