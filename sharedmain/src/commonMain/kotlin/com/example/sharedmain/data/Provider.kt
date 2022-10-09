package com.example.sharedmain.data

import com.example.kmtestapp.providers.AbstractFlowProvider
import com.example.sharedmain.data.local.UserDao
import com.example.sharedmain.data.remote.UserApi

class Provider(
    private val userDao: UserDao,
    private val userApi: UserApi
) : AbstractFlowProvider() {

    fun getUsersApi() = resultFlowSafe {
        val result = userApi.getUsers()
        userDao.insertUsers(result)
        result
    }

    fun getUsersDB() = resultFlowSafe {
        userDao.getAllUsers()
    }

}
