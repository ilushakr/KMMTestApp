package com.example.kmtestapp.di

import io.ktor.client.*
import org.koin.dsl.module

val networkBaseModule = module {

    single {
        HttpClientModuleProvider().getClient()
    }

}

expect class HttpClientModuleProvider() {
    fun getClient(): HttpClient
}
