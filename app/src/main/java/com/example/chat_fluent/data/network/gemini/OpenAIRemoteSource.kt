package com.example.chat_fluent.data.network.gemini

import com.example.chat_fluent.models.ChatRequest
import com.example.chat_fluent.models.Choice
import kotlinx.coroutines.flow.Flow


interface OpenAIRemoteSource{
    suspend fun getTutorResponseFromNetwork(request: ChatRequest): Flow<List<Choice>>
}