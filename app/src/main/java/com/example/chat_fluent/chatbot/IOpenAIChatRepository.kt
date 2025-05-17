package com.example.chat_fluent.chatbot

import com.example.chat_fluent.models.FeedbackResponse
import com.example.chat_fluent.models.Message
import com.example.chat_fluent.models.TutorResponse
import com.example.chat_fluent.utilities.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IOpenAIChatRepository{
    val conversationHistory: StateFlow<List<Message>>
    suspend fun sendMessage(
        userInput: String,
        topic: String?,
        history: List<String>
    ): Result<TutorResponse>

    suspend fun generateFeedback(
        //conversationHistory: List<Message>,
    ): ApiResult<FeedbackResponse>

    fun getConversationHistory(): Flow<List<Message>>
    suspend fun initializeChat(topic: String? = null)
    fun getTopicIntroductionPrompt(topic: String): String
    suspend fun getInitialPrompt(topic: String?): Result<String>

    fun createChatCompelation(message:String)
}