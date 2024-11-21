package com.livmas.my_collections_app.presentation.navigaition

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import com.livmas.my_collections_app.presentation.screens.home.ListInfoModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val ListInfoNavType = object: NavType<ListInfoModel>(false) {
        override fun get(bundle: Bundle, key: String): ListInfoModel? {
            return bundle.getString(key)?.let { Json.decodeFromString(it) }
        }

        override fun parseValue(value: String): ListInfoModel {
            return Json.decodeFromString(value)
        }

        override fun serializeAsValue(value: ListInfoModel): String {
            return Json.encodeToString(value)
        }

        override fun put(bundle: Bundle, key: String, value: ListInfoModel) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}