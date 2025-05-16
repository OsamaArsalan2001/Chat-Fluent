package com.example.chat_fluent.chatbot

import com.example.chat_fluent.models.FeedbackResponse
import com.example.chat_fluent.models.Message
import com.example.chat_fluent.models.TutorResponse
import kotlinx.coroutines.flow.Flow

interface IOpenAIChatRepository{

    suspend fun sendMessage(
        userInput: String,
        topic: String?,
        history: List<String>
    ): Result<TutorResponse>

    suspend fun generateFeedback(
        conversationHistory: List<String>,
        userLevel: String
    ): Result<FeedbackResponse>

    fun getConversationHistory(): Flow<List<Message>>
    fun getTopicIntroductionPrompt(topic: String): String
    suspend fun getInitialPrompt(topic: String?): Result<String>

    fun createChatCompelation(message:String)
}