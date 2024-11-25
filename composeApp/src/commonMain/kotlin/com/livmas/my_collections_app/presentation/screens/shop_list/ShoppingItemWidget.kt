package com.livmas.my_collections_app.presentation.screens.shop_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.livmas.my_collections_app.presentation.models.RevealedActionModel
import com.livmas.my_collections_app.presentation.models.ShoppingItemModel
import com.livmas.my_collections_app.presentation.widgets.RevealedActionWidget
import com.livmas.my_collections_app.presentation.widgets.SwipeToRevealItemWidget
import mycollectionsapp.composeapp.generated.resources.Res
import mycollectionsapp.composeapp.generated.resources.ic_cross_out
import mycollectionsapp.composeapp.generated.resources.ic_delete

@Composable
fun ShoppingItemsList(
    modifier: Modifier = Modifier,
    shopItems: List<ShoppingItemModel>,
    onIntent: (ShopListScreenIntent) -> Unit
) {
    LazyColumn(modifier) {
        items(
            count = shopItems.size,
            key = {
                shopItems[it].id
            }
        ) { index ->
            val model = shopItems[index]

            if (index > 0)
                Divider()

            ShoppingItemWidget(
                model = model,
                onIntent = onIntent
            )
        }
    }
}

@Composable
private fun ShoppingItemWidget(
    modifier: Modifier = Modifier,
    model: ShoppingItemModel,
    onIntent: (ShopListScreenIntent) -> Unit
) {
    var isRevealed by rememberSaveable { mutableStateOf(false) }

    SwipeToRevealItemWidget(
        modifier = Modifier.then(modifier)
            .height(50.dp),
        isRevealed = isRevealed,
        onExpanded = { isRevealed = true },
        onCollapsed = { isRevealed = false },
        actions = {
            getActionModels(
                model = model,
                onIntent = onIntent
            ).forEach {
                RevealedActionWidget(
                    actionModel = it
                )
            }
        },
        content = {
            Text(
                modifier = Modifier.weight(1f),
                text = model.text,
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        postfix = {
            Text(
                text = model.count.toString(),
                style = MaterialTheme.typography.body1,
                maxLines = 1
            )
        },
        decoration = {
            if (model.isCrossed)
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.Black)
                    )
                }
        }
    )
}

@Composable
private fun getActionModels(
    model: ShoppingItemModel,
    onIntent: (ShopListScreenIntent) -> Unit
) = listOf(
    RevealedActionModel(
        backgroundColor = Color.Red,
        foregroundColor = MaterialTheme.colors.surface,
        iconDrawable = Res.drawable.ic_delete,
        callback = {
            onIntent(ShopListScreenIntent.DeleteShoppingItem(model))
        }
    ),
    RevealedActionModel(
        backgroundColor = Color.Black,
        foregroundColor = MaterialTheme.colors.surface,
        iconDrawable = Res.drawable.ic_cross_out,
        callback = {
            onIntent(ShopListScreenIntent.CrossShoppingItemOut(model))
        }
    ),
)
