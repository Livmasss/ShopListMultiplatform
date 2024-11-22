package com.livmas.my_collections_app.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import com.livmas.my_collections_app.presentation.theme.spacing
import com.livmas.my_collections_app.presentation.widgets.ItemWidget
import com.livmas.my_collections_app.presentation.widgets.PositionNumberPrefix


@Composable
fun AllShopLists(
    modifier: Modifier = Modifier,
    shopLists: List<ShopListInfoModel>,
    onShopListClick: (ShopListInfoModel) -> Unit
) {
    LazyColumn(
        Modifier.then(modifier)
            .fillMaxWidth()
    ) {
        items(
            count = shopLists.size,
            key = { shopLists[it].id }
        ) { index ->
            val collection = shopLists[index]

            if (index > 0)
                Divider(
                    Modifier.padding(
                        horizontal = MaterialTheme.spacing.extraLargeSpacing
                    )
                )

            ShopListInfoWidget(
                Modifier.clickable {
                    onShopListClick(collection)
                }
                    .padding(
                        horizontal = MaterialTheme.spacing.screenPadding,
                        vertical = MaterialTheme.spacing.smallSpacing
                    ),
                model = collection,
                prefix = { PositionNumberPrefix(index) }
            )
        }
    }
}

@Composable
private fun ShopListInfoWidget(
    modifier: Modifier = Modifier,
    model: ShopListInfoModel,
    prefix: @Composable (() -> Unit)? = null
) {
    ItemWidget(
        modifier = modifier,
        content = {
            Text(
                modifier = Modifier.weight(1f),
                text = model.name,
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        prefix = { prefix?.invoke() }
    )
}
