package com.livmas.my_collections_app.presentation.screens.home

import kotlinx.serialization.Serializable

@Serializable
data class ListInfoModel(
    val id: Long,
    val name: String
)
