package com.example.chat_fluent.chatbot.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.chat_fluent.OpenAIChatScreen
import com.example.chat_fluent.data.network.gemini.prompts.EnglishTutorPrompt
import com.example.chat_fluent.models.Correction
import com.example.chat_fluent.models.Message

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenAITestScreen(topic: String? = null,
                     onFeedbackClick: () -> Unit,
                     onBackClick: () -> Unit,) {
    val chatViewModel: OpenAIChatViewModel = viewModel(
        factory = OpenAIViewModelFactory(
            OpenAIChatRepository.getInstance(
                OpenAIClient.getInstance(),
                EnglishTutorPrompt
            )
        )
    )
    Log.d("ChatRepo", "OpenAITestScreen chat with topic: $topic")


    var message by remember { mutableStateOf("") }
    val conversationHistory by chatViewModel.conversationHistory.collectAsState()
    // Create and remember the LazyListState
    val listState = rememberLazyListState()

    // Scroll to bottom when conversation history changes
    LaunchedEffect(conversationHistory.size) {
        if (conversationHistory.isNotEmpty()) {
            listState.scrollToItem(0)
        }
    }
    LaunchedEffect(Unit) {
        if (conversationHistory.isEmpty()) {
            chatViewModel.startChatSession(topic)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("chat screen")},
                modifier = Modifier,
                navigationIcon = { IconButton(onClick = onBackClick)
                {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                    )
                } },
                actions = {
                    Button(onClick = {
                        onFeedbackClick()
                    }) {
                        Text("Get Feedback")
                    }

                })
        },
        content = {
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
                    state = listState,  // Attach the state
                    reverseLayout = true
                ) {
                    items(conversationHistory.reversed()) {
                        MessageBubble2(message = it)
                    }
                }

                // Input field and send button
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = message,
                        onValueChange = { message = it },
                        modifier = Modifier.weight(1f),
                        label = { Text("Type your message") },
                        singleLine = false,
                        maxLines = 3
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    IconButton(
                        onClick = {
                            if (message.isNotEmpty()) {
                                Log.d("OpenAITest", "Sending message: $message")
                                chatViewModel.createChatCompelation(message)
                                message = ""
                            }
                        },
                        // modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "Send message"
                        )
                    }
                    //Text("Send")
                }
            }
        }
    )

}

@Composable
fun MessageBubble2(message: Message) {
    val isUser = message.role=="user"
    Row(
        modifier = Modifier.fillMaxWidth(),
       // verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Column (modifier = Modifier
            .padding(
                start = (if (!isUser) 7.dp else 70.dp),
                end = (if (!isUser) 70.dp else 7.dp),
                top = 8.dp,
                bottom = 8.dp

            )) {

            Box(
                modifier = Modifier
                   // .align(if (!isUser) Alignment.BottomStart else Alignment.BottomEnd)
                    .clip(RoundedCornerShape(48f))
                    //.fillMaxWidth()
                    .background(
                        color = when (message.role) {
                            "user" -> MaterialTheme.colorScheme.secondary
                            else -> MaterialTheme.colorScheme.primary
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text(
                    //  text =if(message.correction!=null) message.content+"\n"+CorrectionView(correction = message.correction) else message.content,
                    text = message.content,
                    modifier = Modifier.padding(16.dp),
                    color = when (message.role) {
                        "user" -> Color.Black
                        else -> Color.White
                    }
                )
            }
                Spacer(modifier = Modifier.height(20.dp))

                message.correction?.let { correction ->
                    CorrectionView(correction = correction)
                }
            }
        }
    }

@Composable
fun CorrectionView(correction: Correction?) {
    correction?.let { nonNullCorrection ->
        nonNullCorrection.errors?.let { errors ->
            if (errors.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red.copy(alpha = 0.1f))
                        .padding(8.dp)
                ) {
                    Text("✏️ Correction", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Original: ${correction.original}", color = Color.Red)
                    Text("Corrected: ${correction.corrected}", color = Color.Green)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Mistakes found:", fontWeight = FontWeight.Bold)
                    correction.errors.forEach { error ->
                        Column(modifier = Modifier.padding(4.dp)) {
                            Text("Type: ${error.type}")
                            Text("${error.incorrect} → ${error.correct}")
                            Text(error.explanation, fontStyle = FontStyle.Italic)
                        }
                    }
                }
            }

        }
    }
    }
