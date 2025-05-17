package com.example.chat_fluent.chatbot.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chat_fluent.chatbot.IOpenAIChatRepository
import com.example.chat_fluent.models.Message
import com.example.chat_fluent.utilities.ApiResult
import com.example.chat_fluent.utilities.ChatApiState
import com.example.chat_fluent.utilities.FeedbackApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OpenAIChatViewModel(private val openAIChatRepository: IOpenAIChatRepository): ViewModel() {
   // private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    //val messages: StateFlow<List<ChatMessage>> = _messages


    private val _messages : MutableStateFlow<ChatApiState> = MutableStateFlow(ChatApiState.Loading)
    val messages: StateFlow<ChatApiState>
        get() = _messages

    private val _feedback: MutableStateFlow<FeedbackApiState> = MutableStateFlow(FeedbackApiState.Loading)
    val feedback: StateFlow<FeedbackApiState>
        get() = _feedback

    private val _conversationHistory = MutableStateFlow<List<Message>>(emptyList())
    val conversationHistory: StateFlow<List<Message>> = _conversationHistory

    //  val conversationHistory = openAIChatRepository.getConversationHistory()

    private var currentTopic: String? = null
    private var userLevel: String = "B1" // Default level, can be changed
    init {
        viewModelScope.launch {
            openAIChatRepository.getConversationHistory().collect { messages ->
                _conversationHistory.value = messages
            }
        }
       // sendInitialPrompt()
    }

    fun startChatSession(topic: String? = null) {
        viewModelScope.launch {
            openAIChatRepository.initializeChat(topic)
        }
    }
    fun createChatCompelation(message: String)
    {
        openAIChatRepository.createChatCompelation(message)
    }
    fun generateFeedback() {
        _feedback.value = FeedbackApiState.Loading
        viewModelScope.launch {
            val result = openAIChatRepository.generateFeedback()
            when (result) {
                is ApiResult.Success -> {
                    _feedback.value = FeedbackApiState.Success(result.data)
                }

                is ApiResult.Failure -> {
                    _feedback.value = FeedbackApiState.Failure(result.exception)
                }
            }
        }
    }
//    private fun sendInitialPrompt(topic: String? = null) {
//        viewModelScope.launch {
//            _messages.value = ChatApiState.Loading
//            when (val result = openAIChatRepository.getInitialPrompt(topic)) {
//                is Result.Success -> {
//                    _messages.value = ChatApiState.Success(listOf(
//                        ChatMessage("model", result.value)
//                    ))
//                }
//                is Result.Failure -> {
//                    _messages.value = ChatApiState.Failure(result.exception)
//                }
//            }
//        }
//        viewModelScope.launch {
//            _messages.value = ChatApiState.Loading
//            try {
//                println("InitialSendMessage: Success")
//
//                val prompt = openAIChatRepository.getTopicIntroductionPrompt(topic ?: "general English")
//                _messages.value = ChatApiState.Success(listOf(
//                    ChatMessage("model", prompt)
//                ))
//                Log.i("CHAT", "InitialMessage: Success")
//
//            } catch (e: Exception) {
//                Log.i("CHAT", "InitialMessage: Failed")
//                println("InitialSendMessage: Failed")
//
//                _messages.value = ChatApiState.Failure(e)
//            }
//        }
    }
//    fun setTopic(topic: String?) {
//        currentTopic = topic
//        // Initialize conversation with topic introduction if needed
//        viewModelScope.launch {
//            try {
//                val topicIntroduction = openAIChatRepository.getTopicIntroductionPrompt(topic ?: "general conversation")
//                val initialMessage = Message(role = "model", content = topicIntroduction)
//                _messages.value = ChatApiState.Success(listOf(initialMessage))
//
//            } catch (e: Exception) {
//                _messages.value = ChatApiState.Failure(e)
//                _messages.value = ChatApiState.Success(emptyList())
//            }
//        }
//    }
//
//    fun setUserLevel(level: String) {
//        userLevel = level
//    }
//    fun sendMessage(userInput:String) {
//        if (userInput.isBlank()) return
//
//        val currentMessages = (_messages.value as? ChatApiState.Success)?.data ?: emptyList()
//        _messages.value = ChatApiState.Loading
//
//        viewModelScope.launch {
//            try {
//                // Add user message immediately
//                val userMessage = Message(role = "user", content = userInput)
//                val updatedMessages = currentMessages + userMessage
//                //_messages.value = ChatApiState.Success(messagesWithUser)
//
//                val result = openAIChatRepository.sendMessage(
//                    userInput = userInput,
//                    topic = null,
//                    history = updatedMessages.map { it.content }
//                )
//
//                result.onSuccess { response ->
//                    Log.i("CHAT", "SendMessage: Success")
//                    println("SendMessage: Success")
//
//                    val newMessages = updatedMessages.toMutableList().apply {
//                        add(Message("model", response.tutorMessage))
//                        response.correction?.let {
//                            add(Message("system", "Correction: ${it.corrected}"))
//                        }
//                    }
////                    val tutorMessage = ChatMessage(role = "model", content = response.tutorMessage)
////                    val updatedMessages = messagesWithUser + tutorMessage
//                    _messages.value = ChatApiState.Success(newMessages)
//
////                    val newMessages = currentMessages + listOf(
////                        ChatMessage(role = "user", content = userInput),
////                        ChatMessage(role = "model",response.tutorMessage)
////                    )
////                    _messages.value = ApiState.Success(newMessages)
//                }.onFailure { error ->
//                    Log.i("CHAT", "SendMessage: Failed")
//                    println("SendMessage: Failed")
//
//
//                    _messages.value = ChatApiState.Failure(error)
//                   // _messages.value = ChatApiState.Success(currentMessages) // Revert
//                }
//            } catch (e: Exception) {
//                _messages.value = ChatApiState.Failure(e)
//                _messages.value = ChatApiState.Success(currentMessages) // Revert
//            }
//        }
//    }
//    fun generateFeedback() {
//        viewModelScope.launch {
//            val messages = (_messages.value as? ChatApiState.Success)?.data ?: return@launch
//            _feedback.value = FeedbackApiState.Loading
//
//            try {
//                val result = openAIChatRepository.generateFeedback(
//                    conversationHistory = messages.map { it.content },
//                    userLevel = "B1" // Default level
//                )
//                _feedback.value = result.fold(
//                    onSuccess = { FeedbackApiState.Success(it) },
//                    onFailure = { FeedbackApiState.Failure(it) }
//                )
//            } catch (e: Exception) {
//                _feedback.value = FeedbackApiState.Failure(e)
//            }
//        }
////        val currentMessages = (_messages.value as? ChatApiState.Success)?.data ?: emptyList()
////        if (currentMessages.isEmpty()) return
////
////        _feedback.value = FeedbackApiState.Loading
////
////        viewModelScope.launch {
////            try {
////                val conversationHistory = currentMessages.map { it.content }
////                val result = openAIChatRepository.generateFeedback(
////                    conversationHistory = conversationHistory,
////                    userLevel = userLevel
////                )
////
////                result.onSuccess { feedbackResponse ->
////                    _feedback.value = FeedbackApiState.Success(feedbackResponse)
////                }.onFailure { error ->
////                    _feedback.value = FeedbackApiState.Failure(error)
////                }
////            } catch (e: Exception) {
////                _feedback.value = FeedbackApiState.Failure(e)
////            }
////        }
//    }
//
//    fun clearConversation() {
//        _messages.value = ChatApiState.Success(emptyList())
//        _feedback.value = FeedbackApiState.Loading
//        currentTopic = null
//    }

//}