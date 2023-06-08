package com.example.seekbardemo.change;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seekbardemo.R;

public class ChangeActivity extends AppCompatActivity{

    private SeekBar sb_seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        sb_seekbar = (SeekBar) findViewById(R.id.mainactivity_sb_seekbar);
    }
}