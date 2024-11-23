package com.livmas.my_collections_app.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.livmas.my_collections_app.presentation.models.RevealedActionModel
import com.livmas.my_collections_app.presentation.theme.spacing
import org.jetbrains.compose.resources.painterResource

@Composable
fun RevealedActionWidget(
    modifier: Modifier = Modifier,
    actionModel: RevealedActionModel
) {
    Box(
        modifier = Modifier.then(modifier)
            .background(actionModel.backgroundColor)
            .clickable { actionModel.action() }
            .padding(MaterialTheme.spacing.smallSpacing)
    ) {
        Icon(
            modifier = Modifier.fillMaxHeight()
                .aspectRatio(1f),
            painter = painterResource(actionModel.iconDrawable),
            contentDescription = null,
            tint = MaterialTheme.colors.surface
        )
    }
}
