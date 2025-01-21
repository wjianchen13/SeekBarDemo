package com.example.seekbardemo.vertical_seekbar6

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.seekbardemo.R

/**
 * 自定义垂直Seekbar
 */
class VerticalSeekBar : View {
    /**
     * 滑块x y 坐标
     */
    private var mThumbYsxyyx = 0f

    /**
     * 当前进度
     */
    private var mProgress2xyyx = 0f

    /**
     * 绘制背景的范围
     */
    private var sLeft2xyyx = 0f
    private var sRight2xyyx = 0f
    private var sTop2xyyx = 0f
    private var sBottom2xyyx = 0f
    protected var onSlideChangeListener2xyyx: OnSlideChangeListener2? = null

    /**
     * 进度条宽度
     */
    private var mBackgroundWidth2xyyx = 0

    /**
     * 滑块
     */
    private var bitmap2xyyx: Bitmap? = null
    private var middleColor2xyyx = Color.GRAY
    private var thumbColor2xyyx = Color.BLACK
    private var startColor2xyyx = Color.GRAY
    private var endColor2xyyx = Color.GRAY

    /**
     * 进度颜色
     */
    private var colorsArray2xyyx = intArrayOf(Color.GRAY, Color.GRAY, Color.GRAY)
    private val colorArraysxyyx = intArrayOf(startColor2xyyx, middleColor2xyyx, endColor2xyyx)
    private var linearGradient2xyyx: LinearGradient? = null
    private val paint3xyyx = Paint()
    private var sWidth2xyyx = 0f
    private var sHeight2xyyx = 0f
    private var circleRadius2xyyx = DEFAULT_CIRCLE_RADIUS2
    private var dragable23xyyx = true
    private var image_backgroundsxyyx = 0

    /**
     * Seekbar背景
     */
    private var mBackgroundColor2xyyx = Color.GRAY

    /**
     * Seekbar进度背景
     */
    private var mProgressBackgroundColor2xyyx = Color.RED
    private var thumbPaint2xyyx: Paint? = null

    /**
     * 滑块高度
     */
    private var mThumbHeight2xyyx = 0f

    /**
     * 滑块宽度
     */
    private var mThumbWidth2xyyx = 0f

    private var mBackgroundDrawable: Drawable? = null

    private var mPath: Path? = null

    constructor(context: Context?) : this(context, null) {

    }

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {

    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle){
        initAttrsssxyyx(context!!, attrs, defStyle)
        initData2xyyx()
        setCircleColorsXGxyyx(thumbColor2xyyx)
        setmBackgroundColor32xyyx(mBackgroundColor2xyyx)
    }

    /**
     * @param mBackgroundColor 滑竿的颜色
     */
    fun setmBackgroundColor32xyyx(mBackgroundColor: Int) {
        mBackgroundColor2xyyx = mBackgroundColor
        startColor2xyyx = mBackgroundColor
        middleColor2xyyx = mBackgroundColor
        endColor2xyyx = mBackgroundColor
        colorArraysxyyx[0] = startColor2xyyx
        colorArraysxyyx[1] = middleColor2xyyx
        colorArraysxyyx[2] = endColor2xyyx
        invalidate()
    }

    /**
     * @param circle_color 滑块的颜色，在没有设置背景图情况下生效
     */
    fun setCircleColorsXGxyyx(circle_color: Int) {
        thumbColor2xyyx = circle_color
        invalidate()
    }

    private fun initData2xyyx() {
        setProgressColor2xyyx3(mProgressBackgroundColor2xyyx)
    }

