package com.livmas.my_collections_app.data.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateListItemResponse(
    @SerialName("success")
    override val success: Boolean,
    @SerialName("item_id")
    val itemId: Long
): BaseResponse()