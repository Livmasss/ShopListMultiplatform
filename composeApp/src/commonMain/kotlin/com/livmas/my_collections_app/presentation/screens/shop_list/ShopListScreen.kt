package com.livmas.my_collections_app.presentation.screens.shop_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import org.koin.compose.koinInject

@Composable
fun ShopListScreen(
    shopListInfoModel: ShopListInfoModel,
    viewModel: ShopListViewModel = koinInject()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initiateData(shopListInfoModel)
    }

    ShopListFrame(state)
}

@Composable
private fun ShopListFrame(
    uiState: ShopListScreenState
) {
    Scaffold {
        uiState.listInfoModel?.let { listInfo ->
            ShopListScreenContent(
                modifier = Modifier.padding(it),
                shopListInfoModel = listInfo
            )
        }
    }
}

@Composable
private fun ShopListScreenContent(
    modifier: Modifier = Modifier,
    shopListInfoModel: ShopListInfoModel
) {
    Column(modifier) {
        Text(shopListInfoModel.name)
    }
}
