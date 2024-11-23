package com.livmas.my_collections_app.data.repositories

import com.livmas.my_collections_app.data.data_sources.UserRemoteDataSource
import com.livmas.my_collections_app.utils.Resource
import com.livmas.my_collections_app.utils.ResourceFlow
import kotlinx.coroutines.flow.flow

abstract class BaseAuthorizedRepository(
    private val userRemoteDataSource: UserRemoteDataSource
): BaseRepository() {
    suspend fun <T> fetchAuthorized(dataProvider: suspend (authKey: String) -> T): ResourceFlow<T> {
        return fetchData {
            val key = userRemoteDataSource.getUserKey()
            dataProvider(key)
        }
    }
}