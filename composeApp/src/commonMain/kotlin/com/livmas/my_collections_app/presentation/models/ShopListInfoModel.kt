package com.livmas.my_collections_app.presentation.models

import kotlinx.serialization.Serializable

@Serializable
data class ShopListInfoModel(
    val id: Long,
    val name: String
)
