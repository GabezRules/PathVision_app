package com.gabez.pathvisionapp.app

import android.content.Context
import android.graphics.*
import android.graphics.Paint.Align
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.gabez.pathvisionapp.R


class CircularCompletionView : View {
    private var completionPercent = 0
    private val paint: Paint = Paint()
    private var radius: Float = 100F
    private var strokeSize: Float = 20F
    private var textSize: Float = 10F
    private var diameter = radius * 2

    private var TEXT_COLOR: String = "#535353"
    private var STROKE_COLOR: String = "#dedede"
    private var STROKE_FILL_COLOR: String = "#6200EE"

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?) : super(context)

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

    fun setTextColor(color: String){
        TEXT_COLOR = color
        invalidate()
    }

    fun setStrokeColor(color: String){
        STROKE_COLOR = color
        invalidate()
    }

    fun setStrokeFillColor(color: String){
        STROKE_FILL_COLOR = color
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
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

    override fun onDraw(canvas: Canvas) {
        paint.color = Color.parseColor(STROKE_COLOR)
        paint.strokeWidth = strokeSize
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE

        canvas.drawCircle(radius, radius, radius - 10, paint)
        paint.color = Color.parseColor(STROKE_FILL_COLOR)
        paint.strokeWidth = strokeSize
        paint.style = Paint.Style.FILL

        val oval = RectF()

        paint.style = Paint.Style.STROKE

        oval[10f, 10f, diameter - 10.toFloat()] = diameter - 10.toFloat()
        canvas.drawArc(oval, 270F, (completionPercent * 360 / 100).toFloat(), false, paint)

        paint.textAlign = Align.CENTER
        paint.color = Color.parseColor(TEXT_COLOR)
        paint.style = Paint.Style.FILL
        paint.textSize = textSize
        paint.typeface = ResourcesCompat.getFont(context, R.font.montserrat_medium)!!

        canvas.drawText("$completionPercent%", radius, radius + paint.textSize / 2, paint)
    }
}