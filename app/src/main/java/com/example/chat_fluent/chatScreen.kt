package com.example.chat_fluent

import android.inputmethodservice.Keyboard.Row
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun chatscreen(modifier: Modifier, chatviewmodel: chatviewmodel) {

//    Scaffold() {innnerpadding ->
//        apphheader(modifier = Modifier.padding(innnerpadding))
//        messagelist(
//            modifier = Modifier,
//            messagelist = chatviewmodel.messagelist,
//        )
//        BottomAppBar (tonalElevation = 10.dp
//        ) {Bottommessagebar {
//            chatviewmodel.sendquestion(it)
//        }}
////        messageInput(onMessageSend = {
////            chatviewmodel.sendquestion(it)
////        })
//    }
    Scaffold(
        topBar = { apphheader(modifier = Modifier) },
        bottomBar = {
            BottomAppBar(tonalElevation = 10.dp) {
                Bottommessagebar {
                    chatviewmodel.sendquestion(it)
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            messagelist(
                modifier = Modifier.weight(1f),
                messagelist = chatviewmodel.messagelist,
            )
        }
    }
}

@Composable
fun messagelist(modifier: Modifier, messagelist: List<messagemodel>){
    if(messagelist.isEmpty()){
        Column(
            modifier= Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(60.dp),
                painter = painterResource(R.drawable.baseline_question_answer_24),
                contentDescription = "icon")
            Text(text = "lets try chat in english", fontSize = 18.sp)
        }
    }
    else{
        LazyColumn(
            modifier= Modifier,
            reverseLayout = true
        ) {
            items  (messagelist.reversed()){
                messagerow(it)
            }
        }
    }

}

@Composable
fun Bottommessagebar(onMessageSend : (String)-> Unit){
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
                label = { Text("Message") },
                singleLine = true,
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = {
                if(message.isNotEmpty()){
                    onMessageSend(message)
                    message = ""}

            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send ,
                    contentDescription = "send")
            }
        }
    }
}






@Composable
fun messageInput(onMessageSend : (String)-> Unit){
    var message by remember {
        mutableStateOf("")
    }

    Row(modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(

            modifier = Modifier.weight(1f).padding(bottom = 20.dp),
            value = message,
            onValueChange = {
                message = it
            })

        IconButton(onClick = {
            if(message.isNotEmpty()){
                onMessageSend(message)
                message = ""}

        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send ,
                contentDescription = "send")
        }

    }

}


@Composable
fun messagerow(messagemodel: messagemodel){
    val ismodel = messagemodel.role=="model"

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .align(if (ismodel) Alignment.BottomStart else Alignment.BottomEnd)
                    .padding(
                        start = (if (ismodel) 7.dp else 70.dp),
                        end = (if (ismodel) 70.dp else 7.dp),
                        top = 8.dp,
                        bottom = 8.dp

                    )
                    .clip(RoundedCornerShape(48f))
                    .background(if (ismodel) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.secondary)
                    .padding(16.dp)
            ) {
                SelectionContainer {
                    Text(
                        text = messagemodel.message,
                        fontWeight = FontWeight.W500,
                        color = if(ismodel)Color.Black else MaterialTheme.colorScheme.background
                    )
                }
            }
        }
    }
}


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