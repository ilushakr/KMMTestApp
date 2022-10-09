package com.example.sharedmain.helpers

import com.example.kmtestapp.di.sharedBase
import com.example.sharedmain.di.sharedFeatures
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class KoinHelper : KoinComponent {

    fun initKoin(){
        startKoin {
            modules(sharedBase())
            modules(sharedFeatures())
        }
    }
}
