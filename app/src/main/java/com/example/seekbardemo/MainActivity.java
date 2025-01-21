package com.example.seekbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seekbardemo.change.ChangeActivity;
import com.example.seekbardemo.change.ChangeActivity2;
import com.example.seekbardemo.follow.FollowActivity;
import com.example.seekbardemo.test1.TestActivity1;
import com.example.seekbardemo.vertical_seekbar.VerticalSeekbarActivity;
import com.example.seekbardemo.vertical_seekbar2.VerticalSeekbarActivity2;
import com.example.seekbardemo.vertical_seekbar3.VerticalSeekbarActivity3;
import com.example.seekbardemo.vertical_seekbar4.VerticalSeekbarActivity4;
import com.example.seekbardemo.vertical_seekbar5.VerticalSeekbarActivity5;
import com.example.seekbardemo.vertical_seekbar6.VerticalSeekbarActivity6;

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

    /**
     * 自定义垂直 Seekbar
     * @param v
     */
    public void onTest4(View v) {
        startActivity(new Intent(this, VerticalSeekbarActivity.class));
    }

    /**
     * 自定义垂直 Seekbar
     * @param v
     */
    public void onTest5(View v) {
        startActivity(new Intent(this, VerticalSeekbarActivity2.class));
    }

    /**
     * 自定义垂直 Seekbar
     * @param v
     */
    public void onTest6(View v) {
        startActivity(new Intent(this, VerticalSeekbarActivity3.class));
    }

    /**
     * 自定义垂直 Seekbar
     * @param v
     */
    public void onTest7(View v) {
        startActivity(new Intent(this, VerticalSeekbarActivity4.class));
    }

    /**
     * 自定义垂直 Seekbar
     * @param v
     */
    public void onTest8(View v) {
        startActivity(new Intent(this, VerticalSeekbarActivity5.class));
    }

    /**
     * 自定义垂直 Seekbar
     * @param v
     */
    public void onTest9(View v) {
        startActivity(new Intent(this, VerticalSeekbarActivity6.class));
    }

    /**
     * 水平Seekbar
     * @param v
     */
    public void onTest10(View v) {
        startActivity(new Intent(this, TestActivity1.class));
    }



}



