package com.example.seekbardemo.vertical_seekbar6;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.seekbardemo.R;

/**
 * name: RectView
 * desc: canvas绘制矩形
 * author:
 * date: 2018-06-26 16:00
 * remark:
 */
public class RoundRectView extends View {


    // 1.创建一个画笔
    private Paint mPaint = new Paint();
    private Drawable drawable;
    private Bitmap mBitmap;

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.RED);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    public RoundRectView(Context context) {
        super(context);
    }

    // 3.在构造函数中初始化
    public RoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        drawable = ContextCompat.getDrawable(context, R.drawable.bg_vertical_seekbar);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_pay_title_diamond);
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if(drawable != null) {
//            drawable.setBounds(0, 0, 400, 200);
//            drawable.draw(canvas);
//        }
//        // 第一种
//        RectF rectF = new RectF(100,100,800,400);
//        canvas.drawRoundRect(rectF,30,30,mPaint);

        // 第二种
//        canvas.drawRoundRect(100,100,800,400,30,30,mPaint);

        // 第一种
//        RectF rectF = new RectF(100,100,900,500);
//        canvas.drawRoundRect(rectF,200,200, mPaint);



//        // 创建一个原始的 Bitmap
//        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.your_image);
//
//        // 定义圆角的半径
//        float cornerRadius = 50.0f;
//
//// 创建一个空的 Bitmap，用于绘制带有左上角圆角的图像
//        Bitmap roundedBitmap = Bitmap.createBitmap(originalBitmap.getWidth(), originalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
//
//// 创建一个 Canvas 对象，并将空的 Bitmap 设置给它
////        Canvas canvas = new Canvas(roundedBitmap);
//
//// 创建一个 Paint 对象
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//
//// 创建一个圆角矩形的 Path，只设置左上角为圆角
//        Path path = new Path();
//        RectF rect = new RectF(0, 0, originalBitmap.getWidth(), originalBitmap.getHeight());
//        float[] radii = {cornerRadius, cornerRadius, 0, 0, 0, 0, 0, 0}; // 左上角为圆角，其余角为直角
//        path.addRoundRect(rect, radii, Path.Direction.CW);
//
//// 设置 Path 为 Canvas 的剪裁区域
//        canvas.clipPath(path);
//
//// 绘制 Bitmap 到 Canvas
//        canvas.drawBitmap(originalBitmap, 0, 0, paint);
    }
}
