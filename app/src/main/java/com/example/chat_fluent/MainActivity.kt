package com.example.chat_fluent

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chat_fluent.OpenAIChatScreen
import com.example.chat_fluent.chatbot.OpenAIChatRepository
import com.example.chat_fluent.chatbot.view.OpenAIChatScreen
import com.example.chat_fluent.chatbot.viewmodel.OpenAIChatViewModel
import com.example.chat_fluent.data.network.gemini.OpenAIApiService
import com.example.chat_fluent.data.network.gemini.prompts.EnglishTutorPrompt
import com.example.chat_fluent.nav.MainScreen
import com.example.chat_fluent.ui.theme.ChatfluentTheme
import com.example.chat_fluent.widgets.home.HomeScreen
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.ktx.Firebase
//import io.github.jan.supabase.SupabaseClient
//import io.github.jan.supabase.auth.Auth
//import io.github.jan.supabase.auth.auth
//import io.github.jan.supabase.createSupabaseClient
//import io.github.jan.supabase.postgrest.Postgrest

class MainActivity : ComponentActivity() {
//    private  var auth: FirebaseAuth = Firebase.auth
//    val supabase = createSupabaseClient(
//        supabaseUrl = "https://ozsjpolwvdviqcmovfpv.supabase.co",
//        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im96c2pwb2x3dmR2aXFjbW92ZnB2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDYxMTI5MjMsImV4cCI6MjA2MTY4ODkyM30.HWjVY61YD2R4_s3Sk87Xma20GYuEKrI6VJsCU_blIXo"
//    ) {
//        install(Auth)
//        install(Postgrest)
//        //install other modules
//    }






    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp(/*supabase*/)
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
fun MyApp(/*supabase:   SupabaseClient*/){
    val navController = rememberNavController()
    ChatfluentTheme {
        NavHost(navController = navController, startDestination = StartPage.route) {
//            composable(route = HomePage.route) {
//                HomePageScreen(navController)
//            }
//            composable(route = ChatScreen.route) {
//                val chatViewModel = chatviewmodel()
//                chatscreen(chatViewModel)
//            }
//
//
//            composable(SignupPage.route) {
//                signupScreen(navController, supabase)
//
//            }
//            composable(MainScreen.route) {
//                MainScreen()
//
//            }
//
//            composable(LoginPage.route) {
//                LoginScreen(navController, supabase)
//            }
            composable(StartPage.route) {
                MainScreen()

            }
//            composable(
//                route = OpenAIChatScreen.route,
//                arguments = listOf(
//                    navArgument("topic") {
//                        type = NavType.StringType
//                        nullable = true
//                    }
//                )
//            ) { backStackEntry ->
//                val topic = backStackEntry.arguments?.getString("topic")
//                OpenAIChatScreen(
//                    onBackClick = { navController.popBackStack() },
//                    topic = topic
//                )
//            }


        }


        }
    }


