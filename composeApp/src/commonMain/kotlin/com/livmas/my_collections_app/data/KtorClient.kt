package com.livmas.my_collections_app.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    private const val BASE_URL = "https://cyberprot.ru/shopping/v2/"
    val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json { ignoreUnknownKeys = true }
            )
        }
        defaultRequest {
            url(BASE_URL)
        }
    }
}