package com.minicup.materialdesign.TabLayout;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.minicup.materialdesign.Fragments.TabLayoutFragment;
import com.minicup.materialdesign.R;

import java.util.ArrayList;
import java.util.List;


public class TabLayout2Activity extends AppCompatActivity {
    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mImageView;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private FloatingActionButton mFab;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout2);
        findById();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        for (int i = 0; i < 3; i++) {
            fragmentList.add(TabLayoutFragment.newInstance(i + "", null));
        }

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Page" + position;
//                return "";
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mTabLayout.setupWithViewPager(mViewPager, true);


        TabLayout.Tab tab = mTabLayout.newTab();
        tab.setIcon(R.mipmap.heart);
        mTabLayout.addTab(tab,0);

        TabLayout.Tab tabAt = mTabLayout.getTabAt(1);
        tabAt.setIcon(R.mipmap.heart);
    }

    private void findById() {

        mTitle = (TextView) findViewById(R.id.title);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
    }



}
