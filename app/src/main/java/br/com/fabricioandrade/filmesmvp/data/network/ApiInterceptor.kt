package br.com.fabricioandrade.filmesmvp.data.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor(private val apiKey: String, private val language: String): Interceptor {

    companion object {
        const val API_KEY = "api_key"
        const val LANGUAGE = "language"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .addQueryParameter(LANGUAGE, language)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}