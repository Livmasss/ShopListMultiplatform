package com.livmas.my_collections_app.presentation.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject()
) {
    LaunchedEffect(Unit) {
        viewModel.initiateData()
    }

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LazyColumn {
        items(
            count = state.lists.size,
            key = { state.lists[it].id }
        ) {
            Text(state.lists[it].name)
        }
    }
}
