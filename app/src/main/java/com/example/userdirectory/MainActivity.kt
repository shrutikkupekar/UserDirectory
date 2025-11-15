package com.example.userdirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.userdirectory.ui.UserListScreen
import com.example.userdirectory.ui.UserViewModel
import com.example.userdirectory.ui.UserViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                // Collect the flow into Compose state
                val users = viewModel.users.collectAsStateWithLifecycle().value

                Surface {
                    UserListScreen(
                        users = users,
                        onQueryChange = viewModel::setQuery
                    )
                }
            }
        }
    }
}
