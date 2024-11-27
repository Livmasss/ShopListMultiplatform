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
import com.livmas.my_collections_app.presentation.widgets.IntField
import com.livmas.my_collections_app.presentation.widgets.MyTextField
import com.livmas.my_collections_app.utils.BaseDialog
import com.livmas.my_collections_app.utils.removeExtraSpaces
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
    var dialogState by rememberSaveable(
        saver = CreateShoppingItemUiStateSaver()
    ) { mutableStateOf(CreateShoppingItemUiState()) }

    BaseDialog(
        onDismissRequest = onDismissRequest
    ) {
        MyTextField(
            value = dialogState.text,
            onValueChange = { value ->
                dialogState = dialogState.copy(text = value)
            },
            labelResource = Res.string.label_name
        )
        IntField(
            value = dialogState.count,
            onValueChange = { value ->
                dialogState = dialogState.copy(
                    count = value
                )
            },
            labelResource = Res.string.label_count
        )
        Button(
            enabled = dialogState.text.isNotBlank() && dialogState.count != null,
            onClick = {
                onIntent(ShopListScreenIntent.CreateShoppingItem(
                    itemModel = ShoppingItemModel(
                        id = 0,
                        text = dialogState.text.removeExtraSpaces(),
                        count = dialogState.count ?: 0,
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
