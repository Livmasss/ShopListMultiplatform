package com.livmas.my_collections_app.data.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateListResponse (
    @SerialName("success")
    val success: Boolean,
    @SerialName("list_id")
    val listId: Long
)