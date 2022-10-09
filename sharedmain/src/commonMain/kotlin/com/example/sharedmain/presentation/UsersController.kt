package com.example.sharedmain.presentation

import com.example.kmmtest.ui.Dispatcher
import com.example.kmtestapp.dispatchers.defaultDispatcher
import com.example.kmtestapp.objects.ResultState
import com.example.kmtestapp.ui.IController
import com.example.sharedmain.data.Provider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.inject

interface UsersController : IController<UsersStore.UsersState> {

    val provider: Provider

    fun getUsersApi() {
        CoroutineScope(defaultDispatcher).launch {
            provider.getUsersApi().collect{
                val action = when (it) {
                    is ResultState.Fail -> UsersStore.Fail(error = it.error)
                    is ResultState.Pending -> UsersStore.Pending
                    is ResultState.Success -> UsersStore.Success(data = it.data)
                }
                dispatcher.handle(action)
            }
        }
    }

    fun getUsersDb() {
        CoroutineScope(defaultDispatcher).launch {
            provider.getUsersDB().collect{
                val action = when (it) {
                    is ResultState.Fail -> UsersStore.Fail(error = it.error)
                    is ResultState.Pending -> UsersStore.Pending
                    is ResultState.Success -> UsersStore.Success(data = it.data)
                }
                dispatcher.handle(action)
            }
        }
    }


    companion object {
        fun initController() = object : UsersController {
            override val provider by inject<Provider>()
            override val store = UsersStore()
            override val dispatcher = Dispatcher().apply { add(store) }
        }
    }

}