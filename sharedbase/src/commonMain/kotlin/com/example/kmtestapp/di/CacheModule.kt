package com.example.kmtestapp.di

import com.example.kmtestapp.cache.DatabaseDriverFactory
import org.koin.dsl.module

val cacheBaseModule = module {

    single { DatabaseDriverFactory() }

}