package com.example.seekbardemo.follow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.example.seekbardemo.R;

/**
 * Created by Administrator on 2016-11-03.
 */
public class FollowSeekBar extends RelativeLayout implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{
    
    private Context mContext;
    
    private TextView tvProgress;
    private ImageView imgvReduce;
    private AppCompatSeekBar acSbar;
    private ImageView imgvAdd;

    /**
     * 减少图标的宽度，字体移动的时候需要在这个值的基准上移动
     */
    private int imageWidth;

    /**
     * 进度条距离左侧按钮距离
     */
    private int mBarMarginLeft;

    /**
     * 进度条距离右侧按钮距离
     */
    private int mBarMarginRight;

    /**
     * 字体大小
     */
    private float mTextSize;

    /**
     * 字体颜色
     */
    private int mTextColor;

    /**
     * SeekBar的背景图片
     */
    private Drawable mProgressDrawable;

    /**
     * 表示SeekBar的滑块图片
     */
    private Drawable mThumb;

    public FollowSeekBar(Context context) {
        super(context);
        initView(context);
    }

    public FollowSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initView(context);
    }

    public FollowSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initView(context);
    }

    /**
     * init attrs
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.follow_seekbar);
        mBarMarginLeft = a.getDimensionPixelSize(R.styleable.follow_seekbar_fs_margin_left, 0);
        mBarMarginRight = a.getDimensionPixelSize(R.styleable.follow_seekbar_fs_margin_right, 0);
        mTextSize = a.getDimensionPixelSize(R.styleable.follow_seekbar_fs_text_size, 15);
        mTextColor = a.getColor(R.styleable.follow_seekbar_fs_text_color, Color.parseColor("#000000"));
        mProgressDrawable = a.getDrawable(R.styleable.follow_seekbar_fs_progressDrawable);
        mThumb = a.getDrawable(R.styleable.follow_seekbar_fs_thumb);
        a.recycle();
    }

    /**
     * initView
     */
    private void initView(Context context){
        this.mContext = context;
        View view = inflate(getContext(), R.layout.view_follow_seekbar, this);
        tvProgress = view.findViewById(R.id.tv_progress);
        imgvReduce = view.findViewById(R.id.imgv_reduce);
        acSbar = view.findViewById(R.id.ac_sbar);
        imgvAdd = view.findViewById(R.id.imgv_add);

        acSbar.setOnSeekBarChangeListener(this);
        imgvReduce.setOnClickListener(this);
        imgvAdd.setOnClickListener(this);

        tvProgress.setTextColor(mTextColor);
        if(mTextSize != 0)
            tvProgress.setTextSize(mTextSize);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) acSbar.getLayoutParams();
        if(params != null) {
            params.setMargins(mBarMarginLeft, params.topMargin, mBarMarginRight, params.bottomMargin);
            acSbar.setLayoutParams(params);
        }
        
        if(mProgressDrawable != null)
            acSbar.setProgressDrawable(mProgressDrawable);

        if(mThumb != null)
            acSbar.setThumb(mThumb);

        tvProgress.post(new Runnable() {
            @Override
            public void run() {
                initProgressPos(acSbar.getProgress());
            }
        });

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        initProgressPos(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View view) {
        if(view == null)
            return ;
        int id = view.getId();
        if(id == R.id.imgv_reduce) {
            int progress = acSbar.getProgress();
            if(progress > 0) 
                acSbar.setProgress(acSbar.getProgress() - 1);
        } else if(id == R.id.imgv_add) {
            int progress = acSbar.getProgress();
            if(progress < 100)
                acSbar.setProgress(acSbar.getProgress() + 1);
        }
    }
    
    private void initProgressPos(int progress) {
        if(imageWidth == 0)
            imageWidth = imgvReduce.getWidth();
        tvProgress.setText("" + progress);
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tvProgress.measure(spec, spec);
        int quotaWidth = tvProgress.getMeasuredWidth();
        int sbWidth = acSbar.getMeasuredWidth() - acSbar.getPaddingLeft() - acSbar.getPaddingRight(); // 进度条真实长度
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tvProgress.getLayoutParams();
        params.leftMargin = acSbar.getPaddingLeft() // SeekBar paddingLeft，用来完整显示滑块
                + imageWidth // 减少区域占的额宽度
                + (int) (((double) progress / acSbar.getMax()) * sbWidth) //  滑块滑动时距离进度左侧的距离 
                - quotaWidth / 2; // 文字宽度一半，用来实现文字和滑块中间对齐
        tvProgress.setLayoutParams(params);
    }
}
