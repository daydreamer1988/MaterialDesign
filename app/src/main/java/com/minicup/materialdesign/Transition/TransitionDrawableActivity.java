package com.minicup.materialdesign.Transition;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class TransitionDrawableActivity extends AppCompatActivity {
    private RelativeLayout mActivityTransitionDrawable;
    private ImageView mImageView;
    private ImageView mImageView2;
    private TransitionDrawable drawable;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_drawable);

        mActivityTransitionDrawable = (RelativeLayout) findViewById(R.id.activity_transition_drawable);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mText = (TextView) findViewById(R.id.text);
        mText.setText("1." +
                "<ImageView\n" +
                "    android:layout_width=\"wrap_content\"\n" +
                "    android:layout_height=\"wrap_content\"\n" +
                "    android:id=\"@+id/imageView\" />\n" +
                "\n" +
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<transition xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                "    <item android:drawable=\"@drawable/circle_1_red\" />\n" +
                "    <item android:drawable=\"@drawable/circle_2_yellow\" />\n" +
                "</transition>\n" +
                "\n" +
                "drawable = (TransitionDrawable) getResources().getDrawable(R.drawable.transition);\n\n" +
                "mImageView.setImageDrawable(drawable);\n" +
                "drawable.startTransition(1000);\n" +
                "\n" +
                "2.\n" +
                "<ImageView\n" +
                "    android:layout_width=\"wrap_content\"\n" +
                "    android:layout_height=\"wrap_content\"\n" +
                "    android:src=\"@drawable/transition\"\n" +
                "    android:id=\"@+id/imageView2\" />\n" +
                "\n" +
                "Drawable drawable = mImageView2.getDrawable();\n" +
                "if (drawable instanceof TransitionDrawable) {\n" +
                "    //((TransitionDrawable) drawable).startTransition(1000);\n" +
                "    ((TransitionDrawable) drawable).reverseTransition(1000);\n" +
                "}");
        drawable = (TransitionDrawable) getResources().getDrawable(R.drawable.transition);
        mImageView.setImageDrawable(drawable);

        mActivityTransitionDrawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawable.startTransition(1000);

                Drawable drawable = mImageView2.getDrawable();
                if (drawable instanceof TransitionDrawable) {
                    //((TransitionDrawable) drawable).startTransition(1000);
                    ((TransitionDrawable) drawable).reverseTransition(1000);
                }

            }
        });
    }
}
