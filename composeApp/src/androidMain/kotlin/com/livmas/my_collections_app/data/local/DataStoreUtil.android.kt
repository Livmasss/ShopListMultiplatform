package com.livmas.my_collections_app.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual fun createDataStore(context: Any?): DataStore<Preferences> {
    require(context is Context) {
        "Android Context required!"
    }

    return AppSettings.getDataStore {
        context.filesDir
            .resolve(DATASTORE_FILE_NAME)
            .absolutePath
    }
}