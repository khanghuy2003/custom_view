package com.example.learncustomview

import android.content.Context
import android.util.TypedValue


object Common {

    fun dpToPixel(context: Context, dpValue: Float): Float {
        val metrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, metrics)
    }

}