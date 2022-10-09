package com.example.sharedmain.helpers

import com.example.sharedmain.data.Provider
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProviderHelper: KoinComponent {
    private val provider : Provider by inject()
    fun getProvider() = provider
}