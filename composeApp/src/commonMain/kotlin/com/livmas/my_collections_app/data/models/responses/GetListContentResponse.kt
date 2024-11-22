package com.livmas.my_collections_app.data.models.responses

import com.livmas.my_collections_app.data.models.ShoppingItemDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetListContentResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("item_list")
    val items: List<ShoppingItemDTO>
)
