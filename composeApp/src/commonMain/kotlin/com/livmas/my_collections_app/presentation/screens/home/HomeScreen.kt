package com.livmas.my_collections_app.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.livmas.my_collections_app.presentation.theme.ShopListsTheme
import com.livmas.my_collections_app.utils.ScreenState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject(),
    onShopListClick: (ShopListInfoModel) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initiateData()
    }
    HomeFrame(
        state = state,
        onIntent = { viewModel.onIntent(it) },
        onShopListClick = onShopListClick
    )
}

@Composable
private fun HomeFrame(
    state: HomeScreenState,
    onIntent: (HomeScreenIntent) -> Unit,
    onShopListClick: (ShopListInfoModel) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            CreateShopListButton(onIntent)
        }
    ) {
        AllShopLists(
            modifier = Modifier.padding(it),
            shopLists =  state.lists,
            onShopListClick = onShopListClick
        )
    }
}

@Composable
private fun CreateShopListButton(
    onIntent: (HomeScreenIntent) -> Unit
) {
    FloatingActionButton(
        onClick = {
//            onIntent(HomeScreenIntent.CreateShopListIntent())
        }
    ) {

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
                    ShopListInfoModel(
                        id = 0,
                        name = "Collection 1"
                    ),
                    ShopListInfoModel(
                        id = 2,
                        name = "Collection 2"
                    ),
                    ShopListInfoModel(
                        id = 3,
                        name = "Collection 3"
                    )
                )
            ),
            onIntent = {}
        ) {}
    }
}
