package com.livmas.my_collections_app.data.repositories

import com.livmas.my_collections_app.data.local.datasources.UserLocalDataSource
import com.livmas.my_collections_app.data.remote.data_sources.UserRemoteDataSource
import com.livmas.my_collections_app.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
): UserRepository {
    override suspend fun getUserKey(): String {
        return try {
            localDataSource.restoreUserKey()
        }
        catch (e: Exception) {
            val newKey = remoteDataSource.generateUserKey()

            CoroutineScope(Dispatchers.IO).launch {
                localDataSource.saveUserKey(newKey)
            }
            newKey
        }
    }
}