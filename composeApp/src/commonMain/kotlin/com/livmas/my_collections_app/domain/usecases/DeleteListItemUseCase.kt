package com.livmas.my_collections_app.domain.usecases

import com.livmas.my_collections_app.domain.repositories.ShopListRepository
import com.livmas.my_collections_app.utils.ResourceFlow

class DeleteListItemUseCase(
    private val repository: ShopListRepository
) {
    suspend fun execute(listId: Long, itemId: Long): ResourceFlow<Unit> {
        return repository.deleteListItem(listId, itemId)
    }
}