package com.livmas.my_collections_app.di

import com.livmas.my_collections_app.data.data_sources.ListRemoteDataSource
import com.livmas.my_collections_app.data.data_sources.UserRemoteDataSource
import com.livmas.my_collections_app.data.repositories.ListRepositoryImpl
import com.livmas.my_collections_app.domain.repositories.ListRepository
import com.livmas.my_collections_app.presentation.screens.home.HomeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun appModule() = module {
    singleOf(::ListRemoteDataSource)
    singleOf(::UserRemoteDataSource)
    single<ListRepository> { ListRepositoryImpl(get(), get()) }
    viewModelDefinition {
        HomeViewModel(get())
    }
}