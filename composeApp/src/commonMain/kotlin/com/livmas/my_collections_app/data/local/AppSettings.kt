package com.livmas.my_collections_app.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import okio.Path.Companion.toPath

const val DATASTORE_FILE_NAME = "collection_user.preferences_pb"
object AppSettings {
    private val mutex = SynchronizedObject()
    private var _dataStore: DataStore<Preferences>? = null

    fun getDataStore(pathProvider: () -> String): DataStore<Preferences> {
        return _dataStore ?: synchronized(mutex) {
            return@synchronized PreferenceDataStoreFactory.createWithPath {
                pathProvider.invoke().toPath()
            }
        }
    }
}
