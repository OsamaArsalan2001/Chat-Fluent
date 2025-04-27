package com.example.chat_fluent.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
// ask instructor about something
@Composable
fun textfieldWidget(){
    var text by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),

        shape = RectangleShape  ,
        value = text ,
        onValueChange = {
                newText -> text = newText
        } ,
        placeholder = {
            Text("Enter your First name")
        },
        leadingIcon = {
            Icon(Icons.Rounded.AccountCircle , contentDescription = "Account Icon")
        } ,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(red = 248 , green = 244 , blue = 252) ,
            unfocusedContainerColor = Color(red = 248 , green = 244 , blue = 252)  ,
            focusedIndicatorColor = Color.Transparent ,
            unfocusedIndicatorColor = Color.Transparent

        ) ,


        )
}