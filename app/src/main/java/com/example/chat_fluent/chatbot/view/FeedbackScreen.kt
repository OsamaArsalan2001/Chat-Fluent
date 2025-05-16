package com.example.chat_fluent.chatbot.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chat_fluent.models.CategoryFeedback
import com.example.chat_fluent.models.FeedbackResponse

@Composable
fun FeedbackDetailScreen(feedback: FeedbackResponse) {
    Column (Modifier.padding(16.dp)) {
        Text("Your Level: ${feedback.cefrLevel}", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        Text("Strengths:", style = MaterialTheme.typography.titleMedium)
        feedback.strengths.forEach { Text("• $it") }

        Spacer(Modifier.height(16.dp))

        Text("Areas to Improve:", style = MaterialTheme.typography.titleMedium)
        feedback.weaknesses.forEach { Text("• $it") }

        Spacer(Modifier.height(16.dp))

        Text("Study Plan:", style = MaterialTheme.typography.titleMedium)
        feedback.studyPlan.forEach { Text("• $it") }

        Spacer(Modifier.height(16.dp))

        Text("Skill Ratings:", style = MaterialTheme.typography.titleMedium)
        feedback.categories.forEach { (category, details) ->
            SkillRating(category, details)
        }
    }
}

@Composable
private fun SkillRating(name: String, details: CategoryFeedback) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text("$name: ${details.rating}/5", style = MaterialTheme.typography.titleSmall)
            Text(details.feedback)
        }
    }
}