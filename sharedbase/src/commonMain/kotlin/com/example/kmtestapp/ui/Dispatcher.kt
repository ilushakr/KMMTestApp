package com.example.kmmtest.ui

import com.example.kmtestapp.ui.AbstractStore
import com.example.kmtestapp.ui.IAction

class Dispatcher {
    protected val stores = mutableSetOf<AbstractStore<*>>()

    fun handle(action: IAction) {
        stores.forEach {
            it.handle(action)
        }
    }

    fun add(store: AbstractStore<*>) {
        stores.add(store)
    }

    fun remove(store: AbstractStore<*>) {
        stores.remove(store)
    }
}
