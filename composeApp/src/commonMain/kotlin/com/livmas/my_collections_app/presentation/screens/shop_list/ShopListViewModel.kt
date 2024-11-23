package com.livmas.my_collections_app.presentation.screens.shop_list

import com.livmas.my_collections_app.domain.models.ShoppingItem
import com.livmas.my_collections_app.domain.usecases.CrossListItemOutUseCase
import com.livmas.my_collections_app.domain.usecases.DeleteListItemUseCase
import com.livmas.my_collections_app.domain.usecases.GetListContentUseCase
import com.livmas.my_collections_app.mappers.toDomain
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
    private val getListContentUseCase: GetListContentUseCase,
    private val crossListItemOutUseCase: CrossListItemOutUseCase,
    private val deleteListItemUseCase: DeleteListItemUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow(ShopListScreenState())
    val uiState = _uiState.asStateFlow()

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

    fun onIntent(intent: ShopListScreenIntent) {
        when(intent) {
            is ShopListScreenIntent.CrossItemOut -> crossListItemOut(intent)
            is ShopListScreenIntent.DeleteItem -> deleteListItem(intent)
        }
    }

    private fun Resource<List<ShoppingItem>>.handleInitialResource(): ShopListScreenState {
        return when(this) {
            is Resource.Success -> ShopListScreenState(
                screenState = ScreenState.SUCCESS,
                listInfoModel = uiState.value.listInfoModel,
                listContent = data.map {
                    it.toPresentation()
                }
            )
            is Resource.Error -> uiState.value.copy(
                screenState = ScreenState.ERROR,
            )
            is Resource.Loading -> uiState.value.copy(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun crossListItemOut(intent: ShopListScreenIntent.CrossItemOut) {
        viewModelScope.launch {
            uiState.value.listInfoModel?.id?.let {
                crossListItemOutUseCase.execute(
                    listId = it,
                    item = intent.itemModel.toDomain()
                ).collectLatest { result ->
                    if(result !is Resource.Success<Boolean>)
                        return@collectLatest

                    val index = uiState.value.listContent.indexOfFirst { item ->
                        item == intent.itemModel
                    }
                    val list = uiState.value.listContent.toMutableList()
                    list[index] = list[index].copy(
                        isCrossed = result.data
                    )

                    _uiState.value = _uiState.value.copy(
                        listContent = list
                    )
                }
            }
        }
    }

    private fun deleteListItem(intent: ShopListScreenIntent.DeleteItem) {
        viewModelScope.launch {
            uiState.value.listInfoModel?.id?.let {
                deleteListItemUseCase.execute(
                    listId = it,
                    itemId = intent.itemModel.id
                ).collectLatest { result ->
                    if(result !is Resource.Success<Unit>)
                        return@collectLatest

                    val index = uiState.value.listContent.indexOfFirst { item ->
                        item == intent.itemModel
                    }
                    val list = uiState.value.listContent.toMutableList()
                    list.removeAt(index)

                    _uiState.value = _uiState.value.copy(
                        listContent = list
                    )
                }
            }
        }
    }
}