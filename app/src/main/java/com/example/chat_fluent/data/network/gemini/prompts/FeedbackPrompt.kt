package com.example.chat_fluent.data.network.gemini.prompts

// data/remote/prompts/FeedbackPrompt.kt
object FeedbackPrompt {

    const val FEEDBACKTEMPLATE ="""Analyze this English practice session and provide detailed feedback.
        User Level: %s (CEFR A1-C2)
        Conversation Context:
        %s
        
        Respond in JSON:
        {
            "categories": {
                "grammar": {"rating": 0-5, "feedback": "...", "examples": []},
                "pronunciation": {"rating": 0-5, "feedback": "...", "examples": []},
                "vocabulary": {"rating": 0-5, "feedback": "...", "examples": []}
                "fluency": {"rating": 0-5, "feedback": "...", "examples": []}
                "listening": {"rating": 0-5, "feedback": "...", "examples": []}
                "coherence": {"rating": 0-5, "feedback": "...", "examples": []}
                
            },
            "cefr_level": "A2",
            "strengths": [],
            "weaknesses": [],
            "study_plan": []
        }"""

    fun build(
        userLevel: String,
        conversationHistory: List<String>
    ): String {
        return FEEDBACKTEMPLATE.format(
            userLevel,
            conversationHistory.takeLast(6).joinToString("\n")
        )
    }
}