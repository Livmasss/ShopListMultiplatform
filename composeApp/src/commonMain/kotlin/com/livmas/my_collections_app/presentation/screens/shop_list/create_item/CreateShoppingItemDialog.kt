package com.livmas.my_collections_app.presentation.screens.shop_list.create_item

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.livmas.my_collections_app.presentation.models.ShoppingItemModel
import com.livmas.my_collections_app.presentation.screens.shop_list.ShopListScreenIntent
import com.livmas.my_collections_app.presentation.widgets.BaseIntField
import com.livmas.my_collections_app.presentation.widgets.BaseTextField
import com.livmas.my_collections_app.utils.BaseDialog
import mycollectionsapp.composeapp.generated.resources.Res
import mycollectionsapp.composeapp.generated.resources.label_count
import mycollectionsapp.composeapp.generated.resources.label_create
import mycollectionsapp.composeapp.generated.resources.label_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun CreateShoppingItemDialog(
    onDismissRequest: () -> Unit,
    onIntent: (ShopListScreenIntent.CreateShoppingItem) -> Unit
) {
    var uiState by rememberSaveable(
        saver = CreateShoppingItemUiStateSaver()
    ) { mutableStateOf(CreateShoppingItemUiState()) }

    BaseDialog(
        onDismissRequest = onDismissRequest
    ) {
        BaseTextField(
            value = uiState.text,
            onValueChange = { value ->
                uiState = uiState.copy(text = value)
            },
            labelResource = Res.string.label_name
        )
        BaseIntField(
            value = uiState.count,
            onValueChange = { value ->
                uiState = uiState.copy(
                    count = value
                )
            },
            labelResource = Res.string.label_count
        )
        Button(
            onClick = {
                onIntent(ShopListScreenIntent.CreateShoppingItem(
                    itemModel = ShoppingItemModel(
                        id = 0,
                        text = uiState.text,
                        count = uiState.count ?: 0,
                        isCrossed = false
                    ),
                    onSuccess = onDismissRequest
                ))
            }
        ) {
            Text(stringResource(Res.string.label_create))
        }
    }
}