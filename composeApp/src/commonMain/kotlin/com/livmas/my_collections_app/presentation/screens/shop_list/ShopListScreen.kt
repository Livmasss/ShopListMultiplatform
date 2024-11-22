package com.livmas.my_collections_app.presentation.screens.shop_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.livmas.my_collections_app.presentation.models.ShopListInfoModel

@Composable
fun ShopListScreen(
    shopListInfoModel: ShopListInfoModel
) {
    ShopListFrame(shopListInfoModel)
}

@Composable
private fun ShopListFrame(
    shopListInfoModel: ShopListInfoModel
) {
    Scaffold {
        ShopListScreenContent(
            Modifier.padding(it),
            shopListInfoModel
        )
    }
}

@Composable
private fun ShopListScreenContent(
    modifier: Modifier = Modifier,
    shopListInfoModel: ShopListInfoModel
) {
    Column(modifier) {
        Text(shopListInfoModel.name)
    }
}
