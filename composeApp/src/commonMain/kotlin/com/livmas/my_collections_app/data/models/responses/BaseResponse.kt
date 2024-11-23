package com.livmas.my_collections_app.data.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class BaseResponse {
    @SerialName("success")
    abstract val success: Boolean
}