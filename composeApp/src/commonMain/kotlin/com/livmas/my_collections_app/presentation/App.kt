package com.livmas.my_collections_app.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.livmas.my_collections_app.presentation.navigaition.MainRouter
import com.livmas.my_collections_app.presentation.theme.LocalSpacingProvider
import com.livmas.my_collections_app.presentation.theme.ShopListsTheme
import com.livmas.my_collections_app.presentation.theme.Spacing
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    ShopListsTheme {
        KoinContext  {
            CompositionLocalProvider(
                LocalSpacingProvider provides Spacing()
            ) {
                MainRouter()
            }
        }
    }
}
