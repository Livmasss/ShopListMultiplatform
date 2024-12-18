package com.livmas.my_collections_app.presentation.screens.shop_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import com.livmas.my_collections_app.presentation.screens.shop_list.create_item.CreateShoppingItemDialog
import com.livmas.my_collections_app.presentation.theme.spacing
import com.livmas.my_collections_app.presentation.widgets.AsyncLoadingScaffold
import com.livmas.my_collections_app.presentation.widgets.BackgroundedTopAppBar
import com.livmas.my_collections_app.utils.ScreenState
import mycollectionsapp.composeapp.generated.resources.Res
import mycollectionsapp.composeapp.generated.resources.ic_back
import mycollectionsapp.composeapp.generated.resources.ic_create
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@Composable
fun ShopListScreen(
    shopListInfoModel: ShopListInfoModel,
    onBackClick: () -> Unit,
    viewModel: ShopListViewModel = koinInject()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val onIntent: (ShopListScreenIntent) -> Unit = { viewModel.onIntent(it) }

    LaunchedEffect(Unit) {
        viewModel.initiateData(shopListInfoModel)
    }

    ShopListFrame(
        uiState = state,
        onIntent = onIntent,
        onBackClick = onBackClick
    )
}

@Composable
private fun ShopListFrame(
    uiState: ShopListScreenState,
    onIntent: (ShopListScreenIntent) -> Unit,
    onBackClick: () -> Unit
) {
    var showCreateDialog by rememberSaveable {
        mutableStateOf(false)
    }

    if (showCreateDialog)
        CreateShoppingItemDialog(
            onDismissRequest = {
                showCreateDialog = false
            },
            onIntent = onIntent
        )

    AsyncLoadingScaffold(
        loading = uiState.screenState == ScreenState.LOADING,
        onRefresh = { onIntent(ShopListScreenIntent.RefreshScreen) },
        topBar = {
            uiState.listInfoModel?.let {
                ShopListScreenTopBar(
                    shopListInfoModel = it,
                    onBackClick = onBackClick
                )
            }
        },
        floatingActionButton = {
            CreateShoppingItemFab {
                showCreateDialog = true
            }
        }
    ) {
        ShopListScreenContent(
            modifier = Modifier.padding(it)
                .padding(MaterialTheme.spacing.screenPadding),
            state = uiState,
            onIntent = onIntent
        )
    }
}

@Composable
private fun ShopListScreenTopBar(
    shopListInfoModel: ShopListInfoModel,
    onBackClick: () -> Unit
) {
    BackgroundedTopAppBar(
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
    )
}

@Composable
private fun ShopListScreenContent(
    modifier: Modifier = Modifier,
    state: ShopListScreenState,
    onIntent: (ShopListScreenIntent) -> Unit
) {
    Box(modifier) {
        ShoppingItemsList(
            modifier = Modifier.fillMaxSize(),
            shopItems = state.listContent,
            onIntent = onIntent
        )
    }
}


@Composable
private fun CreateShoppingItemFab(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_create),
            contentDescription = null
        )
    }
}
