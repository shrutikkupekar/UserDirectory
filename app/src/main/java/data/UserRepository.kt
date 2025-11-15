package com.example.userdirectory.data

import android.util.Log
import com.example.userdirectory.data.local.UserDao
import com.example.userdirectory.data.local.UserEntity
import com.example.userdirectory.data.remote.JsonPlaceholderApi
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val api: JsonPlaceholderApi,
    private val dao: UserDao
) {

    // UI always reads from Room
    fun users(): Flow<List<UserEntity>> = dao.observeAll()

    fun searchUsers(query: String): Flow<List<UserEntity>> {
        return if (query.isBlank()) {
            dao.observeAll()
        } else {
            dao.search("%${query.trim()}%")
        }
    }

    // Offline-first data refresh
    suspend fun refresh() {
        runCatching {
            val remote = api.getUsers()
            val entities = remote.map { it.asEntity() }
            dao.upsertAll(entities)
        }.onFailure { e ->
            Log.w("UserRepository", "Refresh failed, using cached data", e)
        }
    }
}
