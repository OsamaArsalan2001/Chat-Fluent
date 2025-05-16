//package com.example.chat_fluent.chatbot.viewmodel
//
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.chat_fluent.chatbot.GeminiChatRepository
//import com.example.chat_fluent.models.ChatMessage
//import kotlinx.coroutines.launch
//
//class GeminiChatViewModel(private val geminiRepository: GeminiChatRepository): ViewModel() {
//    private val _messages = mutableStateListOf<ChatMessage>()
//    val messages: List<ChatMessage> get() = _messages
//
//
//
//
//
//    private val _isLoading = mutableStateOf(false)
//    val isLoading: Boolean get() = _isLoading.value
//
//    fun startNewSession(topic: String? = null) {
//        viewModelScope.launch {
//            geminiRepository.initializeSession(topic)
//            _messages.clear()
//        }
//    }
//
//    fun sendMessage(message: String) {
//        viewModelScope.launch {
//            _isLoading.value = true
//            try {
//                val newMessages = geminiRepository.getTutorResponse(message)
//                _messages.addAll(newMessages)
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//
//    fun getHistory() = geminiRepository.getConversationHistory()
//}