package com.livmas.my_collections_app.presentation.screens.home

import com.livmas.my_collections_app.data.KtorClient
import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.domain.usecases.CreateShopListUseCase
import com.livmas.my_collections_app.domain.usecases.DeleteShopListUseCase
import com.livmas.my_collections_app.domain.usecases.GetShopListsUseCase
import com.livmas.my_collections_app.mappers.toDomain
import com.livmas.my_collections_app.mappers.toPresentation
import com.livmas.my_collections_app.utils.Resource
import com.livmas.my_collections_app.utils.toScreenState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel(
    private val getShopListsUseCase: GetShopListsUseCase,
    private val createShopListUseCase: CreateShopListUseCase,
    private val deleteShopListUseCase: DeleteShopListUseCase,
): ViewModel() {
    private val _uiState: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    override fun onCleared() {
        KtorClient.client.close()
    }

    fun initiateData() {
        viewModelScope.launch {
            getShopListsUseCase.execute().collectLatest { lists ->
                _uiState.value = lists.generateHomeScreenState()
            }
        }
    }

    fun onIntent(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.CreateShopListIntent -> createShopList(intent)
            is HomeScreenIntent.DeleteShopListIntent -> deleteShopList(intent)
        }
    }

    private fun deleteShopList(intent: HomeScreenIntent.DeleteShopListIntent) {
        viewModelScope.launch {
            deleteShopListUseCase.execute(
                intent.model.toDomain()
            ).collectLatest { resource ->
                if (resource !is Resource.Success)
                    return@collectLatest

                _uiState.update { currentState ->
                    HomeScreenState(
                        screenState = resource.toScreenState(),
                        lists = currentState.lists - intent.model
                    )
                }
            }
        }
    }

    private fun createShopList(intent: HomeScreenIntent.CreateShopListIntent) {
        viewModelScope.launch {
            createShopListUseCase.execute(
                ShopListInfo(
                    id = 0,
                    name = intent.state.name
                )
            ).collectLatest { resource ->
                if (resource !is Resource.Success)
                    return@collectLatest

                _uiState.update { currentState ->
                    HomeScreenState(
                        screenState = resource.toScreenState(),
                        lists = currentState.lists + resource.data.toPresentation()
                    )
                }
                intent.onSuccess()
            }
        }
    }
}

fun HomeScreenState.handleNotSuccess() {

}