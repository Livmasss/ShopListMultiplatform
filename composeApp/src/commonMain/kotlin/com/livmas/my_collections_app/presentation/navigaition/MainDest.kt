package com.livmas.my_collections_app.presentation.navigaition

import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import kotlinx.serialization.Serializable

sealed interface MainDest {
    @Serializable
    data object Home: MainDest
    @Serializable
    data class ShopList(
        val mainInfo: ShopListInfoModel
    ): MainDest
}