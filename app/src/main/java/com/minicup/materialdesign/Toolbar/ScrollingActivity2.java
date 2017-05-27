package com.minicup.materialdesign.Toolbar;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class ScrollingActivity2 extends AppCompatActivity {
    private AppBarLayout mAppBar;
    private CollapsingToolbarLayout mToolbarLayout;
    private Toolbar mToolbar;
    private TextView mTitle;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling2);
        findByid();
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                展开为0，完全收缩为AppBar的总滑动距离(appBarLayout.getTotalScrollRange());
//                Log.e("TAG", "verticalOffset:" + verticalOffset);
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                float alpha = verticalOffset * 1.0f / totalScrollRange;
                mTitle.setAlpha(Math.abs(alpha));
            }
        });
    }

    private void findByid() {

        mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) findViewById(R.id.title);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
