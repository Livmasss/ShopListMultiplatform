package com.livmas.my_collections_app.data.models.requests

data class CreateListItemRequest(
    val listId: Long,
    val text: String,
    val count: Int
)
