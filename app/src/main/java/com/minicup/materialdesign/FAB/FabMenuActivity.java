package com.minicup.materialdesign.FAB;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.minicup.materialdesign.R;

public class FabMenuActivity extends AppCompatActivity {
    private FloatingActionMenu mFabMenu;
    private FloatingActionButton mFab1;
    private FloatingActionButton mFab2;
    private FloatingActionButton mFab3;
    private LocalListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_menu);

        mFabMenu = (FloatingActionMenu) findViewById(R.id.fabMenu);


        mFab1 = (FloatingActionButton) findViewById(R.id.fab1);
        mFab2 = (FloatingActionButton) findViewById(R.id.fab2);
        mFab3 = (FloatingActionButton) findViewById(R.id.fab3);

        mFabMenu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean b) {
                //Toast.makeText(FabMenuActivity.this, "Menu打开否：" + b, Toast.LENGTH_SHORT).show();
            }
        });

        listener = new LocalListener();

        mFab1.setOnClickListener(listener);
        mFab2.setOnClickListener(listener);
        mFab3.setOnClickListener(listener);

    }

    private class LocalListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.fab1:
                    break;
                case R.id.fab2:
                    break;
                case R.id.fab3:
                    break;
            }
            mFabMenu.toggle(true);
        }
    }
}
