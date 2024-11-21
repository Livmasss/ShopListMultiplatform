package com.livmas.my_collections_app.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.livmas.my_collections_app.presentation.theme.LocalSpacingProvider
import com.livmas.my_collections_app.presentation.theme.Spacing
import com.livmas.my_collections_app.di.appModule
import com.livmas.my_collections_app.presentation.screens.home.HomeScreen
import com.livmas.my_collections_app.presentation.theme.ShopListsTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule())
    }) {
        CompositionLocalProvider(
            LocalSpacingProvider provides Spacing()
        ) {
            ShopListsTheme {
                HomeScreen()
            }
        }
    }
}