package com.livmas.my_collections_app.data.models.responses

import com.livmas.my_collections_app.data.models.ListInfoDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllListsResponseBody (
    @SerialName("shop_list")
    val shopList: List<ListInfoDTO>
)