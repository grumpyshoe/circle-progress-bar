package com.grumpyshoe.circleprogressbar

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator


/*
 * CircleProgressBar
 * CircularProgress
 *
 * Created by Thomas Cirksena on 25.06.20.
 * Copyright © 2020 Thomas Cirksena. All rights reserved.
 */
class CircleProgressBar(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val DEFAULT_COLOR_PROGRESS = Color.BLUE
    private val DEFAULT_COLOR_PROGRESS_BACKGROUND = Color.LTGRAY

    private val desiredWidth = 105
    private val desiredHeight = 105
    private val animateValueChange = true
    private var arcAngle = 270f
    private var anim: ValueAnimator? = null

    private var progressbackgroundTint = DEFAULT_COLOR_PROGRESS_BACKGROUND
    private var progressTint = DEFAULT_COLOR_PROGRESS
    private val strokeWidth = 11f
    private var max = 100

    init {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar)

        progressTint = typedArray.getColor(R.styleable.CircleProgressBar_progressTint, DEFAULT_COLOR_PROGRESS)
        progressbackgroundTint = typedArray.getColor(R.styleable.CircleProgressBar_progressBackgroundTint, DEFAULT_COLOR_PROGRESS_BACKGROUND)
        max = typedArray.getInteger(R.styleable.CircleProgressBar_max, 0)

        typedArray.recycle()

        initPaint()
    }


    /**
     * set max valu
     *
     */
    fun setMax(maxValue: Int) {
        max = maxValue
        invalidate()
    }

    /**
     * update progress value
     *
     */
    fun updateProgress(progressValue: Int) {
        if (animateValueChange) {
            initValueChangeAnimation(progressValue)
        } else {
            arcAngle = calculateAngle(progressValue)
            invalidate()
        }
    }

    /**
     * calculate angle according to progress value
     *
     */
    private fun calculateAngle(progressValue: Int) = 360f / max * progressValue

    /**
     * init animation for value change
     *
     */
    private fun initValueChangeAnimation(newProgressValue: Int) {

        // cancel current animation
        anim?.cancel()

        // calculate new angle
        val newAngle = calculateAngle(newProgressValue)

        // start animation
        anim = ValueAnimator.ofFloat(arcAngle, newAngle).apply {
            anim = this
            this.duration = 500
            this.interpolator = DecelerateInterpolator()
            this.addUpdateListener {
                arcAngle = it.animatedValue as Float
                invalidate()
            }
            this.start()
        }
    }

    private fun initPaint() {

        // background
        backgroundPaint.color = progressbackgroundTint
        backgroundPaint.style = Paint.Style.STROKE
        backgroundPaint.strokeWidth = strokeWidth

        // progress
        progressPaint.color = progressTint
        progressPaint.style = Paint.Style.STROKE
        progressPaint.strokeWidth = strokeWidth
        progressPaint.strokeCap = Paint.Cap.ROUND
    }

    /**
     * override onDraw to trigger custom draw methods
     *
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBackground(canvas)
        drawProgress(canvas)
    }

    /**
     * draw the progress background
     *
     */
    private fun drawBackground(canvas: Canvas) {
        canvas.drawCircle((width / 2f), (height / 2f), width / 2f - (strokeWidth / 2), backgroundPaint)
    }

    /**
     * draw the progress on canvas
     *
     */
    private fun drawProgress(canvas: Canvas) {
        val ovalRect = RectF((strokeWidth / 2), (strokeWidth / 2), (width - (strokeWidth / 2)), (height - (strokeWidth / 2)).toFloat())
        canvas.drawArc(ovalRect, 270f, arcAngle, false, progressPaint)
    }

    /**
     * measure space for this view
     *
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val width: Int
        val height: Int

        //Measure Width
        width = if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            widthSize
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            Math.min(desiredWidth, widthSize)
        } else {
            //Be whatever you want
            desiredWidth
        }

        //Measure Height
        height = if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            heightSize
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            Math.min(desiredHeight, heightSize)
        } else {
            //Be whatever you want
            desiredHeight
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height)
    }
}