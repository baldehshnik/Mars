package com.firstapplication.mars.ui.utils

import android.view.animation.Interpolator
import kotlin.math.cos
import kotlin.math.pow

class ShareButtonInterpolator(
    private val viewCount: Double,
    private val frameCount: Double
) : Interpolator {

    override fun getInterpolation(time: Float): Float {
        return (-1 * Math.E.pow(-time / viewCount) * cos(frameCount * time) + 1).toFloat()
    }

}