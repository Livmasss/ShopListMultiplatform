package com.livmas.my_collections_app.presentation.screens.shop_list

import com.livmas.my_collections_app.domain.models.ShoppingItem
import com.livmas.my_collections_app.domain.usecases.GetListContentUseCase
import com.livmas.my_collections_app.mappers.toPresentation
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import com.livmas.my_collections_app.utils.Resource
import com.livmas.my_collections_app.utils.ScreenState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ShopListViewModel(
    val getListContentUseCase: GetListContentUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(ShopListScreenState())
    val state = _uiState.asStateFlow()

    fun initiateData(listInfo: ShopListInfoModel) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                screenState = ScreenState.LOADING,
                listInfoModel = listInfo,
                listContent = listOf()
            )

            getListContentUseCase.execute(listInfo.id).collectLatest {
                _uiState.value = it.handleInitialResource()
            }
        }
    }

    private fun Resource<List<ShoppingItem>>.handleInitialResource(): ShopListScreenState {
        return when(this) {
            is Resource.Success -> ShopListScreenState(
                screenState = ScreenState.SUCCESS,
                listInfoModel = state.value.listInfoModel,
                listContent = this.data.map {
                    it.toPresentation()
                }
            )
            is Resource.Error -> state.value.copy(
                screenState = ScreenState.ERROR,
            )
            is Resource.Loading -> state.value.copy(
                screenState = ScreenState.LOADING,
            )
        }
    }

    fun onIntent(intent: ShopListScreenIntent) {

    }
}