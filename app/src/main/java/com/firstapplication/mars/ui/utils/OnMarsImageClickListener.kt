package com.firstapplication.mars.ui.utils

import android.widget.ImageView
import com.firstapplication.mars.domain.model.MarsModel

interface OnMarsImageClickListener {
    fun onClick(model: MarsModel, imageView: ImageView)
}