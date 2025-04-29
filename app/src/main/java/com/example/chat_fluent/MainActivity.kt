package com.example.chat_fluent

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
@Composable

// fun MyApp(){
//     var theme = remember { mutableStateOf(false) }

fun MyApp(auth:FirebaseAuth){

    val navController = rememberNavController()
    ChatfluentTheme {
        NavHost(navController = navController, startDestination = HomePage.route) {
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


            composable(LoginPage.route) {
                LoginScreen(navController)
            }


        }
    }

}
