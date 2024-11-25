package com.livmas.my_collections_app.presentation.screens.home

import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.mappers.toPresentation
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import com.livmas.my_collections_app.utils.Resource
import com.livmas.my_collections_app.utils.ScreenState

data class HomeScreenState (
    val screenState: ScreenState = ScreenState.LOADING,
    val lists: List<ShopListInfoModel> = listOf(),
    val error: String = ""
)

fun Resource<List<ShopListInfo>>.generateHomeScreenState(): HomeScreenState {
    return when (this) {
        is Resource.Success -> HomeScreenState(
            screenState = ScreenState.SUCCESS,
            lists = data.map { it.toPresentation() }
        )

        is Resource.Error -> HomeScreenState(
            screenState = ScreenState.ERROR,
            lists = listOf(),
            error = throwable.message.orEmpty()
        )

        is Resource.Loading -> HomeScreenState(
            screenState = ScreenState.LOADING,
            lists = listOf()
        )
    }
}
