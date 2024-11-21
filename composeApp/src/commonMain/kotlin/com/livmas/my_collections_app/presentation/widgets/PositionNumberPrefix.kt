package com.livmas.my_collections_app.presentation.widgets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
fun PositionNumberPrefix(index: Int) {
    Text(
        text = (index + 1).toString(),
        style = MaterialTheme.typography.body1
    )
}