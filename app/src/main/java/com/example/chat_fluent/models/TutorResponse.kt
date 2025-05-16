package com.example.chat_fluent.models

import com.google.gson.annotations.SerializedName

//@Serializable
data class TutorResponse(
    @SerializedName("tutorMessage")
    val tutorMessage: String,       // Natural language response
    @SerializedName("correction")
    val correction: Correction?     // Null if no errors
)

//@Serializable
data class Correction(
    val original: String,           // Original user input
    val corrected: String,
    val errors: List<LanguageError>
)

//@Serializable
data class LanguageError(
    val type: String,              // "grammar" or "vocabulary"
    val incorrect: String,         // Specific incorrect part
    val correct: String,           // Suggested correction
    val explanation: String        // Simple rule explanation
)
