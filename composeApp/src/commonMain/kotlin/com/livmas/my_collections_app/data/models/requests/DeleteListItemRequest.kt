package com.livmas.my_collections_app.data.models.requests

data class DeleteListItemRequest(
    val listId: Long,
    val itemId: Long,
)
