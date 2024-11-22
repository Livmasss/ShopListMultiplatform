package com.livmas.my_collections_app.data.data_sources

import com.livmas.my_collections_app.data.KtorClient
import com.livmas.my_collections_app.data.models.requests.CreateListRequest
import com.livmas.my_collections_app.data.models.responses.CreateListResponse
import com.livmas.my_collections_app.data.models.responses.GetAllListsResponse
import io.ktor.client.call.body
import io.ktor.client.request.post

class ListRemoteDataSource {
    suspend fun fetchLists(authKey: String): GetAllListsResponse {
        val response = KtorClient.client.post("GetAllMyShopLists?key=$authKey")
        return response.body<GetAllListsResponse>()
    }
    suspend fun createList(authKey: String, request: CreateListRequest): CreateListResponse {
        val response = KtorClient.client.post("GetAllMyShopLists?key=$authKey&name=${request.name}")
        return response.body<CreateListResponse>()
    }
}
