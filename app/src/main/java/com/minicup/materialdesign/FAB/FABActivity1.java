package com.minicup.materialdesign.FAB;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class FABActivity1 extends AppCompatActivity {
    private FloatingActionButton mFab;
    private TextView mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab1);

        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mDesc = (TextView) findViewById(R.id.desc);
        mDesc.setText(
                "android:src->设置图标\n" +
                        "app:rippleColor->点击后的效果\n" +
                        "app:useCompatPadding->使用兼容的padding, 防止阴影被切掉\n" +
                        "app:elevation->正常显示的阴影大小\n" +
                        "app:pressedTranslationZ->点击后显示的阴影大小\n" +
                        "app:layout_anchor->设置锚点\n" +
                        "app:layout_anchorGravity=\"bottom|end\""+
                        "如果5.x系统无阴影：app:borderWidth=\"0dp\"\n" +
                        "如果FAB在普通父布局中，显示Snackbar时会有些问题"
        );

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "fuck", 1000).show();
            }
        });
    }
}
