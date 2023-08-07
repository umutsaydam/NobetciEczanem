package com.example.nobetcieczanem

import com.example.nobetcieczanem.API.ApiClient
import com.example.nobetcieczanem.API.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val BASE_URL = "https://api.collectapi.com/health/"
    private val API_KEY = "apikey {API_KEY}"


    private val httpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("content-type", "application/json")
                    builder.header("authorization", API_KEY)

                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    val apiClient = ApiClient(apiService)
}