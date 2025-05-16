package com.example.chat_fluent.data.network.gemini

import com.example.chat_fluent.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    fun getInstance(): OpenAIApiService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)  // Increase connection timeout
            .readTimeout(30, TimeUnit.SECONDS)     // Increase read timeout
            .writeTimeout(30, TimeUnit.SECONDS)    // Increase write timeout
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.openai.com/")  // Make sure this is correct
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(OpenAIApiService::class.java)
    }

//object RetrofitHelper {
//    private const val BASE_URL = Constants.BASE_OPENAI_URL
//    private const val API_KEY = Constants.OPENAI_API_KEY
//
//    @Volatile
//    private var instance: OpenAIApiService?=null
//    fun getInstance(): OpenAIApiService
//    {
//        synchronized(this) {
//            return instance?: Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(OpenAIApiService::class.java)
//                .also {
//                    instance=it
//                }
//        }
//    }
//    val api: OpenAIApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(
//                OkHttpClient.Builder()
//                    .addInterceptor { chain ->
//                        chain.proceed(
//                            chain.request().newBuilder()
//                                .addHeader("Authorization", "Bearer $API_KEY")
//                                .build()
//                        )
//                    }
//                    .build()
//            )
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(OpenAIApiService::class.java)
//    }
}