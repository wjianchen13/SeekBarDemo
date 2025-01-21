package com.example.seekbardemo.vertical_seekbar5;

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.seekbardemo.R

/**
 * Description:
 * CreateDate:     2023/7/11 14:58
 * Author:         agg
 */
class SeekBarVertical : ConstraintLayout {

        lateinit var parentView: ConstraintLayout
        lateinit var seekBar: SeekBar
        lateinit var icon: View

private var selfWidth: Int = -1
private var selfHeight: Int = -1
private var seekBarMinMaxValue: Int = -1
private var selfProgress: Int = -1
private var selfMax: Int = -1
private var iconDrawable: Drawable? = null

        constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
        constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0) : super(
        context, attrs, defStyle
        ) {
        attrs?.let { initAttrs(context, attrs) }
        initView()
        }

        /**
         * 代码中动态添加时使用。如：
         *
         * binding.root.addView(SeekBarVertical(this,192,432))
         */
        constructor(context: Context, width: Int, height: Int) : super(context) {
        selfWidth = width
        selfHeight = height
        seekBarMinMaxValue = if (selfHeight < selfWidth) selfHeight else selfWidth
        initView()
        }

@SuppressLint("ResourceType")
private fun initAttrs(context: Context, attrs: AttributeSet) {
        // 获取Android原生宽、高属性
        context.obtainStyledAttributes(
        attrs, intArrayOf(android.R.attr.layout_width, android.R.attr.layout_height)
        ).apply {
        selfWidth = getDimensionPixelSize(0, -1)
        selfHeight = getDimensionPixelSize(1, -1)
        seekBarMinMaxValue = if (selfHeight < selfWidth) selfHeight else selfWidth
        recycle()
        }

        // 获取SeekBarVertical自定义属性
        context.obtainStyledAttributes(
        attrs, R.styleable.SeekBarVertical
        ).apply {
        selfProgress = getInt(R.styleable.SeekBarVertical_UISeekBarProgress, -1)
        selfMax = getInt(R.styleable.SeekBarVertical_UISeekBarMax, -1)
        iconDrawable = getDrawable(R.styleable.SeekBarVertical_UISeekBarIcon)
        recycle()
        }
        }

@SuppressLint("NewApi")
private fun initView() {
        // 未设置宽高则直接返回
        if (selfWidth < 0 || selfHeight < 0) return

        parentView = View.inflate(context, R.layout.ui_seekbar_vertical, this) as ConstraintLayout

        // 设置控件整体宽、高
        (parentView.findViewById<ConstraintLayout>(R.id.parent).layoutParams as LayoutParams).apply {
        width = selfWidth
        height = selfHeight
        }

        // 设置SeekBar宽、高，以及progress和max值
        seekBar = parentView.findViewById<SeekBar>(R.id.seekbar).apply {
        (layoutParams as FrameLayout.LayoutParams).apply {
        width = selfHeight
        height = selfWidth
        }
        minHeight = seekBarMinMaxValue
        maxHeight = seekBarMinMaxValue
        minWidth = seekBarMinMaxValue
        maxWidth = seekBarMinMaxValue

        if (selfProgress >= 0) progress = selfProgress
        if (selfMax >= 0) max = selfMax
        }

        // 设置icon距离底部位置，以及icon背景
        icon = parentView.findViewById<View>(R.id.icon).apply {
        (layoutParams as LayoutParams).bottomMargin = seekBarMinMaxValue / 4
        iconDrawable?.let { setBackgroundDrawable(it) }
        }
        }

        }
