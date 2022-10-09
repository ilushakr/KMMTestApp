package com.example.sharedmain.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

//    @SerialName("username")
//    val userName: String
){
    fun getString() = this.toString()
}
