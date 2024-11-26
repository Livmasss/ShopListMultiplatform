package com.livmas.my_collections_app.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
//import platform.Foundation.*

//Not working for me (windows device)
//actual fun createDataStore(context: Any?): DataStore<Preferences> = AppSettings.getDataStore {
//    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
//        directory = NSDocumentDirectory,
//        inDomain = NSUserDomainMask,
//        appropriateForURL = null,
//        create = false,
//        error = null,
//    )
//    requireNotNull(documentDirectory).path + "/$dataStoreFileName"
//}

actual fun createDataStore(context: Any?): DataStore<Preferences> {
    TODO("Not yet implemented (I have only windows device)")
}
