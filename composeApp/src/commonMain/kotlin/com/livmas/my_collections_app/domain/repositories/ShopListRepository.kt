package com.livmas.my_collections_app.domain.repositories

import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.domain.models.ShoppingItem
import com.livmas.my_collections_app.utils.ResourceFlow

interface ShopListRepository {
    suspend fun getLists(): ResourceFlow<List<ShopListInfo>>
    suspend fun createList(shopListInfo: ShopListInfo): ResourceFlow<ShopListInfo>
    suspend fun deleteList(shopListInfo: ShopListInfo): ResourceFlow<Unit>

    suspend fun getListContent(listId: Long): ResourceFlow<List<ShoppingItem>>
}
