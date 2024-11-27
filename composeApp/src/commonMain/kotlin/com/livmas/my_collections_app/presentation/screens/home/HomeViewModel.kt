package com.livmas.my_collections_app.presentation.screens.home

import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.domain.usecases.CreateShopListUseCase
import com.livmas.my_collections_app.domain.usecases.DeleteShopListUseCase
import com.livmas.my_collections_app.domain.usecases.GetShopListsUseCase
import com.livmas.my_collections_app.mappers.toDomain
import com.livmas.my_collections_app.mappers.toPresentation
import com.livmas.my_collections_app.utils.Resource
import com.livmas.my_collections_app.utils.ScreenState
import com.livmas.my_collections_app.utils.toScreenState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeViewModel(
    private val getShopListsUseCase: GetShopListsUseCase,
    private val createShopListUseCase: CreateShopListUseCase,
    private val deleteShopListUseCase: DeleteShopListUseCase,
): ViewModel() {
    private val _uiState: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    fun initiateData() {
        refreshScreen()
    }

    fun onIntent(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.CreateShopListIntent -> createShopList(intent)
            is HomeScreenIntent.DeleteShopListIntent -> deleteShopList(intent)
            is HomeScreenIntent.RefreshScreen -> refreshScreen()
        }
    }

    private fun deleteShopList(intent: HomeScreenIntent.DeleteShopListIntent) {
        viewModelScope.launch {
            deleteShopListUseCase.execute(
                intent.model.toDomain()
            ).collectLatest { resource ->
                if (resource !is Resource.Success) {
                    resource.handleScreenState()
                    return@collectLatest
                }

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
            createShopListUseCase.execute(intent.state.toDomain()).collectLatest { resource ->
                if (resource !is Resource.Success) {
                    resource.handleScreenState()
                    return@collectLatest
                }

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


    private fun Resource<*>.handleScreenState() {
        val screenState = toScreenState()
        _uiState.update {
            if (this is Resource.Error)
                it.copy(
                    screenState = screenState,
                    error = throwable.message.orEmpty()
                )
            else
                it.copy(
                    screenState = screenState,
                    error = ""
                )
        }
    }

    private fun refreshScreen() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getShopListsUseCase.execute().collectLatest { lists ->
                    _uiState.value = lists.generateHomeScreenState()
                }
            }
        }
    }
}

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
