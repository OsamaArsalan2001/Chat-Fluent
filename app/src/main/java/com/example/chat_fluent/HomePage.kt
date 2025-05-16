//package com.example.chat_fluent
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.chat_fluent.R
//
//@Composable
//fun HomePageScreen(navController: NavController){
//    Column(modifier = Modifier.fillMaxSize() , verticalArrangement = Arrangement.SpaceAround , horizontalAlignment = Alignment.CenterHorizontally) {
//        Column(verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally) {
//
//            Image(
//                painter = painterResource(R.drawable.photo) ,
//                contentDescription = "Logo"
//
//            )
//            Text(
//                "English Conversation Coach" ,
//                style = TextStyle(
//                    fontSize = 20.sp
//                )
//            )
//        }
//        Column(
//
//
//        ) {
//            Button( shape = RoundedCornerShape(10.dp)   ,
//                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp , vertical = 20.dp) ,
//                colors = ButtonDefaults.buttonColors(Color(alpha = 255 , red = 98 , green = 106 , blue = 231)),  onClick = {
//                    navController.navigate(SignupPage.route)
//                }) {
//                Text("Sign up" , style = TextStyle(
//                    fontSize = 30.sp
//                ))
//            }
//            Button( shape = RoundedCornerShape(10.dp)   , modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp )  , colors = ButtonDefaults.buttonColors(
//                Color( red = 248 , green = 244 , blue = 252 )
//
//            ),     onClick = {
//                navController.navigate(
//                    LoginPage.route
//                )
//
//            }) {
//                Text("Log In" , style = TextStyle(
//                    fontSize = 30.sp ,
//                    color = Color(alpha = 255 , red = 98 , green = 106 , blue = 231)
//                )
//                )
//            }
//        }
//    }
//}
