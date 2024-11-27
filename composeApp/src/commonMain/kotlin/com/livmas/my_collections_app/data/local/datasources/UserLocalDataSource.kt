package com.livmas.my_collections_app.data.local.datasources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import co.touchlab.kermit.Logger
import com.livmas.my_collections_app.data.local.UserScheme.USER_KEY
import com.livmas.my_collections_app.data.local.createDataStore
import kotlinx.coroutines.flow.first

class UserLocalDataSource(
    private val dataStore: DataStore<Preferences> = createDataStore()
) {
//    It should be encrypted to be more secure
    suspend fun saveUserKey(authKey: String) {
        Logger.d { "Auth key saving: $authKey" }
        dataStore.edit {
            it[USER_KEY] = authKey
        }
    }

    suspend fun restoreUserKey(): String {
        val pref = dataStore.data.first()
        val authKey = pref[USER_KEY]
        Logger.d { "Auth key restored: $authKey" }
        return authKey ?: throw IllegalStateException("$USER_KEY cant be restored from UserLocalDataSource")

    }
}
