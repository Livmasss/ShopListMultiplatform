package com.livmas.my_collections_app.data.data_sources

import com.livmas.my_collections_app.data.KtorClient
import com.livmas.my_collections_app.data.models.responses.GetAllListsResponseBody
import io.ktor.client.call.body
import io.ktor.client.request.post

class ListRemoteDataSource {
    suspend fun fetchLists(key: String): GetAllListsResponseBody {
        val response = KtorClient.client.post("GetAllMyShopLists?key=$key")
        return response.body<GetAllListsResponseBody>()
    }
}