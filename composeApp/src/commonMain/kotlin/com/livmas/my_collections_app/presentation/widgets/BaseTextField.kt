package com.livmas.my_collections_app.presentation.widgets

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BaseIntField(
    modifier: Modifier = Modifier,
    value: Int?,
    onValueChange: (Int?) -> Unit,
    labelResource: StringResource? = null,
    placeholderResource: StringResource? = null
) {
    BaseTextField(
        modifier = modifier,
        value = value?.toString() ?: "",
        onValueChange = {
            onValueChange(it.toIntOrNull())
        },
        labelResource = labelResource,
        placeholderResource = placeholderResource
    )
}

@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelResource: StringResource? = null,
    placeholderResource: StringResource? = null,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { labelResource?.let { Text(stringResource(it))} },
        placeholder = { placeholderResource?.let{ Text(stringResource(it))} }
    )
}