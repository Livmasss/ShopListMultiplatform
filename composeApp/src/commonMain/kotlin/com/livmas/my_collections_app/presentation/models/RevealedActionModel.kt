package com.livmas.my_collections_app.presentation.models

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class RevealedActionModel(
    val backgroundColor: Color,
    val foregroundColor: Color,
    val iconDrawable: DrawableResource,
    val action: () -> Unit
)
