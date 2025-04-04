package com.example.chat_fluent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun signupScreen(navController: NavController){
    Column(
modifier = Modifier.fillMaxWidth() , verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally  ){
        Column(
            modifier = Modifier.statusBarsPadding().navigationBarsPadding().padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)  ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Create Account" ,
                style = TextStyle(
                    fontWeight = FontWeight.Bold ,
                    fontSize = 30.sp


                )



            )


            Text(
                "Create your account to start practicing "
            )
            Text(
                "Your English"
            )

        }

    }

}