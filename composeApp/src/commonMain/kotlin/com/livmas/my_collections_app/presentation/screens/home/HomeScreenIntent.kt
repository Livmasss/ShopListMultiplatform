package com.livmas.my_collections_app.presentation.screens.home

import com.livmas.my_collections_app.presentation.screens.home.create_list.CreateShopListState

sealed interface HomeScreenIntent {
    data class CreateShopListIntent(
        val state: CreateShopListState,
        val onSuccess: () -> Unit
    ): HomeScreenIntent
}