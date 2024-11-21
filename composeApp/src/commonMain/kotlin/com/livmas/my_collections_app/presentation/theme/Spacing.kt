package com.livmas.my_collections_app.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

internal class Spacing {
    val extraSmallSpacing = 4.dp
    val smallSpacing = 8.dp
    val mediumSpacing = 16.dp
    val largeSpacing = 24.dp
    val extraLargeSpacing = 32.dp

    val screenPadding = mediumSpacing
}

internal val LocalSpacingProvider = compositionLocalOf {
    Spacing()
}

internal val MaterialTheme.spacing: Spacing
    @Composable
    get() = LocalSpacingProvider.current
