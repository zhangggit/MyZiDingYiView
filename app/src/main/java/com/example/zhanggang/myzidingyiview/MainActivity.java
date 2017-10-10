package com.example.zhanggang.myzidingyiview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyView myview = (MyView) findViewById(R.id.myview);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aaa);
        myview.setBitmap(bitmap);

    }
}
