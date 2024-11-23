package com.livmas.my_collections_app.presentation.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.livmas.my_collections_app.presentation.theme.spacing

@Composable
internal fun ItemWidget(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
    prefix: @Composable (RowScope.() -> Unit)? = null,
    postfix: @Composable (RowScope.() -> Unit)? = null,
    decoration: @Composable (() -> Unit)? = null
) {
    Box {
        decoration?.invoke()
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            prefix?.run {
                prefix()
                Spacer(Modifier.width(MaterialTheme.spacing.extraSmallSpacing))
            }

            content()

            postfix?.run {
                Spacer(Modifier.width(MaterialTheme.spacing.extraSmallSpacing))
                postfix()
            }
        }
    }
}
