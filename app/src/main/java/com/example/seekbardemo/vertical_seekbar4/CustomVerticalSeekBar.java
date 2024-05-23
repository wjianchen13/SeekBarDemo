package com.example.seekbardemo.vertical_seekbar4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.seekbardemo.R;

/**
 * 自定义垂直Seekbar
 */
public class CustomVerticalSeekBar extends View {

    private static final String TAG = CustomVerticalSeekBar.class.getSimpleName();
    private int startColor = Color.GRAY;
    private int middleColor = Color.GRAY;
    private int endColor = Color.GRAY;
    private int thumbColor = Color.BLACK;
    private int thumbBorderColor = Color.TRANSPARENT;
    private int colorArray[] = {startColor, middleColor, endColor};

    /**
     * 进度颜色
     */
    private int colorArray2[] = {Color.GRAY, Color.GRAY, Color.GRAY};

    /**
     * 滑块x y 坐标
     */
    private float mThumbX, mThumbY;

    /**
     * 背景角度
     */
    private float mRadius;

    /**
     * 当前进度
     */
    private float mProgress;
    private float maxCount = 100f;

    /**
     * 绘制背景的范围
     */
    private float sLeft, sTop, sRight, sBottom;

    /**
     * 进度条背景宽度和高度
     */
    private float sWidth, sHeight;
    private LinearGradient linearGradient;
    private Paint paint = new Paint();
    protected OnSlideChangeListener onSlideChangeListener;

    private static final int DEFAULT_CIRCLE_RADIUS = 0;
    private static final int DEFAULT_CIRCLE_COLOR = Color.GRAY;

    private int circle_radius = DEFAULT_CIRCLE_RADIUS;

    /**
     * Seekbar背景
     */
    private int mBackgroundColor = Color.GRAY;

    /**
     * Seekbar进度背景
     */
    private int mProgressBackgroundColor = Color.RED;

    private int image_background = 0;
    private boolean dragable = true;

    /**
     * 滑块
     */
    private Bitmap bitmap;

    /**
     * 进度条宽度
     */
    private int mBackgroundWidth;

    /**
     * 滑块宽度
     */
    private float mThumbWidth;

    /**
     * 滑块高度
     */
    private float mThumbHeight;

    private Paint thumbPaint;

    public CustomVerticalSeekBar(Context context) {
        this(context, null);
    }

