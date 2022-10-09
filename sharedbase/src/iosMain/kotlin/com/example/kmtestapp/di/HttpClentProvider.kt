package com.example.kmtestapp.di

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual class HttpClientModuleProvider {

    actual fun getClient() = HttpClient(Darwin) {

        installFeatures(this@HttpClient)

        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }

    private fun installFeatures(config: HttpClientConfig<*>) = with(config) {
        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.SIMPLE
        }
        install(ResponseObserver) {
            onResponse { response ->
                println("HTTP status IOS: ${response.status}")
            }
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}
