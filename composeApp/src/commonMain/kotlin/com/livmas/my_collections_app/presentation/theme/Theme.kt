package com.livmas.my_collections_app.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun ShopListsTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalSpacingProvider provides Spacing()
    ) {
        MaterialTheme {
            content()
        }
    }
}
