package com.livmas.my_collections_app.mappers

import com.livmas.my_collections_app.data.models.ListInfoDTO
import com.livmas.my_collections_app.domain.models.ListInfo
import com.livmas.my_collections_app.presentation.screens.home.ListInfoModel

fun ListInfoDTO.toDomain(): ListInfo {
    return ListInfo(
        id = id,
        name = name
    )
}

fun ListInfo.toPresentation(): ListInfoModel {
    return ListInfoModel(
        id = id,
        name = name
    )
}
