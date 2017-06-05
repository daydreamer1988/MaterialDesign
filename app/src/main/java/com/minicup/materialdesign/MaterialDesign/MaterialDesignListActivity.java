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
            new ActivityInfo("Elevation, TranslationZ",
                    "Z = elevation + translationZ\n" +
                            "布局设置elevation\n" +
                            "代码设置setTranslationZ\n" +
                            "View.animate().z(int)\n" +
                            "View.animate().translationZ(int)",
                    ZActivity.class),

                new ActivityInfo("Shadows and Outlines",
                        "ViewOutlineProvider.getOutline()方法会被多次调用",
                        ShadowAndOutlineActivity.class),

                new ActivityInfo("ViewDragHelper",
                        "",
                        ViewDragHelperActivity.class),

                new ActivityInfo("Drawable Tint",
                        "只用一个白色png图片，通过tint属性来改变着色颜色，原图中只有白色部分为不透明的",
                        TintActivity.class),

                new ActivityInfo("Ripple",
                        "",
                        RippleActivity.class),

                new ActivityInfo("Circular Reveal",
                        "ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius)",
                        CircularRevealActivity.class),

        };
        return mData;
    }
}
