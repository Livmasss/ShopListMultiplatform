package com.livmas.my_collections_app.presentation

import android.app.Application
import com.livmas.my_collections_app.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@MyApplication)
        }
    }
}