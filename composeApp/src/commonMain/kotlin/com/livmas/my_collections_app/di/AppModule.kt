package com.livmas.my_collections_app.di

import com.livmas.my_collections_app.data.local.datasources.UserLocalDataSource
import com.livmas.my_collections_app.data.remote.data_sources.ListRemoteDataSource
import com.livmas.my_collections_app.data.remote.data_sources.UserRemoteDataSource
import com.livmas.my_collections_app.data.repositories.ShopListRepositoryImpl
import com.livmas.my_collections_app.data.repositories.UserRepositoryImpl
import com.livmas.my_collections_app.domain.repositories.ShopListRepository
import com.livmas.my_collections_app.domain.repositories.UserRepository
import com.livmas.my_collections_app.domain.usecases.CreateShopListUseCase
import com.livmas.my_collections_app.domain.usecases.CreateShoppingItemUseCase
import com.livmas.my_collections_app.domain.usecases.CrossListItemOutUseCase
import com.livmas.my_collections_app.domain.usecases.DeleteListItemUseCase
import com.livmas.my_collections_app.domain.usecases.DeleteShopListUseCase
import com.livmas.my_collections_app.domain.usecases.GetListContentUseCase
import com.livmas.my_collections_app.domain.usecases.GetShopListsUseCase
import com.livmas.my_collections_app.presentation.screens.home.HomeViewModel
import com.livmas.my_collections_app.presentation.screens.shop_list.ShopListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::ListRemoteDataSource)
    singleOf(::UserRemoteDataSource)
    single<UserLocalDataSource> {
        UserLocalDataSource(
            dataStore = get()
        )
    }

    single<ShopListRepository> { ShopListRepositoryImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    singleOf(::CreateShopListUseCase)
    singleOf(::GetShopListsUseCase)
    singleOf(::GetListContentUseCase)
    singleOf(::DeleteListItemUseCase)
    singleOf(::CrossListItemOutUseCase)
    singleOf(::CreateShoppingItemUseCase)
    singleOf(::DeleteShopListUseCase)

    viewModelDefinition {
        HomeViewModel(get(), get(), get())
    }
    viewModelDefinition {
        ShopListViewModel(get(), get(), get(), get())
    }
}