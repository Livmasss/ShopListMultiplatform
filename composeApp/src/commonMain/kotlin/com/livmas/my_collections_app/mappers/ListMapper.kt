package com.livmas.my_collections_app.mappers

import com.livmas.my_collections_app.data.models.ListInfoDTO
import com.livmas.my_collections_app.domain.models.ListInfo
import com.livmas.my_collections_app.presentation.screens.home.ShopListInfoModel

fun ListInfoDTO.toDomain(): ListInfo {
    return ListInfo(
        id = id,
        name = name
    )
}

fun ListInfo.toPresentation(): ShopListInfoModel {
    return ShopListInfoModel(
        id = id,
        name = name
    )
}
