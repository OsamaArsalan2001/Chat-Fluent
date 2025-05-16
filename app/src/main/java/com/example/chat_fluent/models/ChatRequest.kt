package com.example.chat_fluent.models

import com.example.chat_fluent.Constants
import com.google.gson.annotations.SerializedName

data class ChatRequest(
    val messages: List<Message>,
    val model: String=Constants.OPEN_AI_MODEL,
//    @SerializedName("response_format")
//    val responseFormat: Map<String, String> = mapOf("type" to "json_object")
)