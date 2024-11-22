package com.livmas.my_collections_app.di

import com.livmas.my_collections_app.data.data_sources.ListRemoteDataSource
import com.livmas.my_collections_app.data.data_sources.UserRemoteDataSource
import com.livmas.my_collections_app.data.repositories.ShopListRepositoryImpl
import com.livmas.my_collections_app.domain.repositories.ShopListRepository
import com.livmas.my_collections_app.domain.usecases.CreateShopListUseCase
import com.livmas.my_collections_app.domain.usecases.GetShopListsUseCase
import com.livmas.my_collections_app.presentation.screens.home.HomeViewModel
import com.livmas.my_collections_app.presentation.screens.shop_list.ShopListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun appModule() = module {
    singleOf(::ListRemoteDataSource)
    singleOf(::UserRemoteDataSource)

    single<ShopListRepository> { ShopListRepositoryImpl(get(), get()) }

    singleOf(::CreateShopListUseCase)
    singleOf(::GetShopListsUseCase)

    viewModelDefinition {
        HomeViewModel(get(), get())
    }

    viewModelDefinition {
        ShopListViewModel()
    }
}