package com.minicup.materialdesign.MaterialDesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minicup.materialdesign.ActivityInfo;
import com.minicup.materialdesign.R;
import com.minicup.materialdesign.RecyclerviewAdapter;

public class MaterialDesignListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new RecyclerviewAdapter(this, getData()));
    }

    private ActivityInfo[] getData() {
        ActivityInfo[] mData = {
            new ActivityInfo("Elevation, TranslationZ(4.4不兼容，会崩)",
                    "Z = elevation + translationZ\n" +
                            "布局设置elevation\n" +
                            "代码设置setTranslationZ\n" +
                            "View.animate().z(int)\n" +
                            "View.animate().translationZ(int)",
                    ZActivity.class),

                new ActivityInfo("Shadows and Outlines（4.4不兼容，会崩）",
                        "ViewOutlineProvider.getOutline()方法会被多次调用",
                        ShadowAndOutlineActivity.class),

                new ActivityInfo("ViewDragHelper",
                        "",
                        ViewDragHelperActivity.class),

                new ActivityInfo("Drawable Tint",
                        "只用一个白色png图片，通过tint属性来改变着色颜色，原图中只有白色部分为不透明的",
                        TintActivity.class),

                new ActivityInfo("Ripple（4.4无效果，不会崩）",
                        "",
                        RippleActivity.class),

                new ActivityInfo("Reveal（4.4不兼容，会崩）",
                        "目前View4点击底部边缘有BUG",
                        RevealActivity.class),

                new ActivityInfo("Circular Reveal（4.4不兼容，会崩）",
                        "ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius)",
                        CircularRevealActivity.class),

        };
        return mData;
    }
}
