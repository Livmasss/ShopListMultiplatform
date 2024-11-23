package com.livmas.my_collections_app.presentation.widgets

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun BackgroundedTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = navigationIcon,
        title = title,
        actions = actions,
        elevation = elevation,
        backgroundColor = MaterialTheme.colors.background
    )
}