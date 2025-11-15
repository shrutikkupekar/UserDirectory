package com.example.userdirectory.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdirectory.data.UserRepository
import com.example.userdirectory.data.local.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(private val repo: UserRepository) : ViewModel() {

    private val query = MutableStateFlow("")

    // Users list observable as StateFlow
    val users: StateFlow<List<UserEntity>> = query
        .flatMapLatest { q -> repo.searchUsers(q) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        // Fetch fresh data but still show cached data instantly
        viewModelScope.launch {
            repo.refresh()
        }
    }

    fun setQuery(q: String) {
        query.value = q
    }
}
