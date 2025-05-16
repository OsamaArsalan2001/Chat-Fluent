//package com.example.chat_fluent.chatbot.view
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import com.example.chat_fluent.R
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.Send
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.chat_fluent.chatbot.viewmodel.GeminiChatViewModel
//import com.example.chat_fluent.models.ChatMessage
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//
//@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
//@Composable
//fun GeminiChatScreen(
//    modifier: Modifier = Modifier,
//    viewModel: GeminiChatViewModel = viewModel()
//) {
//    //val messages by viewModel.messages
//    var userInput by remember { mutableStateOf("") }
//
//    Scaffold(
//        topBar = { AppHeader(modifier = Modifier) },
//        bottomBar = {
//            BottomMessageBar(
//                message = userInput,
//                onMessageChange = { userInput = it },
//                onSend = {
//                    viewModel.sendMessage(it)
//                    userInput = ""
//                }
//            )
//        }
//    ) { innerPadding ->
//        MessageList(
//            modifier = Modifier.padding(innerPadding),
//            messages = viewModel.messages
//        )
//    }
//}
//
//@Composable
//fun MessageList(
//    modifier: Modifier = Modifier,
//    messages: List<ChatMessage>
//) {
//    if (messages.isEmpty()) {
//        EmptyChatPlaceholder()
//    } else {
//        LazyColumn(
//            modifier = modifier,
//            reverseLayout = true
//        ) {
//            items  (messages.reversed()){
//                MessageRow(it)
//            }
//        }
//    }
//}
//
//@Composable
//fun MessageRow(message: ChatMessage) {
//    val isModel = message.role == "model"
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 8.dp, vertical = 4.dp),
//        horizontalArrangement = if (isModel) Arrangement.Start else Arrangement.End
//    ) {
//        Box(
//            modifier = Modifier
//                .clip(RoundedCornerShape(16.dp))
//                .background(
//                    color = if (isModel) MaterialTheme.colorScheme.secondary
//                    else MaterialTheme.colorScheme.primary
//                )
//                .padding(16.dp)
//        ) {
//            Column {
//                Text(
//                    text = message.content,
//                    color = if (isModel) MaterialTheme.colorScheme.onSecondary
//                    else MaterialTheme.colorScheme.onPrimary
//                )
//                if (message.errors.isNotEmpty()) {
//                    Spacer(modifier = Modifier.height(4.dp))
//                    message.errors.forEach { correction ->
//                        Text(
//                            text = "${correction.type}: ${correction.explanation}",
//                            color = Color.Gray,
//                            fontSize = 12.sp
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun BottomMessageBar(
//    message: String,
//    onMessageChange: (String) -> Unit,
//    onSend: (String) -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        OutlinedTextField(
//            value = message,
//            onValueChange = onMessageChange,
//            modifier = Modifier.weight(1f),
//            label = { Text("Type your message") },
//            singleLine = false
//        )
//
//        IconButton(
//            onClick = { if (message.isNotBlank()) onSend(message) },
//            enabled = message.isNotBlank()
//        ) {
//            Icon(
//                imageVector = Icons.AutoMirrored.Filled.Send,
//                contentDescription = "Send message"
//            )
//        }
//    }
//}
//
//@Composable
//fun EmptyChatPlaceholder() {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Icon(
//            modifier = Modifier.size(60.dp),
//            painter = painterResource(R.drawable.baseline_question_answer_24),
//            contentDescription = "Chat icon"
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(
//            text = "Let's start chatting in English!",
//            fontSize = 18.sp
//        )
//    }
//}
//
//@Composable
//fun AppHeader(modifier: Modifier) {
//    Box(
//        modifier = modifier
//            .fillMaxWidth()
//            .background(MaterialTheme.colorScheme.primary)
//    ) {
//        Text(
//            modifier = Modifier.padding(16.dp),
//            text = "English Chat Practice",
//            color = Color.White,
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Bold
//        )
//    }
//}