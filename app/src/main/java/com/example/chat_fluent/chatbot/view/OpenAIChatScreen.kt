package com.example.chat_fluent.chatbot.view

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chat_fluent.R
import com.example.chat_fluent.chatbot.OpenAIChatRepository
import com.example.chat_fluent.chatbot.viewmodel.OpenAIChatViewModel
import com.example.chat_fluent.chatbot.viewmodel.OpenAIViewModelFactory
import com.example.chat_fluent.data.network.gemini.OpenAIClient
import com.example.chat_fluent.data.network.gemini.prompts.EnglishTutorPrompt
import com.example.chat_fluent.models.CategoryFeedback
import com.example.chat_fluent.models.Message
import com.example.chat_fluent.models.FeedbackResponse
import com.example.chat_fluent.ui.theme.LightBlue
import com.example.chat_fluent.utilities.ChatApiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenAIChatScreen(
    onFeedbackClick: () -> Unit,
    onBackClick: () -> Unit,
    topic: String? = null) {
   // lateinit var chatViewModel: OpenAIChatViewModel
  //  lateinit var chatViewModelFactory: OpenAIViewModelFactory


//    chatViewModelFactory= OpenAIViewModelFactory(OpenAIChatRepository.getInstance(
//        OpenAIClient.getInstance(),EnglishTutorPrompt))

    //chatViewModel = viewModel(factory = chatViewModelFactory)

    // Initialize ViewModel with factory
    val chatViewModel: OpenAIChatViewModel = viewModel(
        factory = OpenAIViewModelFactory(
            OpenAIChatRepository.getInstance(
                OpenAIClient.getInstance(),
                EnglishTutorPrompt
            )
        )
    )

    //Collect state from ViewModel
   // val messagesState by chatViewModel.messages.collectAsState()
   // val feedbackState by chatViewModel.feedback.collectAsState()
   // var showFeedbackDialog by remember { mutableStateOf(false) }

    // Initialize topic if provided
//    LaunchedEffect(topic) {
//        topic?.let { chatViewModel.setTopic(it) }
//    }
// Show feedback dialog when feedback is available
//    LaunchedEffect(feedbackState) {
//        if (feedbackState is FeedbackApiState.Success) {
//            showFeedbackDialog = true
//        }
//    }
    // Handle feedback dialog state

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
                    IconButton(onClick = {
                       // chatViewModel.generateFeedback()
                        onFeedbackClick()
                    }) {
                        Text("Feedback")
                    }
                }
//                    // Show loading indicator when feedback is being generated
//                    if (feedbackState is FeedbackApiState.Loading) {
//                        Text(text = "Yes I am loading")
//                       // CircularProgressIndicator(modifier = Modifier.size(24.dp))
//                    }
//                    else {
//                        TextButton(
//                            onClick = { chatViewModel.generateFeedback() },
//                            enabled = messagesState is ChatApiState.Success &&
//                                    (messagesState as ChatApiState.Success).data.isNotEmpty(),
////                            modifier = TODO(),
////                            shape = TODO(),
////                            colors = TODO(),
////                            elevation = TODO(),
////                            border = TODO(),
////                            contentPadding = TODO(),
////                            interactionSource = TODO(),
////                            content = TODO(),
//                        )
//                        {Text("Get feedback")}
////                        TextButton (onClick = {chatViewModel.generateFeedback()})
////                        {
////                            Text("Get feedback")
////                        }
//                    }
//
//                },

                )
        },
        content =  { padding ->
//            when (messagesState) {
//                is ChatApiState.Loading -> CenterLoadingIndicator()
//                is ChatApiState.Success ->{
//                    val messages = (messagesState as ChatApiState.Success).data
//                    if (messages.isEmpty()) {
//                        EmptyChatPlaceholder()
//                    } else {
//                        MessageList(messages = messages, modifier = Modifier.padding(padding))
//                    }
//                }
////                MessageList(
////                    messages = (messagesState as ChatApiState.Success).data,
////                    modifier = Modifier.padding(padding) )
//                is ChatApiState.Failure -> ErrorMessage((messagesState as ChatApiState.Failure).msg.message ?: "Error loading chat")
//            }
        },
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding)
//            ) {
//                // Show loading or error states
//                when (messagesState) {
//                    is ChatApiState.Loading -> {
//                        Box(
//                            modifier = Modifier.fillMaxSize(),
//                            contentAlignment = Alignment.Center
//                        ) {
//                         //   CircularProgressIndicator()
//                            Text("Yes chat loading")
//                        }
//                    }
//                    is ChatApiState.Failure -> {
//                        Box(
//                            modifier = Modifier.fillMaxSize(),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                                Text(
//                                    text = "Error loading conversation",
//                                    color = MaterialTheme.colorScheme.error
//                                )
//                                Text(
//                                    text = (messagesState as ChatApiState.Failure).msg.message ?: "Unknown error",
//                                    color = MaterialTheme.colorScheme.error
//                                )
//                                Button(onClick = { /* Retry logic if needed */ }) {
//                                    Text("Retry")
//                                }
//                            }
//                           // Text("Error: ${(messagesState as ChatApiState.Failure).error.message}")
//                        }
//                    }
//                    is ChatApiState.Success -> {
//                        val messages = (messagesState as ChatApiState.Success).data
//                        messagelist(
//                            modifier = Modifier.weight(1f),
//                            messages = messages
//                        )
//                    }
//
//                }
//            }
//        },
        bottomBar = {
            BottomMessagebar(
               // enabled = messagesState !is ChatApiState.Loading,
                onMessageSend = {
                //chatViewModel.sendMessage(it)
                    chatViewModel.createChatCompelation(it)
            })
        }
    )
    //FEEDBACK part
