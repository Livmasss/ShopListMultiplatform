package com.livmas.my_collections_app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.livmas.my_collections_app.di.appModule
import com.livmas.my_collections_app.presentation.screens.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule())
    }) {
        MaterialTheme {
            HomeScreen()
        }
    }
}