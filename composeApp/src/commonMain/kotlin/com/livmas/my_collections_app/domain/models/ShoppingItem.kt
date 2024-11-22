package com.livmas.my_collections_app.domain.models

data class ShoppingItem (
    val id: Long,
    val text: String,
    val count: Int,
    val isCrossed: Boolean
)