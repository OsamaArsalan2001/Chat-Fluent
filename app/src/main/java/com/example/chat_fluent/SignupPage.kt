package com.example.chat_fluent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun signupScreen(navController: NavController){
    Column(
modifier = Modifier.fillMaxWidth() , horizontalAlignment = Alignment.CenterHorizontally
) { Text("Hello this is will be Sign up ") }

}