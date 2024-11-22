package com.livmas.my_collections_app.data.repositories

import com.livmas.my_collections_app.data.data_sources.ListRemoteDataSource
import com.livmas.my_collections_app.data.data_sources.UserRemoteDataSource
import com.livmas.my_collections_app.data.models.requests.CreateListRequest
import com.livmas.my_collections_app.domain.models.ListInfo
import com.livmas.my_collections_app.domain.repositories.ShopListRepository
import com.livmas.my_collections_app.mappers.toDomain
import com.livmas.my_collections_app.utils.Resource
import com.livmas.my_collections_app.utils.ResourceFlow
import kotlinx.coroutines.flow.flow

class ShopListRepositoryImpl(
    private val listRemoteDataSource: ListRemoteDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
): ShopListRepository {
    override suspend fun getLists(): ResourceFlow<List<ListInfo>> {
        return flow {
            emit(Resource.Loading)
            try {
                val key = userRemoteDataSource.getUserKey()
                val result = listRemoteDataSource.fetchLists(key).shopList.map {
                    it.toDomain()
                }

                emit(Resource.Success(result))
            }
            catch (t: Throwable) {
                println(t.stackTraceToString())
                emit(Resource.Error(t))
            }
        }
    }

    override suspend fun createList(listInfo: ListInfo): ResourceFlow<ListInfo> {
        return flow {
            emit(Resource.Loading)
            try {
                val key = userRemoteDataSource.getUserKey()
                val result = listRemoteDataSource.createList(
                    authKey = key,
                    request = CreateListRequest(listInfo.name)
                )

                if (!result.success)
                    throw Throwable(message = "Now success: $result")

                emit(Resource.Success(listInfo))
            }
            catch (t: Throwable) {
                emit(Resource.Error(t))
            }
        }
    }

    override suspend fun deleteList(listInfo: ListInfo): ResourceFlow<ListInfo> {
        TODO("Not yet implemented")
    }
}