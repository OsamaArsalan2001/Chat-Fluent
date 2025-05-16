package com.example.chat_fluent.models

data class CategoryFeedback(
    val rating: Float,           // 0-5 scale
    val feedback: String,        // Specific explanation
    val examples: List<String>   // Correct/incorrect examples
)

//data class ComprehensiveFeedback(
//    // Detailed Category Feedback
//    val grammar: CategoryFeedback,
//    val pronunciation: CategoryFeedback,
//    val vocabulary: CategoryFeedback,
//    val fluency: CategoryFeedback,
//    val listening: CategoryFeedback,
//    val coherence: CategoryFeedback,
//
//    // General Evaluation
//    val generalFeedback: String,
//    val cefrLevel: String,      // A1, A2, B1, B2, C1, C2
//    val strengths: List<String>,
//    val weaknesses: List<String>,
//    val studyPlan: List<String> // Suggested exercises
//)

data class FeedbackResponse(
    val categories: Map<String, CategoryFeedback>,
    val cefrLevel: String,
    val strengths: List<String>,
    val weaknesses: List<String>,
    val studyPlan: List<String>
)
