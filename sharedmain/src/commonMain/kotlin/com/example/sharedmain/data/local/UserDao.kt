package com.example.sharedmain.data.local

import com.example.sharedmain.objects.User

interface UserDao {

    fun getAllUsers(): List<User>

    fun insertUsers(users: List<User>)

}