package com.livmas.my_collections_app.data.local.datasources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.livmas.my_collections_app.data.local.UserScheme.USER_KEY
import com.livmas.my_collections_app.data.local.createDataStore
import kotlinx.coroutines.flow.first

class UserLocalDataSource(
    private val dataStore: DataStore<Preferences> = createDataStore()
) {
    suspend fun saveUserKey(authKey: String) {

        dataStore.edit {
            it[USER_KEY] = authKey
        }
    }
    suspend fun restoreUserKey(): String {
        val pref = dataStore.data.first()
        return pref[USER_KEY] ?: throw IllegalStateException("$USER_KEY cant be restored from UserLocalDataSource")
    }
}
