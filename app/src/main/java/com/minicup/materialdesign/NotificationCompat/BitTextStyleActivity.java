package com.minicup.materialdesign.NotificationCompat;

import android.annotation.TargetApi;
import android.app.Notification;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.minicup.materialdesign.R;

public class BitTextStyleActivity extends AppCompatActivity {
    private RelativeLayout mActivityBitTextStyle;
    private TextInputLayout mTil1;
    private EditText mTitle;
    private TextInputLayout mTil2;
    private EditText mContent;
    private TextInputLayout mTil3;
    private EditText mBigText;
    private TextInputLayout mTil4;
    private EditText mBigcontenttitle;
    private EditText mSummaryText;
    private Button mSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bit_text_style);

        mActivityBitTextStyle = (RelativeLayout) findViewById(R.id.activity_bit_text_style);
        mTil1 = (TextInputLayout) findViewById(R.id.til1);
        mTitle = (EditText) findViewById(R.id.title);
        mTil2 = (TextInputLayout) findViewById(R.id.til2);
        mContent = (EditText) findViewById(R.id.content);
        mTil3 = (TextInputLayout) findViewById(R.id.til3);
        mBigText = (EditText) findViewById(R.id.bigText);
        mTil4 = (TextInputLayout) findViewById(R.id.til4);
        mBigcontenttitle = (EditText) findViewById(R.id.bigcontenttitle);
        mSummaryText = (EditText) findViewById(R.id.summaryText);
        mSend = (Button) findViewById(R.id.send);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void sendNotification() {


        String contentTitleStr = mTitle.getText().toString();
        String contentTextStr = mContent.getText().toString();
        String bigTextStr = mBigText.getText().toString();
        String bigContentTitleStr = mBigcontenttitle.getText().toString();
        String summaryTextStr = mSummaryText.getText().toString();


        ///////////////////////////////////////////////////
        //貌似没有起效果
//        NotificationCompat.Builder compatBuilder = new NotificationCompat.Builder(this);
//        compatBuilder.setContentTitle(contentTitleStr)
//                .setContentText(contentTextStr)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.notification))
//                .setSmallIcon(R.mipmap.notification);
//
//        Notification notification = compatBuilder.build();
//
//        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle(compatBuilder);
//        style.bigText(bigTextStr)
//                .setBigContentTitle(bigContentTitleStr)
//                .setSummaryText(summaryTextStr);


        /////////////////////////////////////////////////

        Notification notif = new Notification.Builder(this)
                .setContentTitle(contentTitleStr)
                .setContentText(contentTextStr)
                .setTicker("tiker")

                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.notification))
                .setSmallIcon(R.drawable.icon_1)
                .setStyle(
                        new Notification.BigTextStyle()
                        .bigText(bigTextStr)
                        .setBigContentTitle(bigContentTitleStr)
                        .setSummaryText(summaryTextStr)
                )
                .build();


        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1, notif);

    }
}
