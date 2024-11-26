package com.livmas.my_collections_app.data.remote.data_sources

import co.touchlab.kermit.Logger
import com.livmas.my_collections_app.data.remote.KtorClient
import io.ktor.client.request.post

class UserRemoteDataSource: BaseRemoteDataSource() {
    suspend fun generateUserKey(): String {
        Logger.d { "Auth key generation" }
        val response = KtorClient.client.post("CreateTestKey")
        return response.handleBody<String>()
    }
}