    public CustomVerticalSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs, defStyle);
        initData();
        setCircle_color(thumbColor);
        setmBackgroundColor(mBackgroundColor);
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs,  R.styleable.CustomVerticalSeekBar, defStyle, 0);
        circle_radius = a.getDimensionPixelSize(R.styleable.CustomVerticalSeekBar_vertical_circle_radius, DEFAULT_CIRCLE_RADIUS);
        thumbColor = a.getColor(R.styleable.CustomVerticalSeekBar_vertical_circle_color, DEFAULT_CIRCLE_COLOR);
        dragable = a.getBoolean(R.styleable.CustomVerticalSeekBar_vertical_dragable, true);
        mBackgroundColor = a.getColor(R.styleable.CustomVerticalSeekBar_vertical_background_color, Color.GRAY);
        mProgressBackgroundColor = a.getColor(R.styleable.CustomVerticalSeekBar_vertical_progress_background_color, Color.YELLOW);
        image_background = a.getResourceId(R.styleable.CustomVerticalSeekBar_vertical_image_background, 0);
        mBackgroundWidth = a.getDimensionPixelSize(R.styleable.CustomVerticalSeekBar_vertical_background_width, 36);
        a.recycle();
    }

    private void initData() {
        setProgressColor(mProgressBackgroundColor);
    }

    /**
     * @param circle_color 滑块的颜色，在没有设置背景图情况下生效
     */
    public void setCircle_color(int circle_color) {
        this.thumbColor = circle_color;
        invalidate();
    }

    /**
     * @param mBackgroundColor 滑竿的颜色
     */
    public void setmBackgroundColor(int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
        this.startColor = mBackgroundColor;
        this.middleColor = mBackgroundColor;
        this.endColor = mBackgroundColor;
        colorArray[0] = startColor;
        colorArray[1] = middleColor;
        colorArray[2] = endColor;
        invalidate();
    }

    /**
     * 设置进度颜色
     * @param color 进度颜色
     */
    public void setProgressColor(int color) {
        colorArray2[0] = color;
        colorArray2[1] = color;
        colorArray2[2] = color;
        invalidate();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
        initParams();
    }

    /**
     * 初始化参数
     */
    private void initParams() {
        if(image_background != 0) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap b = BitmapFactory.decodeResource(getResources(), image_background, options);
            bitmap = Bitmap.createScaledBitmap(b, getMeasuredWidth(), getMeasuredWidth(), true);
            mThumbWidth = bitmap.getWidth();
            mThumbHeight = bitmap.getHeight();
        }
        mRadius = (float) mBackgroundWidth / 2;
        sLeft = (float)(getMeasuredWidth() - mBackgroundWidth) / 2;
        sRight = sLeft + mBackgroundWidth;
        sTop = mThumbWidth / 2;
        sBottom = getMeasuredHeight() - mThumbWidth / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int h = getMeasuredHeight();
        int w = getMeasuredWidth();
        sWidth = sRight - sLeft;
        sHeight = sBottom - sTop;
        mThumbX = (float) w / 2;
        mThumbY = (float) (1 - 0.01 * mProgress) * sHeight;
        drawBackground(canvas);
        drawProgressBackground(canvas);
        drawCircle(canvas);
        paint.reset();
    }

    /**
     * 绘制背景
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        RectF rectBlackBg = new RectF(sLeft, sTop, sRight, sBottom);
        linearGradient = new LinearGradient(sLeft, sTop, sWidth, mThumbY, colorArray, null, Shader.TileMode.MIRROR);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(linearGradient);
        canvas.drawRoundRect(rectBlackBg, sWidth / 2, sWidth / 2, paint);
    }

    /**
     * 绘制进度背景
     * @param canvas
     */
    private void drawProgressBackground(Canvas canvas) {
        float top = (float) ((100 - mProgress) / 100) * sHeight + mThumbHeight / 2;
        RectF rectBlackBg = new RectF(sLeft, top, sRight, sBottom);
        linearGradient = new LinearGradient(sLeft, sTop, sWidth, mThumbY, colorArray2, null, Shader.TileMode.MIRROR);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(linearGradient);
        canvas.drawRoundRect(rectBlackBg, sWidth / 2, sWidth / 2, paint);
    }

    private void drawCircle(Canvas canvas) {
        if(thumbPaint == null) {
            thumbPaint = new Paint();
        }
        thumbPaint.setAntiAlias(true);
        thumbPaint.setStyle(Paint.Style.FILL);
        thumbPaint.setColor(thumbColor);
        float top = (100 - mProgress) / 100 * sHeight + mThumbHeight / 2  - mThumbHeight / 2;
        log("drawCircle top: " + top);
        Rect rect = new Rect(0, (int)top, getMeasuredWidth(), (int)(top + mThumbHeight));
        canvas.drawBitmap(bitmap, null, rect, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!dragable) {
            return true;
        }
        float eventY = event.getY();
        if(eventY < mThumbHeight / 2) {
            eventY = mThumbHeight / 2;
        } else if(eventY > mThumbHeight / 2 + sHeight) {
            eventY = mThumbHeight / 2 + sHeight;
        }
        mProgress = (sHeight - (eventY - mThumbHeight / 2)) / sHeight * 100;
        log("onTouchEvent eventY: " + eventY + "  progress: " + mProgress);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onSlideProgress(MotionEvent.ACTION_DOWN, mProgress);
                break;
            case MotionEvent.ACTION_UP:
                onSlideProgress(MotionEvent.ACTION_UP, mProgress);
                break;
            case MotionEvent.ACTION_MOVE:
                onSlideProgress(MotionEvent.ACTION_MOVE, mProgress);
                break;
        }
        return true;
    }

    public interface OnSlideChangeListener {
        void OnSlideChangeListener(View view, float progress);

        void onSlideStopTouch(View view, float progress);
    }

    public void setOnSlideChangeListener(OnSlideChangeListener onStateChangeListener) {
        this.onSlideChangeListener = onStateChangeListener;
    }

    public void setProgress(float mProgress) {
        this.mProgress = mProgress;
        invalidate();
    }

    public void onSlideProgress(int event, float progress) {
        if (progress < 0) {
            progress = 0;
        }
        if (progress > 100) {
            progress = 100;
        }
        switch (event) {
            case MotionEvent.ACTION_UP:
                if (onSlideChangeListener != null) {
                    onSlideChangeListener.onSlideStopTouch(this, progress);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (onSlideChangeListener != null) {
                    onSlideChangeListener.OnSlideChangeListener(this, progress);
                }
                setProgress(progress);
                this.invalidate();
                break;
            case MotionEvent.ACTION_DOWN:
                this.invalidate();
                break;
        }

    }

    private void log(String str) {
        System.out.println("==========================> " + str);
    }
}