package com.example.userdirectory.di

import android.content.Context
import androidx.room.Room
import com.example.userdirectory.data.UserRepository
import com.example.userdirectory.data.local.AppDatabase
import com.example.userdirectory.data.remote.JsonPlaceholderApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object ServiceLocator {

    @Volatile private var db: AppDatabase? = null
    @Volatile private var repo: UserRepository? = null

    fun repository(context: Context): UserRepository {
        return repo ?: synchronized(this) {
            repo ?: buildRepository(context).also { repo = it }
        }
    }

    private fun buildRepository(context: Context): UserRepository {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())     // 👈 IMPORTANT
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")   // 👈 include trailing slash
            .addConverterFactory(MoshiConverterFactory.create(moshi))  // 👈 use our moshi
            .client(client)
            .build()

        val api = retrofit.create(JsonPlaceholderApi::class.java)

        val database = db ?: Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "user_db"
        ).fallbackToDestructiveMigration().build().also { db = it }

        return UserRepository(api, database.userDao())
    }
}
