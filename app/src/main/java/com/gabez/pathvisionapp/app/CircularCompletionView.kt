package com.gabez.pathvisionapp.app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Align
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi


class CircularCompletionView : View {
    private var completionPercent = 0
    private val paint: Paint = Paint()
    private var radius: Float = 100F
    private var strokeSize: Float = 20F
    private var textSize: Float = 10F
    private var diameter = radius * 2

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?) : super(context) {
    }

    fun setCompletionPercentage(completion: Int) {
        completionPercent = completion
        invalidate()
    }

    fun setTextSize(size: Float) {
        textSize = size
        invalidate()
    }

    fun setStrokeSize(size: Float) {
        strokeSize = size
        invalidate()
    }

    protected override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width: Int = MeasureSpec.getSize(widthMeasureSpec)
        var height: Int = MeasureSpec.getSize(heightMeasureSpec)

        if (width > height) width = height
        else height = width

        diameter = width.toFloat()
        radius = diameter / 2
        val newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        val newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec)
    }

    protected override fun onDraw(canvas: Canvas) {
        paint.color = Color.parseColor("#dedede") // circle stroke color- grey
        paint.strokeWidth = strokeSize
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE

        canvas.drawCircle(radius, radius, radius - 10, paint)
        paint.color = Color.parseColor("#04B404") // circle stroke color(indicating completion Percentage) - green
        paint.strokeWidth = strokeSize
        paint.style = Paint.Style.FILL

        val oval = RectF()

        paint.style = Paint.Style.STROKE

        oval[10f, 10f, diameter - 10.toFloat()] = diameter - 10.toFloat()
        canvas.drawArc(oval, 270F, (completionPercent * 360 / 100).toFloat(), false, paint)

        paint.textAlign = Align.CENTER
        paint.color = Color.parseColor("#282828") // text color - dark grey
        paint.style = Paint.Style.FILL

        paint.textSize = textSize
        canvas.drawText("$completionPercent%", radius, radius + paint.textSize / 2, paint)
    }
}