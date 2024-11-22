package com.livmas.my_collections_app.utils

import kotlinx.coroutines.flow.Flow

sealed interface Resource<out T> {
    data object Loading : Resource<Nothing>
    data class Error(val throwable: Throwable) : Resource<Nothing>
    data class Success<T>(val data: T) : Resource<T>
}

typealias ResourceFlow<T> = Flow<Resource<T>>
