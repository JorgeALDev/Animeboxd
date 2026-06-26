package com.jorge.animeboxd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.jorge.animeboxd.data.local.UserPreferences
import com.jorge.animeboxd.presentation.login.LoginScreen
import com.jorge.animeboxd.presentation.navigation.AppNavGraph
import com.jorge.animeboxd.ui.theme.AnimeboxdTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userPreferences = UserPreferences(applicationContext)

        setContent {
            AnimeboxdTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var isLoggedIn by remember { mutableStateOf(userPreferences.isLoggedIn()) }

                    if (isLoggedIn) {
                        val navController = rememberNavController()
                        AppNavGraph(
                            navController = navController,
                            onLogout = {
                                lifecycleScope.launch {
                                    userPreferences.logout()
                                    isLoggedIn = false
                                }
                            }
                        )
                    } else {
                        LoginScreen(
                            onLoginSuccess = {
                                lifecycleScope.launch {
                                    userPreferences.salvarLogin()
                                    isLoggedIn = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}