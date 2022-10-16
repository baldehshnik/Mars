package com.firstapplication.mars.extensions

import com.firstapplication.mars.data.models.MarsProperty
import com.firstapplication.mars.ui.models.MarsModel

fun MarsProperty.migrateToMarsModel(): MarsModel = MarsModel(
    id = id,
    price = price,
    type = type,
    imageSrcUrl = img_src
)

fun MarsModel.migrateToMarsProperty(): MarsProperty = MarsProperty(
    id = id,
    price = price,
    type = type,
    img_src = imageSrcUrl
)