package com.livmas.my_collections_app.presentation.screens.home.create_list

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.livmas.my_collections_app.presentation.screens.home.HomeScreenIntent
import com.livmas.my_collections_app.presentation.widgets.BaseTextField
import com.livmas.my_collections_app.utils.BaseDialog
import mycollectionsapp.composeapp.generated.resources.Res
import mycollectionsapp.composeapp.generated.resources.label_create
import mycollectionsapp.composeapp.generated.resources.label_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun CreateShopListDialog(
    onDismissRequest: () -> Unit,
    onIntent: (HomeScreenIntent) -> Unit
) {
    var uiState by rememberSaveable(
        saver = CreateShopListStateSaver()
    ) { mutableStateOf(CreateShopListState()) }

    BaseDialog(
        onDismissRequest = onDismissRequest
    ) {
        BaseTextField(
            value = uiState.name,
            onValueChange = {
                uiState = uiState.copy(
                    name = it
                )
            },
            labelResource = Res.string.label_name
        )

        Button(
            onClick = {
                onIntent(HomeScreenIntent.CreateShopListIntent(
                    state = uiState,
                    onSuccess = onDismissRequest
                ))
            }
        ) {
            Text(stringResource(Res.string.label_create))
        }
    }
}