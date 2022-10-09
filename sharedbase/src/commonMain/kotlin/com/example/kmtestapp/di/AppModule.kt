package com.example.kmtestapp.di

fun sharedBase() = listOf(
    networkBaseModule,
    cacheBaseModule
)