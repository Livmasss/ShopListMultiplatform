package com.livmas.my_collections_app.data.repositories

import com.livmas.my_collections_app.data.models.requests.CreateListItemRequest
import com.livmas.my_collections_app.data.models.requests.CreateListRequest
import com.livmas.my_collections_app.data.models.requests.CrossItemOutRequest
import com.livmas.my_collections_app.data.models.requests.DeleteListItemRequest
import com.livmas.my_collections_app.data.models.requests.DeleteListRequest
import com.livmas.my_collections_app.data.models.requests.GetListContentRequest
import com.livmas.my_collections_app.data.models.responses.BaseResponse
import com.livmas.my_collections_app.data.remote.data_sources.ListRemoteDataSource
import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.domain.models.ShoppingItem
import com.livmas.my_collections_app.domain.repositories.ShopListRepository
import com.livmas.my_collections_app.domain.repositories.UserRepository
import com.livmas.my_collections_app.mappers.toDomain
import com.livmas.my_collections_app.utils.ResourceFlow

class ShopListRepositoryImpl(
    private val listRemoteDataSource: ListRemoteDataSource,
    private val userRepository: UserRepository,
): ShopListRepository, BaseAuthorizedRepository(userRepository) {
    override suspend fun getShopLists(): ResourceFlow<List<ShopListInfo>> {
        return fetchAuthorized { authKey ->
            val response = listRemoteDataSource.getShopLists(authKey)

            check (response.success)
            response.shopList.map { it.toDomain() }
        }
    }

    override suspend fun createShopList(shopListInfo: ShopListInfo): ResourceFlow<ShopListInfo> {
        return fetchAuthorized { authKey ->
            val result = listRemoteDataSource.createShopList(
                authKey = authKey,
                request = CreateListRequest(shopListInfo.name)
            )

            check (result.success)
            shopListInfo.copy(
                id = result.listId
            )
        }
    }

    override suspend fun deleteShopList(shopListInfo: ShopListInfo): ResourceFlow<BaseResponse> {
        return fetchAuthorizedResponse { authKey ->
            val result = listRemoteDataSource.deleteShopList(
                authKey = authKey,
                request = DeleteListRequest(shopListInfo.id)
            )

            check (result.newValue)
            result
        }
    }

    override suspend fun getListContent(listId: Long): ResourceFlow<List<ShoppingItem>> {
        return fetchAuthorized { authKey ->
            val result = listRemoteDataSource.getListContent(authKey, GetListContentRequest(listId))

            check (result.success)
            result.items.map { it.toDomain() }
        }
    }

    override suspend fun deleteListItem(listId: Long, itemId: Long): ResourceFlow<Unit> {
        return fetchAuthorized {
            val result = listRemoteDataSource.deleteListItem(
                authKey = userRepository.getUserKey(),
                request = DeleteListItemRequest(
                    listId, itemId
                )
            )

            check (result.success)
        }
    }

    override suspend fun crossOutListItem(listId: Long, item: ShoppingItem): ResourceFlow<Boolean> {
        return fetchAuthorized {
            listRemoteDataSource.crossItemOut(
                authKey = userRepository.getUserKey(),
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