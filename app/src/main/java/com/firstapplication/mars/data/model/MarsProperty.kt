package com.firstapplication.mars.data.model

import com.firstapplication.mars.domain.model.MarsModel

data class MarsProperty(
    val id: String,
    val img_src: String
)

fun MarsProperty.migrateToMarsModel(): MarsModel = MarsModel(
    id = id,
    imageSrcUrl = img_src
)