package com.example.sharedmain.di

import com.example.sharedmain.data.Provider
import org.koin.dsl.module

fun sharedFeatures() = listOf(
    cacheModule,
    networkModule,
    appModule
)

val appModule = module {
    factory {
        Provider(get(), get())
    }
}