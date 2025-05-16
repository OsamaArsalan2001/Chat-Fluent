package com.example.chat_fluent.models

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val index:Int,
    val message: Message
)