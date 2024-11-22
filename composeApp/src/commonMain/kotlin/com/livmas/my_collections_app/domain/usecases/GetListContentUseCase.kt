package com.livmas.my_collections_app.domain.usecases

import com.livmas.my_collections_app.domain.models.ShoppingItem
import com.livmas.my_collections_app.domain.repositories.ShopListRepository
import com.livmas.my_collections_app.utils.ResourceFlow

class GetListContentUseCase(
    private val repository: ShopListRepository
) {
    suspend fun execute(listId: Long): ResourceFlow<List<ShoppingItem>> {
        return repository.getListContent(listId)
    }
}