package com.livmas.my_collections_app.di

import com.livmas.my_collections_app.data.local.createDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val targetModule: Module = module {
    single { createDataStore() }
}