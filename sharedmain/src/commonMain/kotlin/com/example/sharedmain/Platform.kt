package com.example.sharedmain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform