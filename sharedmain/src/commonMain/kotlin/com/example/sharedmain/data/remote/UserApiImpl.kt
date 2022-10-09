package com.example.sharedmain.data.remote

import com.example.kmtestapp.network.KtorClient
import com.example.sharedmain.objects.User

class UserApiImpl(private val client: KtorClient) : UserApi {

    override suspend fun getUsers(): List<User> {
        return client.getRaw("users")
    }
}