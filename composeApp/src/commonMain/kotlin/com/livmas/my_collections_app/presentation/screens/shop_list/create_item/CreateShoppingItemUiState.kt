package com.livmas.my_collections_app.presentation.screens.shop_list.create_item

import kotlinx.serialization.Serializable

@Serializable
data class CreateShoppingItemUiState(
    val text: String = "",
    val count: Int? = null,
)