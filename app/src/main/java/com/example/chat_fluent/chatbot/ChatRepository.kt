package com.example.chat_fluent.chatbot

import android.content.Context
import com.example.chat_fluent.data.network.gemini.GeminiApiService
import com.example.chat_fluent.models.ChatMessage
import org.checkerframework.checker.units.qual.Time

class ChatRepository(private val geminiApiService: GeminiApiService): IChatRepository {
    private val _conversationHistory = mutableListOf<ChatMessage>()
    private var currentTopic: String? = null


    companion object
    {
        private var instance: ChatRepository?=null
        fun getInstance(
            remoteSource: GeminiApiService,context: Context
        ): ChatRepository {
            return instance ?: synchronized(this){
                val temp= ChatRepository(remoteSource)
                instance =temp
                temp
            }
        }

    }

    override suspend fun initializeSession(topic: String?) {
        currentTopic = topic
        _conversationHistory.clear()
        geminiApiService.initializateSession(topic)
    }

    override suspend fun getTutorResponse(userInput: String): List<ChatMessage> {

        val userMessage= ChatMessage(message = userInput, role = "user")
        _conversationHistory.add(userMessage)
        // Get formatted history
        val formattedHistory = _conversationHistory
            .takeLast(3)
            .map { "${it.role}: ${it.message}"}
        // Get tutor response
        val response = geminiApiService.getTutorResponse(
            userInput = userInput,
            topic = currentTopic ?: "general",
            history = formattedHistory
        )

        val systemMessages=response.correction?.let { correction->
            listOf(
                ChatMessage(
                    message = "Correction: ${correction.corrected}",
                    role = "system",
                    errors = correction.errors)
            )
        }?:emptyList()


        val tutorMessage= ChatMessage(response.tutorMessage,"model")

        // Update conversation history
        _conversationHistory.addAll(systemMessages + tutorMessage)

        return systemMessages + tutorMessage


    }

    override fun getConversationHistory(): List<ChatMessage> {
        return _conversationHistory.toList()
    }

    override fun clearSession() {
        _conversationHistory.clear()
        currentTopic = null
        geminiApiService.clearSession()
    }
}