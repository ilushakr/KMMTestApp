package com.example.kmtestapp.network

object KtorUtils {
    class MethodException : Throwable("Can't pass body with get method")
    class ParametersIncompatibilityException : Throwable("Can't pass URL parameter with PATH parameter")
    class UrlCountException(urls: List<Parameters.Url>) : Throwable("More than one URL parameter was passed: ${urls.map { it.value }}")
    sealed class Parameters {
        data class Query(val queryName: String, val value: Any?) : Parameters()
        data class Body(val body: Any) : Parameters()
        data class Path(val pathName: String, val value: String) : Parameters()
        data class Header(val headerName: String, val value: String) : Parameters()
        data class Url(val value: String) : Parameters()
    }
    fun isUrl(parameter: Parameters) = parameter is Parameters.Url
    fun isPath(parameter: Parameters) = parameter is Parameters.Path

}