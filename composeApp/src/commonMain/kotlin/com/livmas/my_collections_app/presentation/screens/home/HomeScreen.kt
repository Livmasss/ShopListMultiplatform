package com.livmas.my_collections_app.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import com.livmas.my_collections_app.presentation.screens.home.create_list.CreateShopListDialog
import com.livmas.my_collections_app.presentation.theme.ShopListsTheme
import com.livmas.my_collections_app.presentation.widgets.AsyncLoadingScaffold
import com.livmas.my_collections_app.presentation.widgets.BackgroundedTopAppBar
import com.livmas.my_collections_app.utils.ScreenState
import mycollectionsapp.composeapp.generated.resources.Res
import mycollectionsapp.composeapp.generated.resources.app_name
import mycollectionsapp.composeapp.generated.resources.ic_create
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject(),
    onShopListClick: (ShopListInfoModel) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    HomeFrame(
        state = state,
        onIntent = { viewModel.onIntent(it) },
        onShopListClick = onShopListClick,
        refreshData = viewModel::initiateData
    )
}

@Composable
private fun HomeFrame(
    state: HomeScreenState,
    onIntent: (HomeScreenIntent) -> Unit,
    onShopListClick: (ShopListInfoModel) -> Unit,
    refreshData: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var showCreateListDialog by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(state.screenState) {
        if (state.screenState == ScreenState.ERROR)
            snackbarHostState.showSnackbar(message = state.error)
    }

    AsyncLoadingScaffold (
        loading = state.screenState == ScreenState.LOADING,
        onLoad = refreshData,
        topBar = {
            HomeScreenAppBar()
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            CreateShopListButton {
                showCreateListDialog = true
            }
        }
    ) {
        HomeScreenContent(
            modifier = Modifier.padding(it)
                .fillMaxSize(),
            state = state,
            onIntent = onIntent,
            onShopListClick = onShopListClick,
            showCreateListDialog = showCreateListDialog,
            onShowCreateListDialogChange = { showCreateListDialog = it }
        )
    }
}

@Composable
private fun HomeScreenAppBar() {
    BackgroundedTopAppBar(
        title = {
            Text(stringResource(Res.string.app_name))
        }
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeScreenState,
    onIntent: (HomeScreenIntent) -> Unit,
    onShopListClick: (ShopListInfoModel) -> Unit,
    showCreateListDialog: Boolean,
    onShowCreateListDialogChange: (Boolean) -> Unit
) {
    AllShopLists(
        modifier = modifier,
        shopLists = state.lists,
        onShopListClick = onShopListClick,
        onDismiss = {
            onIntent(HomeScreenIntent.DeleteShopListIntent(it))
        }
    )

    if (showCreateListDialog)
        CreateShopListDialog(
            onDismissRequest =  {
                onShowCreateListDialogChange(false)
            },
            onIntent = onIntent
        )
}

@Composable
private fun CreateShopListButton(
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
            onIntent = {},
            onShopListClick = {}
        ) {}
    }
}