//    if (showFeedbackDialog) {
//        when (feedbackState) {
//            is FeedbackApiState.Success -> {
//                FeedbackDialog(
//                    feedback = (feedbackState as FeedbackApiState.Success).data,
//                    onDismiss = { showFeedbackDialog = false }
//                )
//            }
//            is FeedbackApiState.Failure -> {
//                AlertDialog(
//                    onDismissRequest = { showFeedbackDialog = false },
//                    title = { Text("Feedback Error") },
//                    text = {
//                        Text((feedbackState as FeedbackApiState.Failure).msg.message ?: "Failed to generate feedback")
//                    },
//                    confirmButton = {
//                        Button(onClick = { showFeedbackDialog = false }) {
//                            Text("OK")
//                        }
//                    }
//                )
//            }
//            FeedbackApiState.Loading -> {
//                // This case shouldn't happen since we only show dialog on Success
//            }
//        }
//    }

    //{
//        apphheader(modifier = Modifier.padding(innnerpadding))
//        messagelist(
//            modifier = Modifier.padding(innnerpadding),
//            messagelist = chatviewmodel.messagelist,
//        )
//        BottomAppBar  {Bottommessagebar {
//            chatviewmodel.sendquestion(it)
//        }}
//        messageInput(onMessageSend = {
//            chatviewmodel.sendquestion(it)
//        })
    //   }
}

//@Composable
//fun messagelist(modifier: Modifier, messages: List<ChatMessage>){
//    if(messages.isEmpty()){
//        Column(
//            modifier= Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Icon(
//                modifier = Modifier.size(60.dp),
//                painter = painterResource(R.drawable.baseline_question_answer_24),
//                contentDescription = "icon")
//            Text(text = "lets try chat in english", fontSize = 18.sp)
//        }
//    }
//    else{
//        LazyColumn(
//            modifier= modifier,
//            reverseLayout = true
//        ) {
//            items  (messages.reversed()){
//               // messagerow(it)
//                MessageBubble(it)
//            }
//        }
//    }
//
//}

@Composable
fun BottomMessagebar(onMessageSend: (String) -> Unit,/* enabled: Boolean*/){
    var message by remember {
        mutableStateOf("")}

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        ) {
        Row (

            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier.weight(1f),
                label = { Text("Type your message") },
                singleLine = false,
                maxLines = 3
            )
            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = {
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.android_microphone),
                    contentDescription = "send voice ",
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = {
                if(message.isNotEmpty()){
                    onMessageSend(message)
                    message = ""}

            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send ,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Send message")
            }

        }
    }
}
//@Composable
//private fun MessageList(modifier: Modifier,messages: List<ChatMessage>) {
//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//        reverseLayout = true
//    ) {
//        items(messages.reversed()) { message ->
//            MessageBubble(message = message)
//        }
//    }
//}
@Composable
private fun MessageList(messages: List<Message>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(messages) { message ->
            when (message.role) {
                "user" -> UserMessage(message.content)
                "model" -> TutorMessage(message.content)
                "system" -> CorrectionMessage(message.content)
            }
        }
    }
}
@Composable
private fun UserMessage(text: String) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(text)
    }
}

@Composable
private fun TutorMessage(text: String) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Blue, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(text, color = Color.White)
    }
}
@Composable
private fun EmptyChatPlaceholder() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            painter = painterResource(R.drawable.baseline_question_answer_24),
            contentDescription = "Chat icon",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Let's practice English!",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
