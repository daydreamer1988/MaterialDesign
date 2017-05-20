package com.minicup.materialdesign.Toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.minicup.materialdesign.R;

public class ToolbarActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDesc = (TextView) findViewById(R.id.desc);

        mToolbar.setTitle("CodeTitle");
        mToolbar.setSubtitle("CodeSubtitle");
        mToolbar.setLogo(getResources().getDrawable(R.mipmap.heart));
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_name));


        setSupportActionBar(mToolbar);



        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDesc.setText("" +
                "<android.support.v7.widget.Toolbar\n" +
                "        android:id=\"@+id/toolbar\"\n" +
                "        android:layout_width=\"match_parent\"\n" +
                "        android:layout_height=\"wrap_content\"\n" +
                "        android:minHeight=\"?attr/actionBarSize\"\n" +
                "\n" +
                "        app:logo=\"@mipmap/ic_launcher\"\n" +
                "\n" +
                "        app:title=\"Title\"\n" +
                "        app:titleTextColor=\"@color/white\"\n" +
                "\n" +
                "        app:subtitle=\"Subtitle\"\n" +
                "        app:subtitleTextColor=\"@color/white\"\n" +
                "\n" +
                "        android:background=\"@color/colorPrimary\"\n" +
                "\n" +
                "        app:navigationIcon=\"@drawable/fab_add\"\n" +
                "\n        app:theme=\"@style/Base.ThemeOverlay.AppCompat.Dark.ActionBar\"(设置MENU颜色为白色)\n" +
                "\n" +
                "        app:popupTheme=\"@style/OverFlowMenuTheme\"(设置弹出菜单不overlay)\n" +
                "        android:elevation=\"3dp\">\n" +
                "</android.support.v7.widget.Toolbar>\n" +
                "\n\n" +
                "代码设置：\n" +
                "mToolbar.setTitle();\n" +
                "mToolbar.setSubtitle();\n" +
                "mToolbar.setLogo();\n" +
                "mToolbar.setNavigationIcon();\n\n" +
                "涉及：Toolbar的Title Subtitle Background Logo NavigationIcon Elevation");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(getApplicationContext(), "item1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(getApplicationContext(), "item2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item3:
                Toast.makeText(getApplicationContext(), "item3", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
