package com.example.sharedmain.di

import com.example.kmtestapp.cache.DatabaseDriverFactory
import com.example.sharedmain.data.local.UserDao
import com.example.sharedmain.data.local.UserDaoImpl
import com.example.sharedmain.shared.cache.UserDatabase
import org.koin.dsl.module

val cacheModule = module {

    single<UserDao> {
        UserDatabase.Schema
        UserDaoImpl(UserDatabase((get() as DatabaseDriverFactory)
            .createDriver(UserDatabase.Schema, "user_database.db")))
    }

}