package com.livmas.my_collections_app.presentation.screens.home

import com.livmas.my_collections_app.domain.models.ListInfo
import com.livmas.my_collections_app.mappers.toPresentation
import com.livmas.my_collections_app.utils.Resource
import com.livmas.my_collections_app.utils.ScreenState

data class HomeScreenState (
    val screenState: ScreenState = ScreenState.LOADING,
    val lists: List<ShopListInfoModel> = listOf()
)

fun Resource<List<ListInfo>>.generateHomeScreenState(): HomeScreenState {
    return when (this) {
        is Resource.Success -> HomeScreenState(
            screenState = ScreenState.SUCCESS,
            lists = data.map { it.toPresentation() }
        )

        is Resource.Error -> HomeScreenState(
            screenState = ScreenState.ERROR,
            lists = listOf()
        )

        is Resource.Loading -> HomeScreenState(
            screenState = ScreenState.LOADING,
            lists = listOf()
        )
    }
}
