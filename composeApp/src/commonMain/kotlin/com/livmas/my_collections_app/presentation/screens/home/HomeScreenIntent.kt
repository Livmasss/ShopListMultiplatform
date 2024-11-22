package com.livmas.my_collections_app.presentation.screens.home

sealed interface HomeScreenIntent {
    data class CreateShopListIntent(
        val info: ShopListInfoModel
    ): HomeScreenIntent
}