package com.example.chat_fluent.data.network.gemini

import com.example.chat_fluent.Constants
import com.example.chat_fluent.models.ChatRequest
import com.example.chat_fluent.models.ChatResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAIApiService {
    @POST("v1/chat/completions")
     fun getTutorResponse(
        @Body request: ChatRequest,
        @Header("Content-type") contentType:String="application/json",
        @Header("Authorization") authorization:String="Bearer ${Constants.OPENAI_API_KEY}"
    ): Call<ChatResponse>
}