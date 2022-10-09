package com.example.sharedmain.presentation

import com.example.kmtestapp.ui.AbstractStore
import com.example.kmtestapp.ui.IAction
import com.example.kmtestapp.ui.IState
import com.example.sharedmain.objects.User

class UsersStore: AbstractStore<UsersStore.UsersState>(UsersState()) {

    override fun newState(oldState: UsersState, action: IAction): UsersState {
        return when(action){
            is Success -> oldState.copy(data = action.data, pending = false, error = null)
            is Pending -> oldState.copy(data = null, pending = true, error = null)
            is Fail -> oldState.copy(data = null, pending = false, error = action.error)
            else -> super.newState(oldState, action)
        }
    }

    data class UsersState(
        val data: List<User>? = null,
        val pending: Boolean? = null,
        val error: Throwable? = null
    ): IState

    data class Success(val data: List<User>): IAction
    object Pending: IAction
    data class Fail(val error: Throwable): IAction
}