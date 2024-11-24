package com.livmas.my_collections_app.presentation.screens.shop_list

import com.livmas.my_collections_app.presentation.models.ShoppingItemModel

sealed interface ShopListScreenIntent {
    data class CrossShoppingItemOut(
        val itemModel: ShoppingItemModel
    ): ShopListScreenIntent

    data class DeleteShoppingItem(
        val itemModel: ShoppingItemModel
    ): ShopListScreenIntent

    data class CreateShoppingItem(
        val itemModel: ShoppingItemModel
    ): ShopListScreenIntent
}