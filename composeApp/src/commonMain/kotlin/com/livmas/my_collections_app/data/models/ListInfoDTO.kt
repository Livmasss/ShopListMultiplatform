package com.livmas.my_collections_app.data.models

import kotlinx.serialization.Serializable

@Serializable
class ListInfoDTO(
    val id: Long,
    val name: String,
    val created: String
)