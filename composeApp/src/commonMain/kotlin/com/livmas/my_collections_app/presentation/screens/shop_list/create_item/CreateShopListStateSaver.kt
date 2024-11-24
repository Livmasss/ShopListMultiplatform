package com.livmas.my_collections_app.presentation.screens.shop_list.create_item

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CreateShoppingItemUiStateSaver: Saver<MutableState<CreateShoppingItemUiState>, String> {
    override fun restore(value: String): MutableState<CreateShoppingItemUiState>? {
        return try {
            mutableStateOf(Json.decodeFromString<CreateShoppingItemUiState>(value))
        }
        catch (e: Exception) {
            null
        }
    }

    override fun SaverScope.save(value: MutableState<CreateShoppingItemUiState>): String? {
        return try {
            Json.encodeToString(value.value)
        }
        catch (e: Exception) {
            null
        }
    }
}