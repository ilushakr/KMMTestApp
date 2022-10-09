package com.example.kmtestapp.ui

import com.example.kmmtest.ui.Dispatcher
import com.example.kmtestapp.dispatchers.defaultDispatcher
import org.koin.core.component.KoinComponent
import io.ktor.utils.io.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

interface IController<STATE : IState>: KoinComponent {
    val store: AbstractStore<STATE>
    val dispatcher: Dispatcher

    fun state() = store.state()

    fun onChange(provideNewState: ((STATE) -> Unit)) : Closeable {

        val job = Job()

        state().onEach {
            provideNewState(it)
        }.launchIn(
            CoroutineScope(defaultDispatcher)
        )

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }

    }
}
