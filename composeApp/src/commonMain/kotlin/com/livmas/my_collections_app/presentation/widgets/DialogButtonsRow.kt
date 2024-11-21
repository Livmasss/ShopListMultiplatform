package com.livmas.my_collections_app.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.livmas.my_collections_app.presentation.theme.spacing

@Composable
fun TextDialogButtonsRow(
    cancelText: String = "Отменить",
    confirmText: String = "Подтвердить",

    onCancel: () -> Unit = {},
    onConfirm: () -> Unit
) {
    DialogButtonsRow(
        cancelButton = {
            Text(
                modifier = Modifier.clickable {
                    onCancel()
                },
                text = cancelText,
            )
        },
        confirmButton = {
            Text(
                modifier = Modifier.clickable {
                    onConfirm()
                },
                text = confirmText,
                color = MaterialTheme.colors.primary
            )
        }
    )
}

@Composable
fun DialogButtonsRow(
    cancelButton: @Composable () -> Unit,
    confirmButton: @Composable () -> Unit
) {
    Row {
        cancelButton()
        Spacer(Modifier.width(MaterialTheme.spacing.smallSpacing))
        confirmButton()
    }
}
