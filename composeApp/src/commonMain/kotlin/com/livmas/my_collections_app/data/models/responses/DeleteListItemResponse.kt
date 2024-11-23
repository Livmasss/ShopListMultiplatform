package com.livmas.my_collections_app.data.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteListItemResponse(
    @SerialName("success")
    override val success: Boolean
): BaseResponse()