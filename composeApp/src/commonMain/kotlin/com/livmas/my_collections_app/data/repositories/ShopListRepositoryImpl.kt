package com.livmas.my_collections_app.data.repositories

import com.livmas.my_collections_app.data.data_sources.ListRemoteDataSource
import com.livmas.my_collections_app.data.data_sources.UserRemoteDataSource
import com.livmas.my_collections_app.data.models.requests.CreateListItemRequest
import com.livmas.my_collections_app.data.models.requests.CreateListRequest
import com.livmas.my_collections_app.data.models.requests.CrossItemOutRequest
import com.livmas.my_collections_app.data.models.requests.DeleteListItemRequest
import com.livmas.my_collections_app.data.models.requests.DeleteListRequest
import com.livmas.my_collections_app.data.models.requests.GetListContentRequest
import com.livmas.my_collections_app.data.models.responses.BaseResponse
import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.domain.models.ShoppingItem
import com.livmas.my_collections_app.domain.repositories.ShopListRepository
import com.livmas.my_collections_app.mappers.toDomain
import com.livmas.my_collections_app.utils.Resource
import com.livmas.my_collections_app.utils.ResourceFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class ShopListRepositoryImpl(
    private val listRemoteDataSource: ListRemoteDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
): ShopListRepository, BaseAuthorizedRepository(userRemoteDataSource) {
    override suspend fun getShopLists(): ResourceFlow<List<ShopListInfo>> {
        return flow {
            emit(Resource.Loading)
            try {
                val key = userRemoteDataSource.getUserKey()
                val result = listRemoteDataSource.getShopLists(key).shopList.map {
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

    override suspend fun createShopList(shopListInfo: ShopListInfo): ResourceFlow<ShopListInfo> {
        return flow {
            emit(Resource.Loading)
            try {
                val key = userRemoteDataSource.getUserKey()
                val result = listRemoteDataSource.createShopList(
                    authKey = key,
                    request = CreateListRequest(shopListInfo.name)
                )

                if (!result.success)
                    throw Throwable(message = "Not success: $result")

                emit(Resource.Success(shopListInfo.copy(
                    id = result.listId
                )))
            }
            catch (t: Throwable) {
                emit(Resource.Error(t))
            }
        }
    }

    override suspend fun deleteShopList(shopListInfo: ShopListInfo): ResourceFlow<BaseResponse> {
        return fetchAuthorizedResponse { authKey ->
            val result = listRemoteDataSource.deleteShopList(
                authKey = authKey,
                request = DeleteListRequest(shopListInfo.id)
            )

            if (!result.newValue)
                throw IllegalStateException("Not success")
            result
        }
    }

    override suspend fun getListContent(listId: Long): ResourceFlow<List<ShoppingItem>> {
        return flow {
            emit(Resource.Loading)

            try {
                val key = userRemoteDataSource.getUserKey()
                val result = listRemoteDataSource.getListContent(key, GetListContentRequest(listId))

                if (!result.success)
                    throw Throwable(message = "Not success: $result")

                emit(Resource.Success(result.items.map { it.toDomain() }))
            }
            catch (t: Throwable) {
                emit(Resource.Error(t))
            }
        }
    }

    override suspend fun deleteListItem(listId: Long, itemId: Long): ResourceFlow<Unit> {
        return flow {
            emit(Resource.Loading)
            try {
                val result = listRemoteDataSource.deleteListItem(
                    authKey = userRemoteDataSource.getUserKey(),
                    request = DeleteListItemRequest(
                        listId, itemId
                    )
                )

                if (!result.success)
                    throw Throwable(message = "Not success: $result")

                emit(Resource.Success(Unit))
            }
            catch (t: Throwable) {
                emit(Resource.Error(t))
            }
        }
    }

    override suspend fun crossOutListItem(listId: Long, item: ShoppingItem): ResourceFlow<Boolean> {
        return fetchAuthorized {
            listRemoteDataSource.crossItemOut(
                authKey = userRemoteDataSource.getUserKey(),
                request = CrossItemOutRequest(
                    listId, item.id
                )
            )

            !item.isCrossed
        }
    }

    override suspend fun createListItem(
        listId: Long,
        item: ShoppingItem
    ): ResourceFlow<ShoppingItem> {
        return fetchAuthorized {
            val result = listRemoteDataSource.createListItem(it, CreateListItemRequest(
                listId = listId,
                text = item.text,
                count = item.count
            ))

            item.copy(
                id = result.itemId
            )
        }
    }
}