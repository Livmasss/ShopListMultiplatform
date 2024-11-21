package com.livmas.my_collections_app.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.livmas.my_collections_app.presentation.theme.ShopListsTheme
import com.livmas.my_collections_app.utils.ScreenState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initiateData()
    }

    AllLists(state.lists) {

    }
}

@Composable
private fun HomeFrame(
    state: HomeScreenState
) {
    AllLists(state.lists) {

    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ShopListsTheme {
        HomeFrame(
            HomeScreenState(
                ScreenState.SUCCESS,
                listOf(
                    ListInfoModel(
                        id = 0,
                        name = "Collection 1"
                    ),
                    ListInfoModel(
                        id = 2,
                        name = "Collection 2"
                    ),
                    ListInfoModel(
                        id = 3,
                        name = "Collection 3"
                    )
                )
            )
        )
    }
}
