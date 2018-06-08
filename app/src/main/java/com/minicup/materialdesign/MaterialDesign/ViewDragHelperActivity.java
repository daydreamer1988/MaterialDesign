package com.minicup.materialdesign.MaterialDesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.minicup.materialdesign.R;

public class ViewDragHelperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drag_helper);
        int scaledTouchSlop = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
        Toast.makeText(this, "系统TouchSlop:" + scaledTouchSlop, Toast.LENGTH_SHORT).show();

    }

    public void buttonClicked(View view) {
        Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show();
    }
}
