package com.livmas.my_collections_app.data.repositories

import com.livmas.my_collections_app.data.models.responses.BaseResponse
import com.livmas.my_collections_app.domain.repositories.UserRepository
import com.livmas.my_collections_app.utils.ResourceFlow

abstract class BaseAuthorizedRepository(
    private val userRepository: UserRepository
): BaseRepository() {
    suspend fun <T: BaseResponse> fetchAuthorizedResponse(dataProvider: suspend (authKey: String) -> T): ResourceFlow<T> {
        return fetchResponse {
            val key = userRepository.getUserKey()
            dataProvider(key)
        }
    }
    suspend fun <T> fetchAuthorized(dataProvider: suspend (authKey: String) -> T): ResourceFlow<T> {
        return fetchData {
            val key = userRepository.getUserKey()
            dataProvider(key)
        }
    }
}