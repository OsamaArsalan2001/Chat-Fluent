package com.example.chat_fluent.data.network.gemini

import com.example.chat_fluent.Constants
import com.example.chat_fluent.data.network.gemini.prompts.EnglishTutorPrompt
import com.example.chat_fluent.models.TutorResponse
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.android.gms.common.api.internal.ApiKey
import com.google.gson.Gson


class GeminiApiService(private val apiKey: String)
{
    companion object
    {
        private var instance: GeminiApiService?=null
        fun getInstance(): GeminiApiService {
            return instance ?: synchronized(this){
                val temp= GeminiApiService(apiKey = Constants.GEMINI_API_KEY)
                instance =temp
                temp
            }
        }

    }

    private val generativeModel by lazy {
        GenerativeModel(
            modelName = "gemini-1.5-pro-latest",
            apiKey=apiKey,
            safetySettings = listOf( // Optional: Filter sensitive content
                SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.MEDIUM_AND_ABOVE)
            )
        )
    }
    private var chatSession: Chat? = null
    private val gson = Gson()
    private var currentTopic: String? = null



    suspend fun initializateSession(topic:String?=null)
    {
        currentTopic = topic
        chatSession = generativeModel.startChat()

        // Send initial topic context if provided
        topic?.let {
            chatSession?.sendMessage(
                EnglishTutorPrompt.getTopicIntroductionPrompt(it)
            )
        }

    }

    suspend fun getTutorResponse(userInput: String,topic:String,history:List<String>): TutorResponse
    {
        val prompt= EnglishTutorPrompt.buildPrompt(userInput = userInput,topic, history = history)
        return try {
            val response = chatSession?.sendMessage(prompt)?.text
                ?: throw Exception("Empty response")

           // Json.decodeFromString(response)
            gson.fromJson(response, TutorResponse::class.java)
                ?: TutorResponse(
                    "I didn't understand that",
                    correction = null
                )
        } catch (e: Exception) {
            TutorResponse(
                tutorMessage = "Let's continue our conversation!",
                correction = null
            )
        }
    }
    fun clearSession() {
        chatSession = null
        currentTopic = null
    }
}

/*class GeminiApiService(private val apiKey: String) {
    private val generativeModel by lazy {
        GenerativeModel(
            modelName = "gemini-1.5-pro-latest",
            apiKey = apiKey,
            safetySettings = listOf( // Optional: Filter sensitive content
                SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.MEDIUM_AND_ABOVE)
            )
        )
    }

    // Shared chat instance for conversation continuity
    private var ongoingChat: Chat? = null

    suspend fun sendMessage(
        message: String,
        isNewTopic: Boolean = false
    ): String {
        return try {
            if (isNewTopic) ongoingChat = null

            val chat = ongoingChat ?: generativeModel.startChat().also {
                ongoingChat = it
            }

            chat.sendMessage(message).text ?: "No response"
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}*/