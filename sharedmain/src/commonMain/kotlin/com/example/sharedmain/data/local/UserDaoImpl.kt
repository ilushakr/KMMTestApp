package com.example.sharedmain.data.local

import com.example.sharedmain.objects.User
import com.example.sharedmain.shared.cache.UserDatabase


class UserDaoImpl(userDatabase: UserDatabase) : UserDao {

    private val userQueries = userDatabase.userDatabaseQueries

    override fun getAllUsers(): List<User> {
        return userQueries.selectAllUsers(::mapUsers).executeAsList()
    }

    private fun mapUsers(id: Long, name: String): User {
        return User(
            id = id.toInt(),
            name = name
        )
    }


    override fun insertUsers(users: List<User>) {
        userQueries.transaction {
            users.forEach { user ->
                insertUser(user)
            }
        }
    }

    private fun insertUser(user: User) {
        userQueries.insertUser(
            id = user.id.toLong(),
            name = user.name
        )
    }

}