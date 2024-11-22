package com.livmas.my_collections_app.presentation.screens.shop_list

import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import com.livmas.my_collections_app.presentation.models.ShoppingItemModel
import com.livmas.my_collections_app.utils.ScreenState

data class ShopListScreenState(
    val screenState: ScreenState = ScreenState.LOADING,
    val listInfoModel: ShopListInfoModel? = null,
    val listContent: List<ShoppingItemModel> = listOf()
)
