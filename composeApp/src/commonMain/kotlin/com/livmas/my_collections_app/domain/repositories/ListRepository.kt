package com.livmas.my_collections_app.domain.repositories

import com.livmas.my_collections_app.domain.models.ListInfo
import com.livmas.my_collections_app.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    suspend fun getLists(): Flow<Resource<List<ListInfo>>>
}