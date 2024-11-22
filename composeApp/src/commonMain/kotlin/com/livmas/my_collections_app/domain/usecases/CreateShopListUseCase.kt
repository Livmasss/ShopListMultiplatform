package com.livmas.my_collections_app.domain.usecases

import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.domain.repositories.ShopListRepository
import com.livmas.my_collections_app.utils.ResourceFlow

class CreateShopListUseCase(
    private val repository: ShopListRepository
) {
//    It is better to use another for this use case instead of ShopListInfo. It will allow customization of this protocol and segregate it from domain model
    suspend fun execute(shopListInfo: ShopListInfo): ResourceFlow<ShopListInfo> {
        return repository.createList(shopListInfo)
    }
}