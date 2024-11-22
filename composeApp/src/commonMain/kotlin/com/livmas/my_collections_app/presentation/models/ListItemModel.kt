package com.livmas.my_collections_app.presentation.models

data class ListItemModel(
    val id: Long,
    val text: String,
    val count: Int,
    val isCrossed: Boolean
)
