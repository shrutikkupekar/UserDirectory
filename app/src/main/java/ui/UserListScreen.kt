package com.example.userdirectory.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.userdirectory.data.local.UserEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(

    users: List<UserEntity>,
    onQueryChange: (String) -> Unit
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("User Directory") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    onQueryChange(it.text)
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Search by name or email") }
            )

            if (users.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No users found (try refreshing or check internet).")
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(users) { user ->
                        UserRow(user)
                    }
                }
            }
        }
    }
}

@Composable
private fun UserRow(user: UserEntity) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "#${user.id}  •  ${user.name}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
            Text(text = user.phone, style = MaterialTheme.typography.bodySmall)
        }
    }
}
