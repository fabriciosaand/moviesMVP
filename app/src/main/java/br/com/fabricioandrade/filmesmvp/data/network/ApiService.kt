package br.com.fabricioandrade.filmesmvp.data.network

import br.com.fabricioandrade.filmesmvp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {

    private val service: MoviesService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(createHttpClient(BuildConfig.API_KEY, BuildConfig.LANGUAGE))
            .build()

        service = retrofit.create(MoviesService::class.java)
    }

    fun getInstance(): MoviesService{
        return service
    }

    private fun createHttpClient(apiKey: String, language: String): OkHttpClient {
        if (apiKey.isEmpty()) {
            throw IllegalArgumentException("The API key is missing. Please get one from https://www.themoviedb.org")
        }
        return OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor(apiKey, language))
            .build()
    }
}
