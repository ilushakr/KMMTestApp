package com.example.sharedmain.data.remote

import com.example.sharedmain.objects.User

interface UserApi {

    suspend fun getUsers(): List<User>
}