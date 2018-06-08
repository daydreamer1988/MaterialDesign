package com.minicup.materialdesign.BottomNavigationView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.minicup.materialdesign.Fragments.TabLayoutFragment;
import com.minicup.materialdesign.R;

import java.util.ArrayList;

public class BottomNavigationViewPagerActivity2 extends AppCompatActivity {
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private ArrayList<TabLayoutFragment> fragmentList = new ArrayList<>();
    private MenuItem lastMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_view_pager2);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

        for (int i = 0; i < 4; i++) {
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
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(lastMenuItem==null){
                    mBottomNavigationView.getMenu().getItem(0).setChecked(true);
                }else {
                    lastMenuItem.setChecked(false);
                }
                MenuItem item = mBottomNavigationView.getMenu().getItem(position);
                item.setChecked(true);
                lastMenuItem = item;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        mViewPager.setCurrentItem(0,true);
                        break;
                    case R.id.item2:
                        mViewPager.setCurrentItem(1, true);
                        break;
                    case R.id.item3:
                        mViewPager.setCurrentItem(2, true);
                        break;
                    case R.id.item4:
                        mViewPager.setCurrentItem(3, true);
                        break;
                }
                return false;
            }
        });

    }
}
