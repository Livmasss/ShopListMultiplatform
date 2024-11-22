package com.livmas.my_collections_app.presentation.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel
import com.livmas.my_collections_app.presentation.theme.spacing

@Composable
internal fun ShopListScreenTopBar(
    modifier: Modifier = Modifier,
    collectionInfo: ShopListInfoModel
) {
    TopAppBar(
        modifier = modifier,
        title = {
            ShopListMainInfoWidget(collectionInfo)
        }
    )
}

@Composable
internal fun ShopListMainInfoWidget(
    collectionInfo: ShopListInfoModel,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    Row {
        leadingIcon?.run {
            invoke()
            Spacer(Modifier.width(MaterialTheme.spacing.extraSmallSpacing))
        }

        Text(
            text = collectionInfo.name,
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        trailingIcon?.run {
            invoke()
            Spacer(Modifier.width(MaterialTheme.spacing.extraSmallSpacing))
        }
    }
}
