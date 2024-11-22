package com.livmas.my_collections_app.presentation.screens.shop_list

import com.livmas.my_collections_app.presentation.models.ListItemModel
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import com.livmas.my_collections_app.utils.ScreenState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShopListViewModel: ViewModel() {
    private val _state = MutableStateFlow(ShopListScreenState())
    val state = _state.asStateFlow()
    fun initiateData(listInfo: ShopListInfoModel) {
        _state.update {
            it.copy(
                screenState = ScreenState.LOADING,
                listInfoModel = listInfo,
                listContent = listOf(
                    ListItemModel(0, "123", 12, false)
                )
            )
        }
    }
}