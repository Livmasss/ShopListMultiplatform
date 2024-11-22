package com.livmas.my_collections_app.presentation.models

data class ShoppingItemModel(
    val id: Long,
    val text: String,
    val count: Int,
    val isCrossed: Boolean
)
