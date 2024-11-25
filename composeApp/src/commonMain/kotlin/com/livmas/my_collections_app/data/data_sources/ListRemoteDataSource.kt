package com.livmas.my_collections_app.data.data_sources

import com.livmas.my_collections_app.data.KtorClient
import com.livmas.my_collections_app.data.models.requests.CreateListItemRequest
import com.livmas.my_collections_app.data.models.requests.CreateListRequest
import com.livmas.my_collections_app.data.models.requests.CrossItemOutRequest
import com.livmas.my_collections_app.data.models.requests.DeleteListItemRequest
import com.livmas.my_collections_app.data.models.requests.DeleteListRequest
import com.livmas.my_collections_app.data.models.requests.GetListContentRequest
import com.livmas.my_collections_app.data.models.responses.CreateListItemResponse
import com.livmas.my_collections_app.data.models.responses.CreateListResponse
import com.livmas.my_collections_app.data.models.responses.CrossItemOutResponse
import com.livmas.my_collections_app.data.models.responses.DeleteListItemResponse
import com.livmas.my_collections_app.data.models.responses.DeleteShopListResponse
import com.livmas.my_collections_app.data.models.responses.GetAllListsResponse
import com.livmas.my_collections_app.data.models.responses.GetListContentResponse
import io.ktor.client.call.body
import io.ktor.client.request.post

class ListRemoteDataSource {
    suspend fun getShopLists(authKey: String): GetAllListsResponse {
        val response = KtorClient.client.post("GetAllMyShopLists?key=$authKey")
        return response.body<GetAllListsResponse>()
    }

    suspend fun createShopList(authKey: String, request: CreateListRequest): CreateListResponse {
        val response = KtorClient.client.post("CreateShoppingList?key=$authKey&name=${request.name}")
        return response.body<CreateListResponse>()
    }

    suspend fun deleteShopList(authKey: String, request: DeleteListRequest): DeleteShopListResponse {
        val response = KtorClient.client.post( "RemoveShoppingList?key=$authKey&list_id=${request.listId}")
        return response.body<DeleteShopListResponse>()
    }

    suspend fun getListContent(authKey: String, request: GetListContentRequest): GetListContentResponse {
        val response = KtorClient.client.post("GetShoppingList?key=$authKey&list_id=${request.listId}")
        return response.body<GetListContentResponse>()
    }

    suspend fun crossItemOut(authKey: String, request: CrossItemOutRequest): CrossItemOutResponse {
        val response = KtorClient.client.post("CrossItOff?key=$authKey&list_id=${request.listId}&id=${request.itemId}")
        return response.body<CrossItemOutResponse>()
    }

    suspend fun deleteListItem(authKey: String, request: DeleteListItemRequest): DeleteListItemResponse {
        val response = KtorClient.client.post("RemoveFromList?key=$authKey&list_id=${request.listId}&item_id=${request.itemId}")
        return response.body<DeleteListItemResponse>()
    }

    suspend fun createListItem(authKey: String, request: CreateListItemRequest): CreateListItemResponse {
        val response = KtorClient.client.post("AddToShoppingList?key=$authKey&id=${request.listId}&value=${request.text}&n=${request.count}")
        return response.body<CreateListItemResponse>()
    }
}
