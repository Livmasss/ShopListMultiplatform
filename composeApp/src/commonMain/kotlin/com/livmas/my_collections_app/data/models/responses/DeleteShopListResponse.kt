package com.livmas.my_collections_app.data.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteShopListResponse (
    @SerialName("success")
    override val success: Boolean,
    @SerialName("new_value")
    val newValue: Boolean
): BaseResponse()