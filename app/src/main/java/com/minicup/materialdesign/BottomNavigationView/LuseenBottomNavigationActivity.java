package com.minicup.materialdesign.BottomNavigationView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewAnimationUtils;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.minicup.materialdesign.Fragments.TabLayoutFragment;
import com.minicup.materialdesign.R;

import java.util.ArrayList;

import static com.minicup.materialdesign.R.id.bottomNavigationView;

public class LuseenBottomNavigationActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private ArrayList<TabLayoutFragment> fragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luseen_bottom_navigation);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mBottomNavigationView = (BottomNavigationView) findViewById(bottomNavigationView);


        mBottomNavigationView.isWithText(false);//是否全部显示title的文字
        //mBottomNavigationView.activateTabletMode();//在左侧边

//        mBottomNavigationView.isColoredBackground(false);//不再有背景颜色，与官方BottomNavigationView默认一样
//        mBottomNavigationView.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(this, R.color.firstColor));//如果没有背景颜色 ，设置选中的颜色（ICON与TEXT同一颜色）

        mBottomNavigationView.setTextActiveSize(getResources().getDimension(R.dimen.text_active));
        mBottomNavigationView.setTextInactiveSize(getResources().getDimension(R.dimen.text_inactive));
        mBottomNavigationView.setFont(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Noh_normal.ttf"));
        mBottomNavigationView.disableShadow();


        int[] image = {R.drawable.ic_mic_black_24dp, R.drawable.ic_favorite_black_24dp,
                R.drawable.ic_book_black_24dp, R.drawable.github_circle};
        int[] color = {ContextCompat.getColor(this, R.color.firstColor), ContextCompat.getColor(this, R.color.secondColor),
                ContextCompat.getColor(this, R.color.thirdColor), ContextCompat.getColor(this, R.color.fourthColor)};


        //手动添加item

       /* BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("Record", color[0], image[0]);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("Like", color[1], image[1]);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("Books", color[2], image[2]);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("GitHub", color[3], image[3]);


        mBottomNavigationView.addTab(bottomNavigationItem);
        mBottomNavigationView.addTab(bottomNavigationItem1);
        mBottomNavigationView.addTab(bottomNavigationItem2);
        mBottomNavigationView.addTab(bottomNavigationItem3);*/


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

        mBottomNavigationView.setUpWithViewPager(mViewPager, color, image);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //mBottomNavigationView.getItem(position);
                mBottomNavigationView.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
