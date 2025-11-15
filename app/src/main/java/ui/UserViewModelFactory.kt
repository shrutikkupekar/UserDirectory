package com.example.userdirectory.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.userdirectory.di.ServiceLocator

class UserViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repo = ServiceLocator.repository(appContext)
        return UserViewModel(repo) as T
    }
}
