package com.example.userdirectory.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY name")
    fun observeAll(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE name LIKE :q OR email LIKE :q ORDER BY name")
    fun search(q: String): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(users: List<UserEntity>)
}
