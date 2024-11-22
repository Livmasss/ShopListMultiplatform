package com.livmas.my_collections_app.presentation.screens.home

import com.livmas.my_collections_app.data.KtorClient
import com.livmas.my_collections_app.domain.repositories.ShopListRepository
import com.livmas.my_collections_app.domain.usecases.CreateShopListUseCase
import com.livmas.my_collections_app.domain.usecases.GetShopListsUseCase
import com.livmas.my_collections_app.mappers.toDomain
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeViewModel(
    private val getShopListsUseCase: GetShopListsUseCase,
    private val createShopListUseCase: CreateShopListUseCase
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
        }
    }

    private fun createShopList(intent: HomeScreenIntent.CreateShopListIntent) {
        viewModelScope.launch {
            createShopListUseCase.execute(
                intent.info.toDomain()
            )
        }
    }
}