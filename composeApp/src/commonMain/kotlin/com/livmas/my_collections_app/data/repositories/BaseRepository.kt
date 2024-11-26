package com.livmas.my_collections_app.data.repositories

import co.touchlab.kermit.Logger
import com.livmas.my_collections_app.data.models.responses.BaseResponse
import com.livmas.my_collections_app.utils.Resource
import com.livmas.my_collections_app.utils.ResourceFlow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {
    suspend fun <T: BaseResponse> fetchResponse(dataProvider: suspend () -> T): ResourceFlow<T> {
        return fetchData {
            val result = dataProvider()

            if (!result.success)
                throw Throwable(message = "Not success: $result")
            result
        }
    }
    suspend fun <T> fetchData(dataProvider: suspend () -> T): ResourceFlow<T> {
        return flow {
            emit(Resource.Loading)

            try {
                val result = dataProvider()

                emit(Resource.Success(result))
            }
            catch (t: Throwable) {
                Logger.e(t) { "Some error occurred while fetchData" }
                emit(Resource.Error(t))
            }
        }
    }
}