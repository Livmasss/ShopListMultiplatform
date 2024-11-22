package com.livmas.my_collections_app.domain.repositories

import com.livmas.my_collections_app.domain.models.ListInfo
import com.livmas.my_collections_app.utils.Resource
import com.livmas.my_collections_app.utils.ResourceFlow
import kotlinx.coroutines.flow.Flow

interface ShopListRepository {
    suspend fun getLists(): ResourceFlow<List<ListInfo>>
    suspend fun createList(listInfo: ListInfo): ResourceFlow<ListInfo>
    suspend fun deleteList(listInfo: ListInfo): ResourceFlow<ListInfo>
}
