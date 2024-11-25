package com.livmas.my_collections_app.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.livmas.my_collections_app.presentation.models.RevealedActionModel
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import com.livmas.my_collections_app.presentation.theme.spacing
import com.livmas.my_collections_app.presentation.widgets.PositionNumberPrefix
import com.livmas.my_collections_app.presentation.widgets.RevealedActionWidget
import com.livmas.my_collections_app.presentation.widgets.SwipeToRevealItemWidget
import mycollectionsapp.composeapp.generated.resources.Res
import mycollectionsapp.composeapp.generated.resources.ic_delete


@Composable
fun AllShopLists(
    modifier: Modifier = Modifier,
    shopLists: List<ShopListInfoModel>,
    onShopListClick: (ShopListInfoModel) -> Unit,
    onDismiss: (ShopListInfoModel) -> Unit,
) {
    LazyColumn(
        Modifier.then(modifier)
            .fillMaxWidth()
    ) {
        items(
            count = shopLists.size,
            key = { shopLists[it].id }
        ) { index ->
            val listInfo = shopLists[index]

            if (index > 0)
                Divider(
                    Modifier.padding(
                        horizontal = MaterialTheme.spacing.extraLargeSpacing
                    )
                )

            ShopListInfoWidget(
                Modifier.clickable {
                    onShopListClick(listInfo)
                }
                    .padding(
                        horizontal = MaterialTheme.spacing.screenPadding,
                        vertical = MaterialTheme.spacing.smallSpacing
                    ),
                model = listInfo,
                prefix = { PositionNumberPrefix(index) },
                onDismiss = {
                    onDismiss(listInfo)
                }
            )
        }
    }
}

@Composable
private fun ShopListInfoWidget(
    modifier: Modifier = Modifier,
    model: ShopListInfoModel,
    onDismiss: () -> Unit,
    prefix: @Composable (() -> Unit)? = null
) {
    var isRevealed by rememberSaveable {
        mutableStateOf(false)
    }

    SwipeToRevealItemWidget(
        modifier = Modifier.then(modifier),
        content = {
            Text(
                modifier = Modifier.weight(1f),
                text = model.name,
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        isRevealed = isRevealed,
        onExpanded = { isRevealed = true },
        onCollapsed = { isRevealed = false },
        prefix = { prefix?.invoke() },
        actions = {
            RevealedActionWidget(
                modifier = Modifier
                    .height(40.dp)
                    .width(70.dp),
                actionModel = RevealedActionModel(
                    backgroundColor = Color.Red,
                    foregroundColor = MaterialTheme.colors.onSurface,
                    iconDrawable = Res.drawable.ic_delete,
                    callback = {
                        onDismiss()
                    }
                )
            )
        }
    )
}
