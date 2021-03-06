package com.example.kotlinpro

import android.app.Application
import com.example.kotlinpro.servicelocated.networkModule
import com.example.kotlinpro.servicelocated.repositoriesModel
import com.example.kotlinpro.servicelocated.viewModelsModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@HiltAndroidApp
class App : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoriesModel, viewModelsModule)
        }
    }
}