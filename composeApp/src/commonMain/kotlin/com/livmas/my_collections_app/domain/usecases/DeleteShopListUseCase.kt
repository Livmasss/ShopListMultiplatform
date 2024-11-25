package com.livmas.my_collections_app.domain.usecases

import com.livmas.my_collections_app.data.models.responses.BaseResponse
import com.livmas.my_collections_app.domain.models.ShopListInfo
import com.livmas.my_collections_app.domain.repositories.ShopListRepository
import com.livmas.my_collections_app.utils.ResourceFlow

class DeleteShopListUseCase(
    private val repository: ShopListRepository
) {
    suspend fun execute(shopListInfo: ShopListInfo): ResourceFlow<BaseResponse> {
        return repository.deleteShopList(shopListInfo)
    }
}