@Composable
private fun CorrectionMessage(text: String) {
    Text(
        text = "✏️ $text",
        color = Color.Red,
        modifier = Modifier.padding(8.dp)
    )
}
//
//
//
//@Composable
//fun MessageInputBar(onMessageSend : (String)-> Unit){
//    var message by remember {
//        mutableStateOf("")
//    }
//
//    Row(modifier = Modifier.padding(8.dp),
//        verticalAlignment = Alignment.CenterVertically) {
//        OutlinedTextField(
//
//            modifier = Modifier
//                .weight(1f)
//                .padding(bottom = 20.dp),
//            value = message,
//            onValueChange = {
//                message = it
//            })
//
//        IconButton(onClick = {
//            if(message.isNotEmpty()){
//                onMessageSend(message)
//                message = ""}
//
//        }) {
//            Icon(
//                imageVector = Icons.AutoMirrored.Filled.Send ,
//                contentDescription = "send")
//        }
//
//    }
//
//}
@Composable
private fun MessageBubble(message: Message) {
    val isModel = message.role == "model"
    val bubbleColor = if (isModel) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.secondary
    val textColor = if (isModel) MaterialTheme.colorScheme.onPrimary
    else MaterialTheme.colorScheme.onSecondary

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isModel) Arrangement.Start else Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(bubbleColor)
                .padding(16.dp)
        ) {
            SelectionContainer {
                Text(
                    text = message.content,
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun messagerow(message: Message){
    val isModel = message.role=="model"

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .align(if (isModel) Alignment.BottomStart else Alignment.BottomEnd)
                    .padding(
                        start = (if (isModel) 7.dp else 70.dp),
                        end = (if (isModel) 70.dp else 7.dp),
                        top = 8.dp,
                        bottom = 8.dp

                    )
                    .clip(RoundedCornerShape(48f))
                    .background(if (isModel) Color.Blue else LightBlue)
                    .padding(16.dp)
            ) {
                SelectionContainer {
                    Text(
                        text = message.content,
                        fontWeight = FontWeight.W500,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun FeedbackSection(title: String, items: List<String>) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        items.forEach { item ->
            Text(
                text = "• $item",
                modifier = Modifier.padding(start = 16.dp))

        }
    }
}

@Composable
private fun FeedbackDialog(
    feedback: FeedbackResponse,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Your English Feedback",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                // Overall Level
                FeedbackSection(
                    title = "Overall English Level",
                    items = listOf("CEFR Level: ${feedback.cefrLevel}")
                )

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Strengths
                if (feedback.strengths.isNotEmpty()) {
                    FeedbackSection(
                        title = "Your Strengths",
                        items = feedback.strengths
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }

                // Weaknesses
                if (feedback.weaknesses.isNotEmpty()) {
                    FeedbackSection(
                        title = "Areas for Improvement",
                        items = feedback.weaknesses
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }

                // Study Plan
                if (feedback.studyPlan.isNotEmpty()) {
                    FeedbackSection(
                        title = "Recommended Study Plan",
                        items = feedback.studyPlan
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }

                // Detailed Categories
                Text(
                    text = "Detailed Evaluation",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                feedback.categories.forEach { (categoryName, categoryFeedback) ->
                    FeedbackCategoryItem(
                        categoryName = categoryName,
                        categoryFeedback = categoryFeedback,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Got it!")
            }
        },
        modifier = Modifier
            .padding(16.dp)
            .widthIn(max = 600.dp)
    )
}

@Composable
private fun FeedbackCategoryItem(
    categoryName: String,
    categoryFeedback: CategoryFeedback,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Category Header with Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Text(
                    text = categoryName.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )

                RatingBar(
                    rating = categoryFeedback.rating,
                    modifier = Modifier.width(120.dp)
                )
            }

            // Feedback Text
            Text(
                text = categoryFeedback.feedback,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Examples if available
            if (categoryFeedback.examples.isNotEmpty()) {
                Text(
                    text = "Examples:",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Column(modifier = Modifier.padding(start = 8.dp, top = 4.dp)) {
                    categoryFeedback.examples.forEach { example ->
                        Text(
                            text = "• $example",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RatingBar(
    rating: Float,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = "%.1f".format(rating),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 4.dp)
        )
        Row {
            repeat(5) { index ->
                val fillAmount = when {
                    rating >= index + 1 -> 1f
                    rating > index -> rating - index
                    else -> 0f
                }
                Icon(
                    //Icons.Default.Star,
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = if (fillAmount > 0) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    }
                )
            }
        }
    }
}

@Composable
private fun FeedbackSection(
    title: String,
    items: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Column(modifier = Modifier.padding(start = 8.dp)) {
            items.forEach { item ->
                Text(
                    text = "• $item",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}
@Composable
private fun CenterLoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
@Composable
private fun ErrorMessage(message: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Error,
                contentDescription = "Error",
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message ?: "An error occurred",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
/*
@Composable
fun apphheader(modifier: Modifier) {
Box(
modifier = Modifier
.fillMaxWidth()
.background(MaterialTheme.colorScheme.primary)
) {
Text(
modifier = Modifier.padding(16.dp),
text = "chat",
color = Color.White,
fontSize = 12.sp


)
}

}
*/