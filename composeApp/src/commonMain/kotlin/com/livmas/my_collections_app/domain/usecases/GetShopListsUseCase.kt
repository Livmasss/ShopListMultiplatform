package com.livmas.my_collections_app.domain.usecases

import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.domain.repositories.ShopListRepository
import com.livmas.my_collections_app.utils.ResourceFlow

class GetShopListsUseCase(
    private val repository: ShopListRepository
) {
    suspend fun execute(): ResourceFlow<List<ShopListInfo>> {
        return repository.getLists()
    }
}