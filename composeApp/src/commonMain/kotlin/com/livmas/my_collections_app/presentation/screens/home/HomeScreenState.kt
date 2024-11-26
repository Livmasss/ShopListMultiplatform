package com.livmas.my_collections_app.presentation.screens.home

import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import com.livmas.my_collections_app.utils.ScreenState

data class HomeScreenState (
    val screenState: ScreenState = ScreenState.LOADING,
    val lists: List<ShopListInfoModel> = listOf(),
    val error: String = ""
)
