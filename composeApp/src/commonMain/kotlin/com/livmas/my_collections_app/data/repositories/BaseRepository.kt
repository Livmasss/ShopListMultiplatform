package com.livmas.my_collections_app.data.repositories

import com.livmas.my_collections_app.utils.Resource
import com.livmas.my_collections_app.utils.ResourceFlow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {
    suspend fun <T> fetchData(dataProvider: suspend () -> T): ResourceFlow<T> {
        return flow {
            emit(Resource.Loading)

            try {
                val result = dataProvider()

                emit(Resource.Success(result))
            }
            catch (t: Throwable) {
                println(t.stackTraceToString())
                emit(Resource.Error(t))
            }
        }
    }
}