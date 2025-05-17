package com.example.chat_fluent.chatbot

import android.util.Log
import com.example.chat_fluent.Constants
import com.example.chat_fluent.data.network.gemini.OpenAIRemoteSource
import com.example.chat_fluent.data.network.gemini.RetrofitHelper
import com.example.chat_fluent.data.network.gemini.prompts.EnglishTutorPrompt
import com.example.chat_fluent.data.network.gemini.prompts.FeedbackPrompt
import com.example.chat_fluent.models.Message
import com.example.chat_fluent.models.ChatRequest
import com.example.chat_fluent.models.ChatResponse
import com.example.chat_fluent.models.Choice
import com.example.chat_fluent.models.FeedbackResponse
import com.example.chat_fluent.models.TutorResponse
import com.example.chat_fluent.utilities.ApiResult
import com.example.chat_fluent.utilities.FeedbackApiState
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull.content
import retrofit2.Call
import retrofit2.Response
import kotlin.collections.first

class OpenAIChatRepository(
    private val remoteSource: OpenAIRemoteSource,
    private val promptBuilder: EnglishTutorPrompt,

) : IOpenAIChatRepository {
    val gson = GsonBuilder()
        .setLenient()
        .serializeNulls()
        .create()

    private val apiClient = RetrofitHelper.getInstance()
    private val _conversationHistory = MutableStateFlow<List<Message>>(emptyList())
  //  public val conversationHistory: StateFlow<List<Message>> = _conversationHistory.asStateFlow()
    // Expose read-only StateFlow
    override val conversationHistory: StateFlow<List<Message>> = _conversationHistory.asStateFlow()
    private val _feedbackState = MutableStateFlow<FeedbackApiState>(FeedbackApiState.Loading)
    val feedbackState: StateFlow<FeedbackApiState> = _feedbackState.asStateFlow()
    override fun getConversationHistory(): Flow<List<Message>> = _conversationHistory.asStateFlow()
    override suspend fun initializeChat(topic: String?) {
        // Use empty user input to trigger greeting
        val prompt = promptBuilder.buildPrompt(
            userInput = "",
            topic = topic,
            history = emptyList()
        )
        Log.d("ChatRepo", "Initializing chat with topic: $topic")
        Log.d("ChatRepo", "Prompt: ${prompt.joinToString("\n") { it.content }}")
        val request = ChatRequest(
            messages = prompt,
            model = Constants.OPEN_AI_MODEL
        )
        try {
            apiClient.getTutorResponse(request).enqueue(object : retrofit2.Callback<ChatResponse> {
                override fun onResponse(
                    call: Call<ChatResponse?>,
                    response: Response<ChatResponse?>
                ) {
                    Log.d("ChatRepo", "Response code: ${response.code()}")

                    if (response.isSuccessful) {
                        response.body()?.let { apiResponse ->
                            val tutorResponse = parseTutorResponse(apiResponse)
                            if (tutorResponse != null) {
                                Log.d("ChatRepo", "Parsed response: $tutorResponse")
                                _conversationHistory.update {
                                    listOf(
                                        Message(
                                            role = "assistant",
                                            content = tutorResponse.tutorMessage,
                                            correction = tutorResponse.correction
                                        )
                                    )
                                }
                            } else {
                                Log.e("ChatRepo", "Failed to parse tutor response")
                                showFallbackGreeting()
                            }
                        } ?: run {
                            Log.e("ChatRepo", "Empty response body")
                            showFallbackGreeting()
                        }
                    } else {
                        Log.e("ChatRepo", "API call failed: ${response.errorBody()?.string()}")
                        showFallbackGreeting()
                    }
                }

                override fun onFailure(
                    call: Call<ChatResponse?>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                }

            })


        } catch (e: Exception) {
            Log.e("ChatRepo", "Exception in initializeChat", e)
            showFallbackGreeting()
        }

//                    tutorResponse?.let {
//                        // Add tutor message to history
//                        val tutorMessage = Message(
//                            role = "assistant",
//                            content = it.tutorMessage,
//                            correction = it.correction
//                        )
//                        // Add assistant response to history
//                        _conversationHistory.update { history ->
//                            history + tutorMessage
//                        }
//                    }

//                    val tutorResponse = parseTutorResponse(apiResponse)
//                    tutorResponse?.let {
//                  //  val tutorResponse = parseTutorResponse(apiResponse)
//                    _conversationHistory.update {
//                        listOf(
//                            Message(
//                                role = "assistant",
//                                content = tutorResponse.tutorMessage,
//                                correction = tutorResponse.correction
//                            )
//                        )
//                    }
//                        }

    }

//        catch (e: Exception) {
//            // Fallback greeting
//            _conversationHistory.update {
//                listOf(
//                    Message(
//                        role = "assistant",
//                        content = "Hello! I'm your English tutor. How can I help you practice today?",
//                        correction = null
//                    )
//                )
//            }
//        }


    private fun showFallbackGreeting() {
        _conversationHistory.update {
            listOf(
                Message(
                    role = "assistant",
                    content = "Hello! I'm your English tutor. How can I help you practice today?",
                    correction = null
                )
            )
        }
    }

    override fun getTopicIntroductionPrompt(topic: String): String {
        return promptBuilder.getTopicIntroductionPrompt(topic)

    }

    // 1. Get initial prompt through API
    override suspend fun getInitialPrompt(topic: String?): Result<String> {
        return try {
            val prompt = promptBuilder.buildPrompt(
                userInput = "",
                topic = topic,
                history = emptyList()
            )

            val choices = remoteSource.getTutorResponseFromNetwork(
                ChatRequest(
                    messages = prompt,
                    model = TODO()
                )
            ).first()

            if (choices.isEmpty()) {
                return Result.failure(Exception("Empty response from API"))
            }

            val response = parseResponse(choices.first())
            Result.success(response.tutorMessage)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to get initial prompt: ${e.message}"))
        }
    }

    override suspend fun clearConversation() {
        _conversationHistory.value=emptyList()
    }

    override fun createChatCompelation(message: String) {
        try {
            // Add user message to history
            val userMessage = Message(content = message, role = "user")
            _conversationHistory.update { history ->
                history + userMessage
            }
            val initialMessage = promptBuilder.buildPrompt(
                userInput = message,
                topic = null,
                history = _conversationHistory.value
            )

            // Create request with full history
            val messages = initialMessage
            //this work
//                    listOf(
//                    Message(content = "You are a helpful a chinese assistant, so answer all questions in chinese even if the question is in english.", role = "system")
//                ) + _conversationHistory.value

            val request = ChatRequest(
                messages = ArrayList(messages),
                model = Constants.OPEN_AI_MODEL
            )

//                val request= ChatRequest(arrayListOf(
//                    Message(content = "Hi who are you?", role = "system"),
//                    Message(content = message, role = "user")
//                ),
//                    Constants.OPEN_AI_MODEL
//                    )
            apiClient.getTutorResponse(request).enqueue(object : retrofit2.Callback<ChatResponse> {
                override fun onResponse(
                    call: Call<ChatResponse?>,
                    response: Response<ChatResponse?>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { assistantMessage ->
                            // val parsed = parseResponse(Choice(index = 0,_conversationHistory.value.get(0)))
                            val tutorResponse = parseTutorResponse(assistantMessage)
                                ?: run {
                                    // Fallback response if parsing fails
                                    TutorResponse(
                                        tutorMessage = assistantMessage.choices.first().message.content,
                                        correction = null
                                    )
                                }
                            tutorResponse?.let {
                                // Add tutor message to history
                                val tutorMessage = Message(
                                    role = "assistant",
                                    content = it.tutorMessage,
                                    correction = it.correction
                                )
                                // Add assistant response to history
                                _conversationHistory.update { history ->
                                    history + tutorMessage
                                }
                            }
                            Log.d("ChatHistory", "Updated history: ${_conversationHistory.value}")
                        }
                    }
                    val code = response.code()
                    /*  if (code==200){
                response.body()?.choices?.get(0)?.message?.let {
                    Log.d("message",it.toString())
                }
            }*/
                    if (code == 429) {
                        Log.e(
                            "OpenAITest",
                            "API quota exceeded - check your OpenAI account billing"
                        )
                    } else {
                        Log.d("error", response.errorBody().toString())

                    }
                }

                override fun onFailure(
                    call: Call<ChatResponse?>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                }

            })
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    companion object {
        private var instance: OpenAIChatRepository? = null
        fun getInstance(
            remoteSource: OpenAIRemoteSource,
            promptBuilder: EnglishTutorPrompt

        ): OpenAIChatRepository {
            return instance ?: synchronized(this) {
                val temp = OpenAIChatRepository(remoteSource, promptBuilder)
                instance = temp
                temp
            }
        }

    }

    override suspend fun sendMessage(
        userInput: String,
        topic: String?,
        history: List<String>
    ): Result<TutorResponse> {

        return try {
            val message = promptBuilder.buildPrompt(userInput, topic, emptyList())
            // Make API call through remoteSource
            val choices = remoteSource.getTutorResponseFromNetwork(
                ChatRequest(messages = message)
            ).first()

            if (choices.isEmpty()) {
                return Result.failure(Exception("Empty response from API"))
            }

            val firstChoice = choices.first()
            val jsonResponse = parseResponse(firstChoice)
            updateHistory(userInput, jsonResponse.tutorMessage)
            Result.success(jsonResponse)

        } catch (e: Exception) {
            Result.failure(Exception("API call failed: ${e.message}"))
        }
    }

//        val message = promptBuilder.buildPrompt(userInput, topic, history)
//        // Make API call
//        val response = remoteSource.getTutorResponseFromNetwork(
//            ChatRequest(messages = message)
//        )            // Parse response
//        return response
//
//
//
//    }

    //        return try {
//            val message= promptBuilder.buildPrompt(userInput,topic,history)
//            // Make API call
//            val response = apiService.getTutorResponse(
//                ChatRequest(messages = message)
//            )            // Parse response
//
//            response.body()?.let { apiResponse ->
//                val jsonResponse = parseResponse(apiResponse)
//                updateHistory(userInput, jsonResponse.tutorMessage)
//                Result.success(jsonResponse)
//            } ?: Result.failure(Exception("Empty response"))
//
//        } catch (e: Exception) {
//            Result.failure(Exception("API call failed: ${e.message}"))
//        }
//
//        }
//    private fun parseResponse(response: ChatResponse): TutorResponse {
//        return try {
//            // Try to parse as JSON first
//            Json.decodeFromString<TutorResponse>(response.choices.first().message.content)
//        } catch (e: Exception) {
//            // Fallback to simple text response
//            TutorResponse(
//                tutorMessage = response.choices.first().message.content,
//                correction = null
//            )
//        }
//    }
    private fun parseResponse(choice: Choice): TutorResponse {
        return try {
            // Try to parse as JSON first
            Json.decodeFromString<TutorResponse>(choice.message.content)
        } catch (e: Exception) {
            // Fallback to simple text response
            TutorResponse(
                tutorMessage = choice.message.content,
                correction = null
            )
        }
    }

    private fun parseTutorResponse(chatResponse: ChatResponse): TutorResponse? {
        return try {
            val content = chatResponse.choices.first().message.content.trim()
            // Case 1: Already proper JSON object
            if (content.startsWith("{") && content.endsWith("}")) {
                gson.fromJson(content, TutorResponse::class.java).apply {
                    // Ensure we always have tutorMessage
                    if (tutorMessage.isBlank()) {
                        throw Exception("Empty tutorMessage in JSON response")
                    }
                }
            }
            // Case 2: JSON-like but malformed (common with AI responses)
            else if (content.contains("\"tutorMessage\"") || content.contains("\"correction\"")) {
                try {
                    // Try to repair common JSON issues
                    val repairedJson = content
                        .replace("\\\".*?\\\"".toRegex()) { match ->
                            match.value.replace("\\\"", "\"")
                        }
                        .replace("\\n", "")

                    gson.fromJson(repairedJson, TutorResponse::class.java)
                } catch (e: Exception) {
                    // Fallback to treating as plain text
                    TutorResponse(
                        tutorMessage = content,
                        correction = null
                    )
                }
            }
            // Case 3: Plain text response
            else {
                TutorResponse(
                    tutorMessage = content,
                    correction = null
                )
            }
//            try {
//                // Remove surrounding quotes and unescape characters
//                if (jsonResponse.startsWith("\"") && jsonResponse.endsWith("\"")) {
//                    jsonResponse = content
//                        .removeSurrounding("\"")
//                        .replace("\\\"", "\"")
//                        .replace("\\n", "")
//                }
//
//                gson.fromJson(jsonResponse, TutorResponse::class.java)
//            }catch (jsonWxception: Exception)
//            {
//                TutorResponse(
//                    tutorMessage = jsonResponse,
//                    correction = null
//                )
//            }
        }  catch (e: Exception) {
            Log.e("ParseError", "Failed to parse: ${chatResponse.choices.firstOrNull()?.message?.content}", e)
            TutorResponse(
                tutorMessage = "I couldn't process that. Please try again.",
                correction = null
            )
        }
    }

    private suspend fun updateHistory(userInput: String, tutorMessage: String?) {
//        val newHistory = _conversationHistory.value.toMutableList().apply {
//            add(userInput)
//            tutorMessage?.let { add(it) }
//            // Keep only last 10 exchanges (20 messages)
//            if (size > 20) removeAt(0)
//        }
//        _conversationHistory.emit(newHistory)
//        _conversationHistory.update { history ->
//            (history + userInput + (tutorMessage ?: ""))
//                .takeLast(20) // Keep last 10 exchanges (20 messages)
//        }
    }

    //    override suspend fun generateFeedback(
//        conversationHistory: List<String>,
//        userLevel: String
//    ): Result<FeedbackResponse> {
//        return try {
//            // Build feedback prompt using the FeedbackPrompt object
//            val feedbackPrompt = FeedbackPrompt.build(userLevel, conversationHistory)
//            val messages = listOf(
//                ChatMessage(role = "user", content = feedbackPrompt)
//            )
//
//            val response = apiService.getTutorResponse(ChatRequest(messages = messages))
//
//            response.body()?.let { apiResponse ->
//                val feedbackResponse = parseFeedbackResponse(apiResponse)
//                Result.success(feedbackResponse)
//            } ?: Result.failure(Exception("Empty feedback response"))
//        } catch (e: Exception) {
//            Result.failure(Exception("Feedback generation failed: ${e.message}"))
//        }
//    }
    override suspend fun generateFeedback(
        // conversationHistory: List<Message>,
    ): ApiResult<FeedbackResponse> {
        // _feedbackState.value = FeedbackApiState.Loading
        return try {
            // 1. Build feedback prompt
            val conversationHistory = _conversationHistory.value
                .filter { it.role == "user" }
                .map { it.content }
            // Build feedback prompt
            val feedbackPrompt = FeedbackPrompt.build(conversationHistory)

            // 2. Create request
            val messages = listOf(
                Message(role = "user", content = feedbackPrompt)
            )
            val request = ChatRequest(
                messages = messages,
                model = Constants.OPEN_AI_MODEL
            )

            // 3. Make API call
            val response = apiClient.getTutorResponse(request).execute()
            if (response.isSuccessful) {
                response.body()?.let { apiResponse ->
                    val feedback = parseFeedbackResponse(apiResponse)
                    ApiResult.Success(feedback)
                } ?: ApiResult.Failure(Exception("Empty API response"))
            } else {
                ApiResult.Failure(Exception("API error: ${response.code()}"))
            }
        } catch (e: Exception) {
            ApiResult.Failure(e)
        }
    }


    // Make API call through remoteSource
//        val choices = remoteSource.getTutorResponseFromNetwork(
//            ChatRequest(messages = messages)
//        ).first()
//
//        if (choices.isEmpty()) {
//            return Result.failure(Exception("Empty feedback response"))
//        }
//
//        val feedbackResponse = parseFeedbackResponse(choices.first())
//        Result.success(feedbackResponse)
//    } catch (e: Exception) {
//        Result.failure(Exception("Feedback generation failed: ${e.message}"))
//    }


//    private fun parseFeedbackResponse(response: ChatResponse): FeedbackResponse {
//        return try {
//            // The API response should contain the JSON structure we requested in FeedbackPrompt
//            Json.decodeFromString<FeedbackResponse>(response.choices.first().message.content)
//        } catch (e: Exception) {
//            // Fallback to empty feedback if parsing fails
//            FeedbackResponse(
//                categories = emptyMap(),
//                cefrLevel = "B1",
//                strengths = emptyList(),
//                weaknesses = emptyList(),
//                studyPlan = emptyList()
//            )
//        }
//    }

    private fun parseFeedbackResponse(response: ChatResponse): FeedbackResponse {
        return try {
            val jsonResponse = response.choices.first().message.content
            gson.fromJson(jsonResponse, FeedbackResponse::class.java)
//        val jsonString = response.choices.first().message.content
//        Json.decodeFromString<FeedbackResponse>(jsonString)
        } catch (e: Exception) {
            // Fallback to empty feedback
            FeedbackResponse(
                categories = emptyMap(),
                cefrLevel = "B1",
                strengths = emptyList(),
                weaknesses = emptyList(),
                studyPlan = emptyList()
            )
        }
    }
}

