package com.livmas.my_collections_app.mappers

import com.livmas.my_collections_app.data.models.ListInfoDTO
import com.livmas.my_collections_app.data.models.ShoppingItemDTO
import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.domain.models.ShoppingItem
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import com.livmas.my_collections_app.presentation.models.ShoppingItemModel

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

fun ShoppingItem.toDto(): ShoppingItemDTO {
    return ShoppingItemDTO(
        id = id,
        count = count,
        text = text,
        isCrossed = isCrossed
    )
}

fun ShoppingItemDTO.toDomain(): ShoppingItem {
    return ShoppingItem(
        id = id,
        count = count,
        text = text,
        isCrossed = isCrossed
    )
}

fun ShoppingItem.toPresentation(): ShoppingItemModel {
    return ShoppingItemModel(
        id = id,
        text = text,
        count = count,
        isCrossed = isCrossed
    )
}
