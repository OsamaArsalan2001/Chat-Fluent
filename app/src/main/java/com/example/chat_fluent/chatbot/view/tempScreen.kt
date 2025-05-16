package com.example.chat_fluent.chatbot.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chat_fluent.chatbot.OpenAIChatRepository
import com.example.chat_fluent.chatbot.viewmodel.OpenAIChatViewModel
import com.example.chat_fluent.chatbot.viewmodel.OpenAIViewModelFactory
import com.example.chat_fluent.data.network.gemini.OpenAIClient
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.chat_fluent.data.network.gemini.prompts.EnglishTutorPrompt
import com.example.chat_fluent.models.Message

@Composable
fun OpenAITestScreen() {
    val chatViewModel: OpenAIChatViewModel = viewModel(
        factory = OpenAIViewModelFactory(
            OpenAIChatRepository.getInstance(
                OpenAIClient.getInstance(),
                EnglishTutorPrompt
            )
        )
    )

    var message by remember { mutableStateOf("") }
    val conversationHistory by chatViewModel.conversationHistory.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        // Chat messages list
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true
        ) {
            items(conversationHistory.reversed()) {
                MessageBubble2(message = it)
            }
        }

        // Input field and send button
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxWidth()
        ){
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Type your message") },
            singleLine = false,
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (message.isNotEmpty()) {
                    Log.d("OpenAITest", "Sending message: $message")
                    chatViewModel.createChatCompelation(message)
                    message = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Send")
        }
    }
}
}
@Composable
fun MessageBubble2(message: Message) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(
                color = when (message.role) {
                    "user" -> Color.LightGray
                    else -> Color.Blue
                },
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            text = message.content,
            modifier = Modifier.padding(16.dp),
            color = when (message.role) {
                "user" -> Color.Black
                else -> Color.White
            }
        )
    }
}
