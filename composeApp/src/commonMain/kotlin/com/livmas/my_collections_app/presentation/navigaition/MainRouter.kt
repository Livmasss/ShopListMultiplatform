package com.livmas.my_collections_app.presentation.navigaition

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.livmas.my_collections_app.presentation.screens.home.HomeScreen
import com.livmas.my_collections_app.presentation.screens.home.ListInfoModel
import com.livmas.my_collections_app.presentation.screens.shop_list.ShopListScreen
import kotlin.reflect.typeOf

@Composable
fun MainRouter() {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = MainDest.Home
    ) {
        composable<MainDest.Home> {
            HomeScreen {
                navController.navigate(MainDest.ShopList(it))
            }
        }
        composable<MainDest.ShopList>(
            typeMap = mapOf(
                typeOf<ListInfoModel>() to CustomNavType.ListInfoNavType
            )
        ) { navBackStackEntry ->
            val route: MainDest.ShopList = navBackStackEntry.toRoute()
            ShopListScreen(route.mainInfo)
        }
    }
}