package com.livmas.my_collections_app.presentation.screens.shop_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.livmas.my_collections_app.presentation.models.ShoppingItemModel

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
    Row(
        Modifier.then(modifier)
    ) {
        Text("${model.text} - ${model.count}")
        if (model.isCrossed)
            Box(
                Modifier.height(1.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.onSurface)
            )
    }
}