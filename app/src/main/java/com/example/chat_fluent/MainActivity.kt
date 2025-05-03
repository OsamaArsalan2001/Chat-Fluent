package com.example.chat_fluent

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chat_fluent.chatbot.ChatRepository
import com.example.chat_fluent.chatbot.view.GeminiChatScreen
import com.example.chat_fluent.chatbot.viewmodel.GeminiChatViewModel
import com.example.chat_fluent.data.network.gemini.GeminiApiService
import com.example.chat_fluent.nav.MainScreen
import com.example.chat_fluent.ui.theme.ChatfluentTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private  var auth: FirebaseAuth = Firebase.auth



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp(auth)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChatfluentTheme {
        Greeting("Android")
    }
}


//
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable

fun MyApp(auth:FirebaseAuth){

    val navController = rememberNavController()
    ChatfluentTheme {
        NavHost(navController = navController, startDestination = ChatScreen.route) {
            composable(HomePage.route) {
                HomePageScreen(navController)
            }


//             composable(SignupPage.route) {
//                 signupScreen(navController)

        composable(SignupPage.route) {
            signupScreen(navController , auth)

            }
            composable(MainScreen.route) {
                MainScreen()
        }

        composable(LoginPage.route) {
            LoginScreen(navController , auth )
        }

        composable(ChatScreen.route) {
            val chatViewModel = chatviewmodel()
            chatscreen(
                modifier = Modifier,
                chatviewmodel = chatViewModel
            )
        }
            // Add this new composable for the parameterized route
//            composable(
//                route = "${ChatScreen.route}/{${ChatScreenWithTopic.TOPIC_ARG}}",
//                arguments = listOf(
//                    navArgument(ChatScreenWithTopic.TOPIC_ARG) { type = NavType.StringType }
//                )
//            ) { backStackEntry ->
//                val topic = backStackEntry.arguments?.getString(ChatScreenWithTopic.TOPIC_ARG) ?: ""
//                val chatViewModel = chatviewmodel()
//                chatViewModel.setTopic(topic)
//                chatscreen(
//                    modifier = Modifier,
//                    chatviewmodel = chatViewModel
//                )
//            }
//            composable(ChatScreen.route) {
//                val chatViewModel = GeminiChatViewModel(ChatRepository.getInstance(GeminiApiService.getInstance(),LocalContext.current))
//                GeminiChatScreen(
//                    modifier = Modifier,
//                    viewModel = chatViewModel
//                )
//            }

            composable(ChatScreen.route) {
                val chatViewModel = chatviewmodel()
                chatscreen(
                    modifier = Modifier,
                    chatviewmodel = chatViewModel
                )
            }





        }
    }

}
