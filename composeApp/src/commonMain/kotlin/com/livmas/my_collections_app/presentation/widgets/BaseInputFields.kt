package com.livmas.my_collections_app.presentation.widgets

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BaseNumericField(
    modifier: Modifier = Modifier,
    value: Int?,
    onValueChange: (String) -> Unit,
    labelResource: StringResource? = null,
    placeholderResource: StringResource? = null,

    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    BaseField(
        modifier = modifier,
        value = value?.toString() ?: "",
        onValueChange = {
            onValueChange(it)
                        },
        labelResource = labelResource,
        placeholderResource = placeholderResource,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = keyboardActions,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        textStyle = textStyle,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}

@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelResource: StringResource? = null,
    placeholderResource: StringResource? = null,

    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    BaseField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        labelResource = labelResource,
        placeholderResource = placeholderResource,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        keyboardActions = keyboardActions
    )
}

@Composable
fun BaseField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelResource: StringResource? = null,
    placeholderResource: StringResource? = null,

    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { labelResource?.let { Text(stringResource(it))} },
        placeholder = { placeholderResource?.let{ Text(stringResource(it))} },
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}