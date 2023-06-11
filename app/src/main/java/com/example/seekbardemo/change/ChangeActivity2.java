package com.example.seekbardemo.change;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.seekbardemo.R;

public class ChangeActivity2 extends AppCompatActivity{

    private SeekBar sb_seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change2);
        sb_seekbar = (SeekBar) findViewById(R.id.mainactivity_sb_seekbar);
        sb_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Drawable tumb_normal = ContextCompat.getDrawable(ChangeActivity2.this, R.drawable.seekbar_thum_pressed);
                sb_seekbar.setThumb(tumb_normal);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Drawable tumb_normal = ContextCompat.getDrawable(ChangeActivity2.this, R.drawable.seekbar_thum_normal);
                sb_seekbar.setThumb(tumb_normal);
            }
        });
    }
}