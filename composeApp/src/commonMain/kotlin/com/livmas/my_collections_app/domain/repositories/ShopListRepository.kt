package com.livmas.my_collections_app.domain.repositories

import com.livmas.my_collections_app.data.models.responses.BaseResponse
import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.domain.models.ShoppingItem
import com.livmas.my_collections_app.utils.ResourceFlow

interface ShopListRepository {
    suspend fun getShopLists(): ResourceFlow<List<ShopListInfo>>
    suspend fun createShopList(shopListInfo: ShopListInfo): ResourceFlow<ShopListInfo>
    suspend fun deleteShopList(shopListInfo: ShopListInfo): ResourceFlow<BaseResponse>

    suspend fun getListContent(listId: Long): ResourceFlow<List<ShoppingItem>>
    suspend fun deleteListItem(listId: Long, itemId: Long): ResourceFlow<Unit>
    suspend fun crossOutListItem(listId: Long, item: ShoppingItem): ResourceFlow<Boolean>
    suspend fun createListItem(listId: Long, item: ShoppingItem): ResourceFlow<ShoppingItem>
}
