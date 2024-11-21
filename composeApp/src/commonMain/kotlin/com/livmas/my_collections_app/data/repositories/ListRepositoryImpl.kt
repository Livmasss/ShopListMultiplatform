package com.livmas.my_collections_app.data.repositories

import com.livmas.my_collections_app.data.data_sources.ListRemoteDataSource
import com.livmas.my_collections_app.data.data_sources.UserRemoteDataSource
import com.livmas.my_collections_app.domain.models.ListInfo
import com.livmas.my_collections_app.domain.repositories.ListRepository
import com.livmas.my_collections_app.mappers.toDomain
import com.livmas.my_collections_app.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ListRepositoryImpl(
    private val listRemoteDataSource: ListRemoteDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
): ListRepository {
    override suspend fun getLists(): Flow<Resource<List<ListInfo>>> {
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
}