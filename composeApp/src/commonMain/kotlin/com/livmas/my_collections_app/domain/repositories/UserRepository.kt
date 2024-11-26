package com.livmas.my_collections_app.domain.repositories

interface UserRepository {
    suspend fun getUserKey(): String
}