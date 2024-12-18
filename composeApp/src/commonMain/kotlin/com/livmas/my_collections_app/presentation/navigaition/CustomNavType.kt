package com.livmas.my_collections_app.presentation.navigaition

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val ListInfoNavType = object: NavType<ShopListInfoModel>(false) {
        override fun get(bundle: Bundle, key: String): ShopListInfoModel? {
            return bundle.getString(key)?.let { Json.decodeFromString(it) }
        }

        override fun parseValue(value: String): ShopListInfoModel {
            return Json.decodeFromString(value)
        }

        override fun serializeAsValue(value: ShopListInfoModel): String {
            return Json.encodeToString(value)
        }

        override fun put(bundle: Bundle, key: String, value: ShopListInfoModel) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}