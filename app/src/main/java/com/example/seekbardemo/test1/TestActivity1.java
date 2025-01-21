package com.example.seekbardemo.test1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seekbardemo.R;
import com.example.seekbardemo.vertical_seekbar2.VerticalColorSeekBar;

/**
 * https://blog.csdn.net/Agg_bin/article/details/131676712
 * https://blog.csdn.net/bobxie520/article/details/114477944
 * https://zhuanlan.zhihu.com/p/622534050
 */
public class TestActivity1 extends AppCompatActivity{

    private VerticalColorSeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
//        seekBar = findViewById(R.id.seek_gesture_duration);
    }

    /**
     * 初始化 Seekbar
     * @param v
     */
    public void onTest1(View v) {
//        seekBar.setMax(9000);
//        seekBar.setProgress(3600);
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
////                floatView.tvGestureDuration.setText(String.valueOf(i));
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

    }

}