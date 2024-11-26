package com.livmas.my_collections_app

import androidx.compose.ui.window.ComposeUIViewController
import com.livmas.my_collections_app.di.initializeKoin
import com.livmas.my_collections_app.presentation.App

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) { App() }