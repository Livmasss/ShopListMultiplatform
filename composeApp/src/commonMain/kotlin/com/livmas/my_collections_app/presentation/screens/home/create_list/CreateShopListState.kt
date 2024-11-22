package com.livmas.my_collections_app.presentation.screens.home.create_list

import kotlinx.serialization.Serializable

@Serializable
data class CreateShopListState(
    val name: String = ""
)