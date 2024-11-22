package com.livmas.my_collections_app.presentation.screens.shop_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import com.livmas.my_collections_app.presentation.theme.spacing
import mycollectionsapp.composeapp.generated.resources.Res
import mycollectionsapp.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@Composable
fun ShopListScreen(
    shopListInfoModel: ShopListInfoModel,
    onBackClick: () -> Unit,
    viewModel: ShopListViewModel = koinInject()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initiateData(shopListInfoModel)
    }

    ShopListFrame(
        uiState = state,
        onBackClick = onBackClick
    )
}

@Composable
private fun ShopListFrame(
    uiState: ShopListScreenState,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            uiState.listInfoModel?.let {
                ShopListScreenTopBar(
                    shopListInfoModel = it,
                    onBackClick = onBackClick
                )
            }
        }
    ) {
        ShopListScreenContent(
            modifier = Modifier.padding(it)
                .padding(MaterialTheme.spacing.screenPadding),
            state = uiState
        )
    }
}

@Composable
private fun ShopListScreenTopBar(
    shopListInfoModel: ShopListInfoModel,
    onBackClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = null
                )
            }
        },
        title = {
            Text(shopListInfoModel.name)
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
private fun ShopListScreenContent(
    modifier: Modifier = Modifier,
    state: ShopListScreenState
) {
    Column(modifier) {
        ShoppingItemsList(
            shopItems = state.listContent
        )
    }
}
