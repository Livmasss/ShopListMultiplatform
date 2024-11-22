package com.livmas.my_collections_app.utils

enum class ScreenState {
    LOADING,
    ERROR,
    SUCCESS;
}


fun <T> Resource<T>.toScreenState(): ScreenState {
    return when(this) {
        is Resource.Loading -> ScreenState.LOADING
        is Resource.Success -> ScreenState.SUCCESS
        is Resource.Error -> ScreenState.ERROR
    }
}
