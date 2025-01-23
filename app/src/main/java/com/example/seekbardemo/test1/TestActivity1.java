package com.example.seekbardemo.test1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.seekbardemo.R;
import com.example.seekbardemo.Utils;

/**
 * https://blog.csdn.net/Agg_bin/article/details/131676712
 * https://blog.csdn.net/bobxie520/article/details/114477944
 * https://zhuanlan.zhihu.com/p/622534050
 */
public class TestActivity1 extends AppCompatActivity{

    private SeekBar seekBar;
    private int mProgress;
    private Handler mHandler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(seekBar != null) {
                mProgress += 5;
                seekBar.setProgress(mProgress);
            }
            sendEmptyMessageDelayed(1, 2000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        seekBar = findViewById(R.id.sb_normal);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Utils.log("onProgressChanged progress: " + progress + "  fromUser: " + fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Utils.log("onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Utils.log("onStopTrackingTouch");
            }
        });
    }

    /**
     * Seekbar 设置进度
     * @param v
     */
    public void onTest1(View v) {
        seekBar.setProgress(80);

    }


    /**
     * Seekbar 设置进度
     * @param v
     */
    public void onTest2(View v) {
        mHandler.sendEmptyMessageDelayed(1, 2000);

    }

}