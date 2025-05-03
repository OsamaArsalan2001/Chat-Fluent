package com.example.chat_fluent.models

data class ProofreadingResponse(
    val correction: String,
    val explanation: String? = null
)
