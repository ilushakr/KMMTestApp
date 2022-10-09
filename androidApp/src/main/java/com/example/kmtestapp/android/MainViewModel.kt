package com.example.kmtestapp.android

import androidx.lifecycle.ViewModel
import com.example.sharedmain.presentation.UsersController
import com.example.sharedmain.presentation.UsersStore
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent

class MainViewModel : ViewModel(), KoinComponent {

    private val usersController by lazy { UsersController.initController() }

//    val state: StateFlow<UsersStore.UsersState>
//        get() = usersController.state()

    val state = MutableStateFlow(UsersStore.UsersState())
    init {
        usersController.onChange {
            state.value = it
        }
    }
    fun getUsersApi(){
        usersController.getUsersApi()
    }

    fun getUsersDB(){
        usersController.getUsersDb()
    }
}