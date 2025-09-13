package com.example.learncustomview.exercise2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.learncustomview.Common
import com.example.learncustomview.R
import kotlin.math.sqrt

@SuppressLint("UseKtx")
class Exercise2CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    companion object {
        const val RADIUS = 100f
    }

    private var centerX = 0f
    private var centerY = 0f
    private var lastX = 0f
    private var lastY = 0f
    private var isDragging = false
    private var radius = 0f

    var circleColor = Color.WHITE
    var circleBlurColor= Color.WHITE
    var circleRadius = 0f
    var isDraggable = false

    private var paintCircle = Paint()

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.Exercise2CustomView,
            0,
            0
        )

        circleColor = typedArray.getColor(R.styleable.Exercise2CustomView_circleColor, Color.RED)
        circleBlurColor = typedArray.getColor(R.styleable.Exercise2CustomView_circleBlurColor, Color.GRAY)
        circleRadius = typedArray.getDimension(R.styleable.Exercise2CustomView_circleRadius, 0f)
        isDraggable = typedArray.getBoolean(R.styleable.Exercise2CustomView_draggable, false)

        paintCircle = Paint().apply {
            style = Paint.Style.FILL
            color = circleColor
            setShadowLayer(
                40f,
                0f,
                0f,
                circleBlurColor
            )
        }

        typedArray.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        radius = Common.dpToPixel(context, RADIUS)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if(event == null) return super.onTouchEvent(event)

        when (event.actionMasked) {

            MotionEvent.ACTION_DOWN -> {
                if(isDraggable){
                    lastX = event.x
                    lastY = event.y
                    val disX = lastX - centerX
                    val disY = lastY - centerY
                    val distance = sqrt(disX * disX + disY * disY)
                    if(distance < radius) {
                        isDragging = true
                    } else {
                        isDragging = false
                    }
                }
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                if(isDragging && isDraggable) {
                    centerX += event.x - lastX
                    centerY += event.y - lastY
                }
                lastX = event.x
                lastY = event.y
                invalidate()
                return true
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isDragging = false
                return false
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(
            centerX,
            centerY,
            circleRadius,
            paintCircle
        )
    }

}