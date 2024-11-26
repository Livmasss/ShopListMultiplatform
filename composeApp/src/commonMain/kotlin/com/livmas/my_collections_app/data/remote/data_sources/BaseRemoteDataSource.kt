package com.livmas.my_collections_app.data.remote.data_sources

import com.livmas.my_collections_app.data.models.responses.BaseResponse
import com.livmas.my_collections_app.domain.exceptions.ResponseStatusException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

abstract class BaseRemoteDataSource {
    suspend inline fun <reified T> HttpResponse.handleBody(): T {
        if (!this.status.isSuccess())
            throw ResponseStatusException(
                statusCode = this.status.value,
                message = this.status.description
            )
        val body = body<T>()

        return body
    }

    suspend inline fun <reified T: BaseResponse> HttpResponse.handleBaseBody(): T {
        if (!this.status.isSuccess())
            throw ResponseStatusException(
                statusCode = this.status.value,
                message = this.status.description
            )
        val body = body<T>()

        check (body.success) { "Response now success: $body" }
        return body
    }
}