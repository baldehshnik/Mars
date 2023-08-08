package com.firstapplication.mars.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsModel(
    val id: String,
    val imageSrcUrl: String
): Parcelable