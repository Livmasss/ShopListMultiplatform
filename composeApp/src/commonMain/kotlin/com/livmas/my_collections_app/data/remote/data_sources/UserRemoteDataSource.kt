package com.livmas.my_collections_app.data.remote.data_sources

import co.touchlab.kermit.Logger

class UserRemoteDataSource {
    fun generateUserKey(): String {
        Logger.d { "Auth key generation" }
        return "QZAJAW"
    }
}