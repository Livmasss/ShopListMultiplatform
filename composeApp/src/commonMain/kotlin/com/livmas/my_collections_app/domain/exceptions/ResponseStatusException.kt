package com.livmas.my_collections_app.domain.exceptions

class ResponseStatusException(
    val statusCode: Int,
    message: String?
): RuntimeException(
    message
)