package com.livmas.my_collections_app.presentation.screens.home.create_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.livmas.my_collections_app.presentation.screens.home.HomeScreenIntent
import com.livmas.my_collections_app.presentation.theme.spacing
import mycollectionsapp.composeapp.generated.resources.Res
import mycollectionsapp.composeapp.generated.resources.label_create
import org.jetbrains.compose.resources.stringResource

@Composable
fun CreateShopListDialog(
    onDismissRequest: () -> Unit,
    onIntent: (HomeScreenIntent) -> Unit
) {
    var uiState by rememberSaveable(
        saver = CreateShopListStateSaver()
    ) { mutableStateOf(CreateShopListState()) }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Surface {
            Column(
                Modifier.padding(MaterialTheme.spacing.screenPadding)
            ) {
                TextField(
                    value = uiState.name,
                    onValueChange = { value ->
                        uiState = uiState.copy(name = value)
                    }
                )
                Button(
                    onClick = {
                        onIntent(HomeScreenIntent.CreateShopListIntent(
                            state = uiState
                        ))
                    }
                ) {
                    Text(stringResource(Res.string.label_create))
                }
            }
        }
    }
}