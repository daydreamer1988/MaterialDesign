package com.minicup.materialdesign.RecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minicup.materialdesign.ActivityInfo;
import com.minicup.materialdesign.R;
import com.minicup.materialdesign.RecyclerviewAdapter;
import com.minicup.materialdesign.SwipeCards.CardsActivity;

public class RecyclerViewListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new LinearCustomItemDecoration(this, LinearLayoutManager.VERTICAL));
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(this, getData());
        mRecyclerView.setAdapter(adapter);

    }

    public ActivityInfo[] getData() {
        ActivityInfo[] data = {

                new ActivityInfo("简单应用",
                        "statusbar透明, LinearLayoutManager, ItemDecoration",
                        RecyclerViewSimpleActivity.class),

                /////////////////////////////////////////////////////////////////////////////////////
                new ActivityInfo(),
                new ActivityInfo("LinearLayoutManager(Vertical)",
                        "最后一条数据没有Divider\n" +
                                "LinearCustomItemDecoration(this,LinearLayoutManager.VERTICAL)",
                        RecyclerViewVerticalActivity.class),

                new ActivityInfo("LinearLayoutManager(Horizontal)",
                        "最后一条数据没有Divider\n" +
                                "LinearCustomItemDecoration(this, LinearLayoutManager.HORIZONTAL)",
                        RecyclerviewHorizontalActivity.class),

                /////////////////////////////////////////////////////////////////////////////////////
                new ActivityInfo("以下GridLayoutManager与StaggeredGridLayoutManager的ItemDecoration都是用的GridItemDecoration"),

                new ActivityInfo("GridLayoutManager(Vertical)",
                        "最后一行，最后一列，最后一项孝没有Divider\n" +
                                "GridCustomItemDecoration(this, GridLayoutManager.VERTICAL)",
                        GridLayoutManagerVerticalActivity.class),

                new ActivityInfo("GridLayoutManager(Horizontal)",
                        "最后一行，最后一列，最后一项孝没有Divider\n" +
                                "GridCustomItemDecoration(this, GridLayoutManager.HORIZONTAL)",
                        GridLayoutManagerHorizontalActivity.class),

                new ActivityInfo("StaggeredGridLayoutManager(Vertical)",
                        "与GridLayoutManager(Vertical)效果相似\n" +
                                "GridCustomItemDecoration(this, StaggeredGridLayoutManager.VERTICAL)",
                        StaggeredVerticalActivity.class),

                new ActivityInfo("StaggeredGridLayoutManager(Horizontal)",
                        "与GridLayoutManager(Horizontal)效果相似\n" +
                                "GridCustomItemDecoration(this, StaggeredGridLayoutManager.Horizontal)",
                        StaggeredHorizontalActivity.class),

                /////////////////////////////////////////////////////////////////////////////////////

                new ActivityInfo("瀑布流"),

                new ActivityInfo("瀑布流(Vertical)",
                        "空白间隔，MarginItemDecoration",
                        MultiSizeStaggeredVerticalActivity.class),

                new ActivityInfo("瀑布流(Horizontal)",
                        "空白间隔，MarginItemDecoration",
                        MultiSizeStaggeredHorizontalActivity.class),
                /////////////////////////////////////////////////////////////////////////////////////
                new ActivityInfo("ItemAnimator"),

                new ActivityInfo("默认动画添加ITEM，删除ITEM",
                        "系统默认ItemAnimator,即使不设置",
                        ItemAnimationActivity.class),


                /////////////////////////////////////////////////////////////////////////////////////
                new ActivityInfo(),
                new ActivityInfo("翻牌效果",
                        "https://github.com/mcxtzhang/ZLayoutManager",
                        CardsActivity.class),

                new ActivityInfo("干货集中营",
                        "CardView布局，点击效果\n" +
                                "ItemTouchHelper:左右滑删除，长按选中移动位置\n" +
                                "SwipRefreshLayout下拉刷新\n" +
                                "如果可以确定每个item的高度是固定的，设置setHasFixedSize(true)可以提高性能",
                        MeiziActivity.class),
                new ActivityInfo("Staggered妹子",
                        "mLayoutManager.setGapStrategy()来设置返回顶部后布局移动的问题",
                        MeiziStaggeredActivity.class),
                new ActivityInfo("下拉刷新，上拉加载",
                        "第三方SuperRefreshLayout",
                        MeiziBothRefreshActivity.class),
        };

        return data;
    }
}
