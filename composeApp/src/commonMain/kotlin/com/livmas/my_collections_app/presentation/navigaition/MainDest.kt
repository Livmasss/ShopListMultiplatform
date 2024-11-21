package com.livmas.my_collections_app.presentation.navigaition

import com.livmas.my_collections_app.presentation.screens.home.ListInfoModel
import kotlinx.serialization.Serializable

sealed interface MainDest {
    @Serializable
    data object Home: MainDest
    @Serializable
    data class ShopList(
        val mainInfo: ListInfoModel
    ): MainDest
}