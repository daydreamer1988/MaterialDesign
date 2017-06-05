package com.minicup.materialdesign.BottomNavigationView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class BasicBottomNavigationViewActivity extends AppCompatActivity {
    private TextView mTextView;
    private BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_bottom_navigation_view);
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText("" +
                "底部导航栏高度默认是 56dp\n\n" +
                "菜单元素只能是 3~5 个。如果个数少于3个或者多于5个，则会报错\n\n" +
                "icon 的选中颜色默认是 @color/colorPrimary。当然你也可以使用 app:itemIconTint=\"@android:color/white\" 来自定义，这样定以后，所有的 icon 颜色都是这个了\n\n" +
                "菜单元素文字的默认颜色是 @color/colorPrimary。你可以使用 app:itemTextColor=\"@android:color/white\" 自定义\n\n" +
                "底部导航栏背景颜色默认是当前样式的背景色（白色/黑色），你可以使用 app:itemBackground=\"@android:color/black\" 来更改\n\n" +
                "app:menu=\"@menu/bottomnavigationview\"通过Menu设置条目\n\n" +
                "app:itemTextColor=\"@color/colorAccent\"\n\n" +
                "app:itemIconTint=\"@color/colorAccent\"所有的颜色都就能（默认选择变色，不选中灰色）\n\n" +
                "app:itemBackground=\"@color/grayE\"");
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mTextView.setText(item.getTitle().toString().toUpperCase());
                return true;
            }
        });
    }
}