    private fun initAttrsssxyyx(context: Context, attrs: AttributeSet?, defStyle: Int) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.Fgki8ylkCustomVerticalSeekBar, defStyle, 0)
        circleRadius2xyyx = a.getDimensionPixelSize(R.styleable.Fgki8ylkCustomVerticalSeekBar_ddkkyuuy_vertical_circle_radius, DEFAULT_CIRCLE_RADIUS2)
        thumbColor2xyyx = a.getColor(R.styleable.Fgki8ylkCustomVerticalSeekBar_ddkkyuuy_vertical_circle_color, DEFAULT_CIRCLE_COLOR2)
        dragable23xyyx = a.getBoolean(R.styleable.Fgki8ylkCustomVerticalSeekBar_ddkkyuuy_vertical_dragable, true)
        mBackgroundColor2xyyx = a.getColor(R.styleable.Fgki8ylkCustomVerticalSeekBar_ddkkyuuy_vertical_background_color, Color.GRAY)
        mProgressBackgroundColor2xyyx = a.getColor(R.styleable.Fgki8ylkCustomVerticalSeekBar_ddkkyuuy_vertical_progress_background_color, Color.YELLOW)
        image_backgroundsxyyx = a.getResourceId(R.styleable.Fgki8ylkCustomVerticalSeekBar_ddkkyuuy_vertical_image_background, 0)
        mBackgroundWidth2xyyx = a.getDimensionPixelSize(R.styleable.Fgki8ylkCustomVerticalSeekBar_ddkkyuuy_vertical_background_width, 36)
        mBackgroundDrawable = a.getDrawable(R.styleable.Fgki8ylkCustomVerticalSeekBar_ddkkyuuy_vertical_background)
        val startColor = a.getColor(R.styleable.Fgki8ylkCustomVerticalSeekBar_ddkkyuuy_vertical_start_color, DEFAULT_CIRCLE_COLOR2)
        val endColor = a.getColor(R.styleable.Fgki8ylkCustomVerticalSeekBar_ddkkyuuy_vertical_end_color, DEFAULT_CIRCLE_COLOR2)
        colorsArray2xyyx = intArrayOf(endColor, startColor)
        a.recycle()
    }

    /**
     * 初始化参数
     */
    private fun initParams23xyyx() {
        if (image_backgroundsxyyx != 0) {
            val options = BitmapFactory.Options()
            val b = BitmapFactory.decodeResource(resources, image_backgroundsxyyx, options)
            bitmap2xyyx = Bitmap.createScaledBitmap(b, measuredWidth, measuredWidth, true)
            mThumbWidth2xyyx = bitmap2xyyx!!.width.toFloat()
            mThumbHeight2xyyx = bitmap2xyyx!!.height.toFloat()
        }
        sLeft2xyyx = (measuredWidth - mBackgroundWidth2xyyx).toFloat() / 2
        sRight2xyyx = sLeft2xyyx + mBackgroundWidth2xyyx
        sTop2xyyx = mThumbWidth2xyyx / 2
        sBottom2xyyx = measuredHeight.toFloat()
    }

    /**
     * 设置进度颜色
     * @param color 进度颜色
     */
    fun setProgressColor2xyyx3(color: Int) {
        invalidate()
    }

    @Synchronized
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredHeight)
        initParams23xyyx()
    }

    /**
     * 绘制进度背景
     * @param canvas
     */
    private fun drawProgressBackground21xyyx3(canvas: Canvas) {
        if(mPath == null)
            mPath = Path()
        if(mPath != null) {
            mPath!!.reset()
            val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
            val radii = floatArrayOf(
                circleRadius2xyyx.toFloat(),
                circleRadius2xyyx.toFloat(),
                circleRadius2xyyx.toFloat(),
                circleRadius2xyyx.toFloat(),
                circleRadius2xyyx.toFloat(),
                circleRadius2xyyx.toFloat(),
                circleRadius2xyyx.toFloat(),
                circleRadius2xyyx.toFloat()
            )
            mPath!!.addRoundRect(rect, radii, Path.Direction.CW)
            canvas.clipPath(mPath!!)
        }

        val top = ((100 - mProgress2xyyx) / 100) * sHeight2xyyx
        val rectBlackBg = RectF(0f, top, width.toFloat(), height.toFloat())
        linearGradient2xyyx = LinearGradient(0f, top, 0f, height.toFloat(), colorsArray2xyyx, null, Shader.TileMode.MIRROR)
        paint3xyyx.isAntiAlias = true
        paint3xyyx.style = Paint.Style.FILL
        paint3xyyx.shader = linearGradient2xyyx
        canvas.drawRect(rectBlackBg,  paint3xyyx)
    }

    /**
     * 绘制背景
     * @param canvas
     */
    private fun drawBackground3xddxyyx(canvas: Canvas) {
        val drawable = mBackgroundDrawable
        drawable?.let {
            drawable.setBounds(0, 0, width - 0, height)
            drawable.draw(canvas)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val h = measuredHeight
        val w = measuredWidth
        sWidth2xyyx = sRight2xyyx - sLeft2xyyx
        sHeight2xyyx = sBottom2xyyx
        mThumbYsxyyx = (1 - 0.01 * mProgress2xyyx).toFloat() * sHeight2xyyx
        drawBackground3xddxyyx(canvas)
        drawProgressBackground21xyyx3(canvas)
        paint3xyyx.reset()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!dragable23xyyx) {
            return true
        }
        var eventY = event.y
        if (eventY < mThumbHeight2xyyx / 2) {
            eventY = mThumbHeight2xyyx / 2
        } else if (eventY > mThumbHeight2xyyx / 2 + sHeight2xyyx) {
            eventY = mThumbHeight2xyyx / 2 + sHeight2xyyx
        }
        mProgress2xyyx = (sHeight2xyyx - (eventY - mThumbHeight2xyyx / 2)) / sHeight2xyyx * 100
        log2xdyyx("onTouchEvent eventY: $eventY  progress: $mProgress2xyyx")
        when (event.action) {
            MotionEvent.ACTION_DOWN -> onSlideProgress23xyyx(MotionEvent.ACTION_DOWN, mProgress2xyyx)
            MotionEvent.ACTION_UP -> onSlideProgress23xyyx(MotionEvent.ACTION_UP, mProgress2xyyx)
            MotionEvent.ACTION_MOVE -> onSlideProgress23xyyx(MotionEvent.ACTION_MOVE, mProgress2xyyx)
        }
        return true
    }

    fun setOnSlideChangeListener2(onStateChangeListener: OnSlideChangeListener2?) {
        onSlideChangeListener2xyyx = onStateChangeListener
    }

    private fun log2xdyyx(str: String) {
        println("==========================> $str")
    }

    fun onSlideProgress23xyyx(event: Int, progress: Float) {
        var progress = progress
        if (progress < 0) {
            progress = 0f
        }
        if (progress > 100) {
            progress = 100f
        }
        when (event) {
            MotionEvent.ACTION_UP -> if (onSlideChangeListener2xyyx != null) {
                onSlideChangeListener2xyyx!!.onSlideStopTouch22xyyx(this, progress)
            }

            MotionEvent.ACTION_MOVE -> {
                if (onSlideChangeListener2xyyx != null) {
                    onSlideChangeListener2xyyx!!.OnSlideChangeListener23xyyx(this, progress)
                }
                setProgress22xyyx(progress)
                this.invalidate()
            }

            MotionEvent.ACTION_DOWN -> this.invalidate()
        }
    }

    fun setProgress22xyyx(mProgress: Float) {
        mProgress2xyyx = mProgress
        invalidate()
    }

    interface OnSlideChangeListener2 {
        fun OnSlideChangeListener23xyyx(view: View?, progress: Float)
        fun onSlideStopTouch22xyyx(view: View?, progress: Float)
    }

    companion object {
        private const val DEFAULT_CIRCLE_COLOR2 = Color.GRAY
        private const val DEFAULT_CIRCLE_RADIUS2 = 0
    }
}