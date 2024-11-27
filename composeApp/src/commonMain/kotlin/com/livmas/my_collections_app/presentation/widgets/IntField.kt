package com.livmas.my_collections_app.presentation.widgets

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.livmas.my_collections_app.utils.isDigitsOnly
import org.jetbrains.compose.resources.StringResource

@Composable
fun IntField(
    modifier: Modifier = Modifier,
    value: Int?,
    onValueChange: (Int?) -> Unit,
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
    BaseNumericField(
        modifier = modifier,
        value = value,
        onValueChange = {
            if (it.isDigitsOnly())
                onValueChange(it.toIntOrNull())
                        },
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
