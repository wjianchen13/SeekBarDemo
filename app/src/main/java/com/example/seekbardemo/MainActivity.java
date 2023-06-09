package com.example.seekbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seekbardemo.change.ChangeActivity;
import com.example.seekbardemo.change.ChangeActivity2;
import com.example.seekbardemo.follow.FollowActivity;

public class MainActivity extends AppCompatActivity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 进度跟随Seekbar进度条移动
     * @param v
     */
    public void onTest1(View v) {
        startActivity(new Intent(this, FollowActivity.class));
    }

    /**
     * Seekbar 拖动进度条变大，使用svg方式，不确定有没有兼容问题
     * @param v
     */
    public void onTest2(View v) {
        startActivity(new Intent(this, ChangeActivity.class));
    }

    /**
     * Seekbar 拖动进度条变大
     * @param v
     */
    public void onTest3(View v) {
        startActivity(new Intent(this, ChangeActivity2.class));
    }


}



