package com.example.chat_fluent.models

data class Message(
    val content: String,
    val role: String,  // "user" or "model"
    //val timestamp: Long = System.currentTimeMillis()  // For sorting
   // val errors: List<LanguageError> = emptyList()  // Linked corrections

)
