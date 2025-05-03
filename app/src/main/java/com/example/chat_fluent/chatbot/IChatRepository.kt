package com.example.chat_fluent.chatbot

import com.example.chat_fluent.models.ChatMessage

interface IChatRepository {
    suspend fun initializeSession(topic: String? = null)
    suspend fun getTutorResponse(userInput: String): List<ChatMessage>
    fun getConversationHistory(): List<ChatMessage>
    fun clearSession()
}