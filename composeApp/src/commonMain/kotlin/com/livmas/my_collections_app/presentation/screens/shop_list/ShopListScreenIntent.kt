package com.livmas.my_collections_app.presentation.screens.shop_list

import com.livmas.my_collections_app.presentation.models.ShoppingItemModel

sealed interface ShopListScreenIntent {
    data class CrossItemOut(
        val itemModel: ShoppingItemModel
    ): ShopListScreenIntent

    data class DeleteItem(
        val itemModel: ShoppingItemModel
    ): ShopListScreenIntent
}