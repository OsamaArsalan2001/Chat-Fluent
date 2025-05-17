package com.example.chat_fluent

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.chat_fluent.SignupPage
import com.example.chat_fluent.ui.theme.WhiteColor
import com.example.chat_fluent.ui.theme.buttonColorSignup
import com.example.chat_fluent.R
//import com.google.firebase.auth.FirebaseAuth
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionSource
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController , supabase:   SupabaseClient){
    val context:Context = LocalContext.current
    val scope = rememberCoroutineScope()
    var emailAddress by remember { mutableStateOf("") }
    var isErrorEmailAddress by remember { mutableStateOf(false) }
    var passwordEmail by remember { mutableStateOf("")}
    var isErrorPassword by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(true) }
    var showPassword by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    fun isValidEmail(text:String):Boolean{
        return text.matches(regex = Regex("[a-zA-Z]+[0-9]*@gmail\\.com"))
    }

    fun isValidPassword(text:String):Boolean{
        return text.matches(regex = Regex("^[^+_)(*&^%\\\$#!\\\\\":?><]{6,}$"))
    }
    suspend fun userAuthentication(){
        try {
            val result = supabase.auth.signInWith(Email) {
                email = "${emailAddress.lowercase()}"
                password = "${passwordEmail}"
            }


            supabase.auth.sessionStatus.collect {
                when (it) {
                    is SessionStatus.Authenticated -> {
                        println("Received new authenticated session.")
                        when (it.source) { //Check the source of the session
                            SessionSource.External -> {}
                            is SessionSource.Refresh -> {}
                            is SessionSource.SignIn -> {
                                navController.navigate(StartPage.route)

                            }

                            is SessionSource.SignUp -> {
                                // finish this tomorrow and it will work an sall
                            }

                            SessionSource.Storage -> {}
                            SessionSource.Unknown -> {}
                            is SessionSource.UserChanged -> {}
                            is SessionSource.UserIdentitiesChanged -> {}
                            SessionSource.AnonymousSignIn -> {}
                        }
                    }

                    SessionStatus.Initializing -> println("Initializing")
                    is SessionStatus.RefreshFailure -> {
                        openDialog = false
                        Toast.makeText(context, "${it.cause}", Toast.LENGTH_SHORT).show()
                    } //Either a network error or a internal server error
                    is SessionStatus.NotAuthenticated -> {
                        if (it.isSignOut) {
                            println("User signed out")
                        } else {

                            Toast.makeText(
                                context,
                                "it's wrong email or password",
                                Toast.LENGTH_SHORT
                            )
                        }
                    }
                }
            }
        }

        catch (e: Exception){
            Log.d("Exception" , "{${e.toString()}}")
            Toast.makeText(context , "you enter the email or password wrong" , Toast.LENGTH_SHORT).show()
        }

    }
    Column(
        modifier = Modifier.fillMaxSize() , verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally  ){
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(start = 20.dp , end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)  ,
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
                "Sign in  your account to enhance your English" ,
                style = TextStyle(
                    fontSize = 15.sp
                )
            )
            Text(
                "Conversation Skills" ,
                style = TextStyle(
                    fontSize = 15.sp
                )
            )

        }

        LazyColumn (

        ){
            item {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp),

                    shape = RectangleShape  ,
                    value = emailAddress ,
                    onValueChange = {
                            newText -> emailAddress = newText
                        if(isValidEmail(emailAddress) && emailAddress.isNotEmpty()){
                            isErrorEmailAddress = false
                        }
                        else {
                            isErrorEmailAddress = true
                        }
                    } ,
                    placeholder = {
                        Text("Enter your Email")
                    },
                    leadingIcon = {
                        Icon(Icons.Rounded.MailOutline , contentDescription = "Email Icon")
                    } ,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = WhiteColor,
                        unfocusedContainerColor = WhiteColor  ,
                        focusedIndicatorColor = Color.Transparent ,
                        unfocusedIndicatorColor = Color.Transparent

                    ) ,
                    isError = isErrorEmailAddress ,
                    supportingText = {
                        if (isErrorEmailAddress){
                            if (emailAddress.isEmpty()){
                                Text("Insert Email")
                            }
                            else if (!isValidEmail(emailAddress)) {
                                Text("insert by the pattern like osamaHelal@gmail.com")
                            }
                            else {
                                isErrorEmailAddress = false
                            }

                        }

                    }
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp , vertical = 5.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (showPassword) {

                        VisualTransformation.None

                    } else {

                        PasswordVisualTransformation()

                    },
                    trailingIcon = {
                        if (showPassword ){
                            IconButton(onClick = {
                                showPassword = false

                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_visibility_24) ,
                                    contentDescription = "hide"
                                )

                            }
                        }
                        else {
                            IconButton(
                                onClick = {showPassword = true}
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.visibility_off_24px) ,
                                    contentDescription = "hide Icon"

                                )
                            }



                        }
                    },

                    shape = RectangleShape  ,
                    value = passwordEmail ,
                    onValueChange = {
                            newText -> passwordEmail = newText
                        if(isValidPassword(passwordEmail) && passwordEmail.isNotEmpty()){
                            isErrorPassword = false
                        }
                        else {
                            isErrorPassword = true
                        }
                    } ,
                    placeholder = {
                        Text("Create Your password")
                    },
                    leadingIcon = {
                        Icon(Icons.Rounded.Lock , contentDescription = "Password Icon")
                    } ,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = WhiteColor,
                        unfocusedContainerColor = WhiteColor  ,
                        focusedIndicatorColor = Color.Transparent ,
                        unfocusedIndicatorColor = Color.Transparent

                    ) ,
                    isError = isErrorPassword ,
                    supportingText = {
                        if (isErrorPassword){
                            if (passwordEmail.isEmpty()){
                                Text("Insert password")
                            }
                            else if ( !isValidPassword(passwordEmail)) {
                                Text("don't insert special symbol like +_)'(*&^%$#@!~\":?><' and the lowest number of the chacter is six")
                            }
                            else {
                                isErrorPassword = false
                            }

                        }
                    }


                    )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start ,
                    verticalAlignment = Alignment.CenterVertically

                )
                {
                    Checkbox(
                        checked = checked ,
                        onCheckedChange = {
                            checked= it
                        } ,
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary
                        )


                    )

                    Text(
                        "I agree with the Terms & Conditions"
                    )

                }
                Button(
                    shape = RoundedCornerShape(10.dp),
//                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp, vertical = 20.dp),
                    modifier = Modifier.fillMaxWidth().padding(
                        start = 30.dp ,
                        end = 30.dp ,
                        top = 20.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primary
                    ), onClick = {
                        if (
                            (isValidEmail(emailAddress) && emailAddress.isNotEmpty() ) && (isValidPassword(passwordEmail) && (passwordEmail.isNotEmpty()))

                        ){
                            isErrorEmailAddress = false
                            isErrorPassword = false
                            openDialog = true
                            scope.launch{
                                userAuthentication()

                            }

                        }
                        else {
                            if (!isValidEmail(emailAddress) && emailAddress.isEmpty()){
                                isErrorEmailAddress = true
                            }
                            else {

                                    isErrorPassword = true

                            }
                            openDialog = false

                        }
                    }) {
                    Text(
                        "Log in", style = TextStyle(
                            fontSize = 30.sp
                        )
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center ,
                    verticalAlignment = Alignment.CenterVertically


                ){
                    Text("Already registerd?")
                    TextButton(onClick = {
                        navController.navigate(SignupPage.route)
                    }) {
                        Text(
                            "Sign Up" ,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )

                    }
                    if(openDialog){
                        Dialog(
                            onDismissRequest = { openDialog = false },
                            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                        ) {

                            Box(
                                contentAlignment= Alignment.Center,
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(White, shape = RoundedCornerShape(8.dp))
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center
                                ){
                                    CircularProgressIndicator()
                                    Text("Loading...")

                                }

                            }
                        }
                    }

                }


            }

        }


 }
}