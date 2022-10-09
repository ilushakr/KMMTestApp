package com.example.kmtestapp.network

import com.example.kmtestapp.network.KtorUtils.isPath
import com.example.kmtestapp.network.KtorUtils.isUrl
import com.example.kmtestapp.objects.ResultState
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class KtorClient private constructor(
    val baseUrl: String,
    val client: HttpClient
) {

    suspend inline fun <reified T> get(
        endPoint: String,
        vararg parameters: KtorUtils.Parameters
    ) = request<T>(
        url = baseUrl + endPoint,
        parameters = parameters,
        method = HttpMethod.Get
    )

    suspend inline fun <reified T> getRaw(
        endPoint: String,
        vararg parameters: KtorUtils.Parameters
    ) = requestRaw<T>(
        url = baseUrl + endPoint,
        parameters = parameters,
        method = HttpMethod.Get
    )

    suspend inline fun <reified T> post(
        endPoint: String,
        vararg parameters: KtorUtils.Parameters
    ) = request<T>(
        url = baseUrl + endPoint,
        parameters = parameters,
        method = HttpMethod.Post
    )

    suspend inline fun <reified T> put(
        endPoint: String,
        vararg parameters: KtorUtils.Parameters
    ) = request<T>(
        url = baseUrl + endPoint,
        parameters = parameters,
        method = HttpMethod.Put
    )

    suspend inline fun <reified T> get(
        vararg parameters: KtorUtils.Parameters
    ) = request<T>(
        parameters = parameters,
        method = HttpMethod.Get
    )

    suspend inline fun <reified T> post(
        vararg parameters: KtorUtils.Parameters
    ) = request<T>(
        parameters = parameters,
        method = HttpMethod.Post
    )

    suspend inline fun <reified T> put(
        vararg parameters: KtorUtils.Parameters
    ) = request<T>(
        parameters = parameters,
        method = HttpMethod.Put
    )

    suspend inline fun <reified T> patch(
        vararg parameters: KtorUtils.Parameters
    ) = request<T>(
        parameters = parameters,
        method = HttpMethod.Patch
    )

    suspend inline fun <reified T> patch(
        url: String,
        vararg parameters: KtorUtils.Parameters
    ) = request<T>(
        url = baseUrl + url,
        parameters = parameters,
        method = HttpMethod.Patch
    )

    suspend inline fun <reified T> request(
        url: String = baseUrl,
        vararg parameters: KtorUtils.Parameters,
        method: HttpMethod = HttpMethod.Get
    ): ResultState<T> {

        return try {
            ResultState.Success(
                data = requestRaw(url = url, parameters = parameters, method = method)
            )
        } catch (e: Throwable) {
            when (e) {
                is KtorUtils.MethodException, is KtorUtils.UrlCountException, is KtorUtils.ParametersIncompatibilityException -> {
                    throw e
                }
                else -> ResultState.Fail(error = e)
            }
        }
    }

    suspend inline fun <reified T> requestRaw(
        url: String,
        vararg parameters: KtorUtils.Parameters,
        method: HttpMethod = HttpMethod.Get
    ): T {
        return client.request(url) builder@{
            this.method = method
            if (parameters.count(::isUrl) > 1) {
                throw KtorUtils.UrlCountException(urls = parameters.filterIsInstance<KtorUtils.Parameters.Url>())
            }

            // todo some issue with auto complete: show hint to replace with "isEmpty()"
            if (parameters.count(::isUrl) > 0 && parameters.count(::isPath) > 0) {
                throw KtorUtils.ParametersIncompatibilityException()
            }
            for (parameter in parameters) {
                with(parameter) {
                    when (this) {
                        is KtorUtils.Parameters.Header -> {
                            header(headerName, value)
                        }
                        is KtorUtils.Parameters.Query -> {
                            parameter(queryName, value)
                        }
                        is KtorUtils.Parameters.Body -> {
                            if (method == HttpMethod.Get) {
                                throw KtorUtils.MethodException()
                            }
                            contentType(ContentType.Application.Json)
                            setBody(body)
                        }
                        is KtorUtils.Parameters.Path -> {
                            this@builder.url(url.replace("{$pathName}", value))
                        }
                        is KtorUtils.Parameters.Url -> {
                            this@builder.url(value)
                        }
                    }
                }
            }
        }.body()
    }

    class Builder {
        /*
        Should end with "/"
         */
        private lateinit var _baseUrl: String

        private lateinit var _client: HttpClient

        fun baseUrl(baseUrl: String) = apply {
            this._baseUrl = baseUrl
        }

        fun client(client: HttpClient) = apply {
            this._client = client
        }

        fun build(): KtorClient {

            if (!this::_baseUrl.isInitialized) {
                throw Throwable("Base URL required.")
            }

            if (!this::_client.isInitialized) {
                throw Throwable("Http Client required.")
            }

            return KtorClient(_baseUrl, _client)
        }
    }
}