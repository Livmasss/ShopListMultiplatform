package com.livmas.my_collections_app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShoppingItemDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("created")
    val count: Int,
    @SerialName("name")
    val text: String,
    @SerialName("is_crossed")
    val isCrossed: Boolean,
)