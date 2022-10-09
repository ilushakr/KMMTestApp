package com.example.sharedmain.di

import com.example.kmtestapp.network.KtorClient
import com.example.sharedmain.data.remote.UserApi
import com.example.sharedmain.data.remote.UserApiImpl
import org.koin.dsl.module

val networkModule = module {

    val baseUrl = "https://jsonplaceholder.typicode.com/"

    single{
        KtorClient.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .build()
    }

    single<UserApi> {
        UserApiImpl(get())
    }
}
