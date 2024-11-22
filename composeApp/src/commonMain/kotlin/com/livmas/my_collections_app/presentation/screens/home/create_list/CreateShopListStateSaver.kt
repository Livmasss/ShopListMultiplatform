package com.livmas.my_collections_app.presentation.screens.home.create_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CreateShopListStateSaver: Saver<MutableState<CreateShopListState>, String> {
    override fun restore(value: String): MutableState<CreateShopListState>? {
        return try {
            mutableStateOf(Json.decodeFromString<CreateShopListState>(value))
        }
        catch (e: Exception) {
            null
        }
    }

    override fun SaverScope.save(value: MutableState<CreateShopListState>): String? {
        return try {
            Json.encodeToString(value.value)
        }
        catch (e: Exception) {
            null
        }
    }
}