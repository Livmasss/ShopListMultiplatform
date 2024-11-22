package com.livmas.my_collections_app.mappers

import com.livmas.my_collections_app.data.models.ListInfoDTO
import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel

fun ListInfoDTO.toDomain(): ShopListInfo {
    return ShopListInfo(
        id = id,
        name = name
    )
}

fun ShopListInfo.toPresentation(): ShopListInfoModel {
    return ShopListInfoModel(
        id = id,
        name = name
    )
}

fun ShopListInfoModel.toDomain(): ShopListInfo {
    return ShopListInfo(
        id = id,
        name = name
    )
}
