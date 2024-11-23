package com.livmas.my_collections_app.presentation.screens.shop_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.livmas.my_collections_app.presentation.models.ShoppingItemModel
import com.livmas.my_collections_app.presentation.widgets.SwipeToRevealItemWidget

@Composable
fun ShoppingItemsList(
    modifier: Modifier = Modifier,
    shopItems: List<ShoppingItemModel>
) {
    LazyColumn(modifier) {
        items(
            count = shopItems.size,
            key = {
                shopItems[it].id
            }
        ) { index ->
            val model = shopItems[index]
            ShoppingItemWidget(
                model = model
            )
        }
    }
}

@Composable
private fun ShoppingItemWidget(
    modifier: Modifier = Modifier,
    model: ShoppingItemModel
) {
    var isRevealed by rememberSaveable { mutableStateOf(false) }

    SwipeToRevealItemWidget(
        isRevealed = isRevealed,
        actions = { Text("Penis") },
        onExpanded = { isRevealed = true },
        onCollapsed = { isRevealed = false },
        modifier = modifier,
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
                    Modifier.height(1.dp)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.onSurface)
                )
        }
    )
}
