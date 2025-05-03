package com.example.chat_fluent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chat_fluent.nav.MainScreen
import com.example.chat_fluent.ui.theme.ChatfluentTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

class MainActivity : ComponentActivity() {
//    private  var auth: FirebaseAuth = Firebase.auth
    val supabase = createSupabaseClient(
        supabaseUrl = "https://ozsjpolwvdviqcmovfpv.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im96c2pwb2x3dmR2aXFjbW92ZnB2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDYxMTI5MjMsImV4cCI6MjA2MTY4ODkyM30.HWjVY61YD2R4_s3Sk87Xma20GYuEKrI6VJsCU_blIXo"
    ) {
        install(Auth)
        install(Postgrest)
        //install other modules
    }






    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp(supabase)
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
fun MyApp(supabase:   SupabaseClient){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomePage.route){
        composable(HomePage.route) {
            HomePageScreen(navController)
        }


        composable(SignupPage.route) {
            signupScreen(navController , supabase)

        }
        composable(MainScreen.route) {
            MainScreen()

        }

        composable(LoginPage.route) {
            LoginScreen(navController , supabase )
        }



    }

}
