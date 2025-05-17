package com.example.chat_fluent.chatbot.view

import android.R.attr.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chat_fluent.chatbot.OpenAIChatRepository
import com.example.chat_fluent.chatbot.viewmodel.OpenAIChatViewModel
import com.example.chat_fluent.chatbot.viewmodel.OpenAIViewModelFactory
import com.example.chat_fluent.data.network.gemini.OpenAIClient
import com.example.chat_fluent.data.network.gemini.prompts.EnglishTutorPrompt
import com.example.chat_fluent.models.CategoryFeedback
import com.example.chat_fluent.models.FeedbackResponse
import com.example.chat_fluent.utilities.FeedbackApiState


/*@Composable
fun FeedbackDetailScreen(/*feedback: FeedbackResponse*/) {
    val chatViewModel: OpenAIChatViewModel = viewModel(
        factory = OpenAIViewModelFactory(
            OpenAIChatRepository.getInstance(
                OpenAIClient.getInstance(),
                EnglishTutorPrompt
            )
        )
    )

    val feedbackState by chatViewModel.feedback.collectAsState()
    LaunchedEffect(Unit) {
        chatViewModel.generateFeedback()
    }

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
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(
    onBackClick: () -> Unit,

) {
    val viewModel: OpenAIChatViewModel = viewModel(
    factory = OpenAIViewModelFactory(
        OpenAIChatRepository.getInstance(
            OpenAIClient.getInstance(),
            EnglishTutorPrompt
        )
    )
    )
    val feedbackState by viewModel.feedback.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.generateFeedback()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Feedback") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        when (feedbackState) {
            is FeedbackApiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is FeedbackApiState.Success -> {
                val feedback = (feedbackState as FeedbackApiState.Success).data
                FeedbackContent(
                    feedback = feedback,
                    modifier = Modifier.padding(padding))
            }
            is FeedbackApiState.Failure -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Failed to load feedback")
                }
            }
        }
    }
}

@Composable
private fun FeedbackContent(
    feedback: FeedbackResponse,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
                item {
                    Text(
                        "Your Current Level: ${feedback.cefrLevel}",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                item {
                    FeedbackSection("Strengths", feedback.strengths)
                }

                item {
                    FeedbackSection("Areas to Improve", feedback.weaknesses)
                }

                feedback.categories.forEach { (category, details) ->
                    item {
                        CategoryFeedbackItem(category, details)
                    }
                }

                item {
                    FeedbackSection("Study Plan", feedback.studyPlan)
                }
            }
}


@Composable
private fun FeedbackSection(title: String, items: List<String>) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        items.forEach { item ->
            Text(
                text = "• $item",
                modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
            )
        }

    }
}

@Composable
private fun CategoryFeedbackItem(
    category: String,
    details: CategoryFeedback
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
    {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = category.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                RatingBar(rating = details.rating)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = details.feedback,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (details.examples.isNotEmpty()) {
                Text(
                    text = "Examples:",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                details.examples.forEach { example ->
                    Text(
                        text = "• $example",
                        modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                    )
                }
            }
        }
    }

}

@Composable
private fun RatingBar(rating: Float) {
    Row {
        repeat(5) { index ->
            Icon(
                imageVector = if (index < rating) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = null,
                tint = if (index < rating) Color.Yellow else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}