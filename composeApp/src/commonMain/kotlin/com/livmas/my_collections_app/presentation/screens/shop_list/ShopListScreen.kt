package com.livmas.my_collections_app.presentation.screens.shop_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.livmas.my_collections_app.presentation.screens.home.ListInfoModel

@Composable
fun ShopListScreen(
    listInfoModel: ListInfoModel
) {
    ShopListFrame(listInfoModel)
}

@Composable
private fun ShopListFrame(
    listInfoModel: ListInfoModel
) {
    Scaffold {
        ShopListScreenContent(
            Modifier.padding(it),
            listInfoModel
        )
    }
}

@Composable
private fun ShopListScreenContent(
    modifier: Modifier = Modifier,
    listInfoModel: ListInfoModel
) {
    Column(modifier) {
        Text(listInfoModel.name)
    }
}
