package com.example.kmtestapp.android

import android.app.Application
import com.example.kmtestapp.di.sharedBase
import com.example.sharedmain.di.sharedFeatures
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //inject Android context
            androidContext(this@BaseApplication)
            modules(sharedBase())
            modules(sharedFeatures())
        }

    }
}