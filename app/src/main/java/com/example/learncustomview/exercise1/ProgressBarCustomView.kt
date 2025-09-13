package com.example.learncustomview.exercise1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.learncustomview.Common

class ProgressBarCustomView : View {

    // Secondary constructors (don't use @JvmOverloads as it can lead to bugs).
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paintProgress = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = Common.dpToPixel(context, 2f)
        color = Color.BLACK
    }
    private val paintThumb = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLUE
    }

    private var lineStartX = 0f
    private var lineStartY = 0f

    private var lineEndX = 0f
    private var lineEndY = 0f

    private var centerX = 0f
    private var centerY = 0f

    private var radius = 0f

    private val PADDING = Common.dpToPixel(context, 50f)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        lineStartX = 0f + PADDING
        lineStartY = h / 2f
        lineEndX = w - PADDING
        lineEndY = h / 2f

        centerX = (lineStartX + lineEndX) / 2f
        centerY = lineStartY

        radius = Common.dpToPixel(context, 10f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawLine(
            lineStartX,
            lineStartY,
            lineEndX,
            lineEndY,
            paintProgress
        )

        canvas.drawCircle(centerX, centerY, radius, paintThumb)
    }

    companion object {

    }
}