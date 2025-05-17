package com.example.chat_fluent.data.network.gemini.prompts

import com.example.chat_fluent.models.Message

object EnglishTutorPrompt {
    const val BASE_PROMPT = """
        You are an English tutor. Follow these rules strictly:
        1. Engage in natural conversation
        2.For the first message, greet the user and suggest starting a conversation about %Topic%.
        3. Correct ALL grammar/vocabulary errors and any other errors in user's last message
        4. Ask follow-up questions about %TOPIC%
        5. Return JSON with:
           - Your response
           - Corrections (if errors)
        6. Keep explanations simple
        7. Ask follow-up questions

        Example Response for "I has a apple":
        {
            "tutorMessage": "Oh, you have an apple! What kind is it?",
            "correction": {
                "original": "I has a apple",
                "corrected": "I have an apple",
                "errors": [
                    {
                        "type": "grammar",
                        "incorrect": "has",
                        "correct": "have",
                        "explanation": "'Have' is correct for first-person present"
                    },
                    {
                        "type": "vocabulary",
                        "incorrect": "a apple",
                        "correct": "an apple",
                        "explanation": "Use 'an' before vowel sounds"
                    }
                ]
            }
        }

        Current conversation context:
        %HISTORY%
        
        User's message to analyze:
        "%INPUT%"
    """

    fun initialPrompt(topic: String?): String
    {
        val topicPlaceholder = topic ?: "general daily life"
        return BASE_PROMPT
            .replace("%TOPIC%", topicPlaceholder)
    }

    fun buildPrompt(
        userInput: String,
        topic: String?,
        history: List<Message> = emptyList()
    ): List<Message> {
        val topicContext = topic?.let { "We're focusing on: $it" } ?: "General conversation"

        val messages = mutableListOf<Message>().apply {
            // System message with instructions
            add(Message(
                role = "system",
                content = BASE_PROMPT.replace("%TOPIC%", topicContext)
            ))

            // Add conversation history if exists
            if (history.isNotEmpty()) {
                addAll(history.map {
                    Message(role = it.role, content = it.content)
                })
            }

            // Add current user input if not empty (for initial greeting this will be empty)
            if (userInput.isNotBlank()) {
                add(Message(role = "user", content = userInput))
            }
        }

        return messages
        ///this work 100%
//        val historyContext = history.takeLast(3).joinToString("\n")
//        val topicPlaceholder = topic ?: "general daily life"
//        return listOf(
//            Message(
//                role = "system",
//                content = BASE_PROMPT.replace("%TOPIC%", topicPlaceholder)
//            ),
//            Message(
//                role = "user",
//                content = "Context:\n$historyContext\n\nNew message:\n$userInput"
//            )
//        )


//        return BASE_PROMPT
//            .replace("%HISTORY%", historyContext)
//            .replace("%INPUT%", userInput)
//            .replace("%TOPIC%", topicPlaceholder)
    }

    fun getTopicIntroductionPrompt(topic: String): String {
        return """
            We're now practicing English related to: $topic
            Focus on these areas:
            1. Relevant vocabulary
            2. Common phrases
            3. Typical grammar structures
            4. Cultural context
            
            Ready when you are!
        """.trimIndent()
    }
}


/*object EnglishTutorPrompt {
    // Base identity prompt (runs once at chat start)
    const val TUTOR_IDENTITY = """
        You are an English tutor specializing in language practice. Follow these rules:
        1. Speak naturally but limit responses to 3 sentences
        2. Correct errors implicitly by rephrasing correctly
        3. Ask follow-up questions about %TOPIC%
        4. For grammar checks, use the proofreading schema
    """

    // Proofreading prompt (your original requirement)
    const val PROOFREADING_TEMPLATE = """
        As a proofreader, analyze text delimited by ###. Return JSON:
        {
            "correction": "<corrected_text_or_'---'>",
            "explanation": "<optional_rule_explanation>"
        }
        ###%TEXT###
    """

    fun buildInitialPrompt(topic: String?): String {
        val topicPlaceholder = topic ?: "general daily life"
        return TUTOR_IDENTITY.replace("%TOPIC%", topicPlaceholder)
    }

    fun buildProofreadingPrompt(text: String): String {
        return PROOFREADING_TEMPLATE.replace("%TEXT%", text)
    }
}*/