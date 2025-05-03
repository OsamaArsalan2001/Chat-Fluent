package com.example.chat_fluent.models

data class ChatMessage(
    val message: String,
    val role: String,  // "user" or "model"
    //val timestamp: Long = System.currentTimeMillis()  // For sorting
    val errors: List<LanguageError> = emptyList()  // Linked corrections

)
