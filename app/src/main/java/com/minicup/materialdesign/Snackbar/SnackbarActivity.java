package com.minicup.materialdesign.Snackbar;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.minicup.materialdesign.R;

public class SnackbarActivity extends AppCompatActivity {
    private FloatingActionButton mFab;
    private TextView mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        mDesc = (TextView) findViewById(R.id.desc);

        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mDesc.setText("" +
                "设置Snackbar的字体颜色：\n\n" +
                "方法1：\n" +
                "Spanned spanned = Html.fromHtml(\"<font color=\\\"#00ffff\\\">我是蓝色字体</font> \");\n" +
                "Snackbar.make(v, spanned, Snackbar.LENGTH_INDEFINITE).show()\n\n" +
                "方法2：\n" +
                "Snackbar snackbar = Snackbar.make(v, \"Hello Snackbar\", Snackbar.LENGTH_INDEFINITE);\n" +
                "((TextView) snackbar.getView().findViewById(R.id.snackbar_text)).setTextColor(Color.parseColor(\"#ff0000\"));\n" +
                "snackbar.show();\n\n" +
                "设置Snackbar背景颜色：\n" +
                "View view = snackbar.getView();\n" +
                "view.setBackgroundColor(Color.parseColor(\"#ffff00\"));\n\n" +
                "设置Message的DrawableLeft及Gravity\n" +
                "TextView textView = (TextView) view.findViewById(R.id.snackbar_text);\n" +
                "textView.setGravity(Gravity.CENTER_VERTICAL);\n" +
                "Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);\n" +
                "drawable.setBounds(0, 0, drawable.getIntrinsicWidth()/2, drawable.getIntrinsicHeight()/2);(设置图片的大小)\n" +
                "textView.setCompoundDrawables(drawable, null, null, null);\n");

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Spanned spanned = Html.fromHtml("<font color=\"#00ffff\">我是蓝色字体</font> ");
                Snackbar.make(v, spanned, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Change_Color", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Snackbar snackbar = Snackbar.make(v, "Hello Snackbar", Snackbar.LENGTH_INDEFINITE);
                                //设置背景颜色
                                View view = snackbar.getView();
                                view.setBackgroundColor(Color.parseColor("#ffff00"));
                                //设置Message图片，Gravity及颜色
                                TextView textView = (TextView) view.findViewById(R.id.snackbar_text);
                                textView.setGravity(Gravity.CENTER_VERTICAL);
                                textView.setTextColor(Color.parseColor("#ff0000"));
                                Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                                drawable.setBounds(0, 0, drawable.getIntrinsicWidth()/2, drawable.getIntrinsicHeight()/2);
                                textView.setCompoundDrawables(drawable, null, null, null);

                                snackbar.show();
                            }
                        })
                        .setActionTextColor(ContextCompat.getColor(SnackbarActivity.this,R.color.colorAccent))
                        .show();
            }
        });


    }
}
