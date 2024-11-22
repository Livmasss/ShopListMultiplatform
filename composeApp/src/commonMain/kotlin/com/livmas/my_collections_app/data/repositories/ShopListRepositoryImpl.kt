package com.livmas.my_collections_app.data.repositories

import com.livmas.my_collections_app.data.data_sources.ListRemoteDataSource
import com.livmas.my_collections_app.data.data_sources.UserRemoteDataSource
import com.livmas.my_collections_app.data.models.requests.CreateListRequest
import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.domain.repositories.ShopListRepository
import com.livmas.my_collections_app.mappers.toDomain
import com.livmas.my_collections_app.utils.Resource
import com.livmas.my_collections_app.utils.ResourceFlow
import kotlinx.coroutines.flow.flow

class ShopListRepositoryImpl(
    private val listRemoteDataSource: ListRemoteDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
): ShopListRepository {
    override suspend fun getLists(): ResourceFlow<List<ShopListInfo>> {
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

    override suspend fun createList(shopListInfo: ShopListInfo): ResourceFlow<ShopListInfo> {
        return flow {
            emit(Resource.Loading)
            try {
                val key = userRemoteDataSource.getUserKey()
                val result = listRemoteDataSource.createList(
                    authKey = key,
                    request = CreateListRequest(shopListInfo.name)
                )

                if (!result.success)
                    throw Throwable(message = "Not success: $result")

                emit(Resource.Success(shopListInfo))
            }
            catch (t: Throwable) {
                emit(Resource.Error(t))
            }
        }
    }

    override suspend fun deleteList(shopListInfo: ShopListInfo): ResourceFlow<ShopListInfo> {
        TODO("Not yet implemented")
    }
}