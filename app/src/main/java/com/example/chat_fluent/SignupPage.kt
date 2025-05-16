//package com.example.chat_fluent
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.navigationBarsPadding
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.statusBarsPadding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.rounded.Lock
//import androidx.compose.material.icons.rounded.MailOutline
//import androidx.compose.material.icons.rounded.Person
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Checkbox
//import androidx.compose.material3.CheckboxDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Color.Companion.White
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.text.toLowerCase
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//import androidx.compose.ui.window.DialogProperties
//import androidx.navigation.NavController
//import com.example.chat_fluent.models.User
//import com.example.chat_fluent.ui.theme.WhiteColor
//import com.example.chat_fluent.ui.theme.buttonColorSignup
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import io.github.jan.supabase.SupabaseClient
//import io.github.jan.supabase.auth.Auth
//import io.github.jan.supabase.auth.providers.builtin.Email
//import io.github.jan.supabase.auth.auth
//import io.github.jan.supabase.auth.status.SessionSource
//import io.github.jan.supabase.auth.status.SessionStatus
//import io.github.jan.supabase.postgrest.from
//import io.github.jan.supabase.postgrest.query.Columns
//import kotlinx.coroutines.launch
//import kotlinx.serialization.json.Json
//import kotlinx.serialization.json.buildJsonObject
//import kotlin.text.Regex
//
//@SuppressLint("CoroutineCreationDuringComposition", "SuspiciousIndentation")
//@Composable
//fun signupScreen(navController: NavController , supabase:   SupabaseClient ,){
//    val context:Context = LocalContext.current
////     lateinit var user : User
//    val scope = rememberCoroutineScope()
//    var openDialog by remember { mutableStateOf(false) }
//    var firstName by remember { mutableStateOf("") }
//    var isErrorFirstName by remember { mutableStateOf(false) }
//    var lastName by remember { mutableStateOf("") }
//    var isErrorLastName by remember { mutableStateOf(false) }
//    var emailAddress by remember { mutableStateOf("") }
//    var isErrorEmailAddress by remember { mutableStateOf(false) }
//    var passwordEmail by remember { mutableStateOf("") }
//    var isErrorPassword by remember { mutableStateOf(false) }
//    var level by remember { mutableStateOf("") }
//    var isErrorLevel by remember { mutableStateOf(false) }
//    var checked by remember { mutableStateOf(true) }
//    var showPassword by remember { mutableStateOf(false) }
//
//    suspend fun createUser(supabase:   SupabaseClient , context:Context){
//        // here I will put condition if the user register before or not by check the mal
//        val checkUser = supabase.from("Users").select(columns = Columns.list("firstName" , "lastName" , "email" , "password" , "level")) {
//            filter {
//                User::email eq "${emailAddress.lowercase()}"
//                //or
//
//            }
//
//        }
//        val users = Json.decodeFromString<List<User>>(checkUser.data)
//
//
//        if (users.isNotEmpty()){
//            Log.d("this is the result of the checkUser" , "${users.elementAt(0).email}")
//            openDialog = false
//            Toast.makeText(context , "you enter this mail before " , Toast.LENGTH_SHORT).show()
//        }
//
//
//        else {
//            val user = supabase.auth.signUpWith(Email) {
//                email = "${emailAddress.lowercase()}"
//                password = "${passwordEmail}"
//            }
//            supabase.auth.sessionStatus.collect {
//                when (it) {
//                    is SessionStatus.Authenticated -> {
//                        println("Received new authenticated session.")
//                        when (it.source) { //Check the source of the session
//                            SessionSource.External -> {}
//                            is SessionSource.Refresh -> {}
//                            is SessionSource.SignIn -> {}
//                            is SessionSource.SignUp -> {
//                                // finish this tomorrow and it will work an sallah
//                                val user = User(
//                                    firstName = firstName,
//                                    lastName = lastName,
//                                    email = emailAddress.lowercase(),
//                                    password = passwordEmail,
//                                    level = level
//                                )
//                                supabase.from("Users").insert(user)
//                                openDialog = false
//                                navController.navigate(LoginPage.route)
//
//
//                            }
//
//                            SessionSource.Storage -> {}
//                            SessionSource.Unknown -> {}
//                            is SessionSource.UserChanged -> {}
//                            is SessionSource.UserIdentitiesChanged -> {}
//                            SessionSource.AnonymousSignIn -> {}
//                        }
//                    }
//
//                    SessionStatus.Initializing -> println("Initializing")
//                    is SessionStatus.RefreshFailure -> {
//                        openDialog = false
//                        Toast.makeText(context, "${it.cause}", Toast.LENGTH_SHORT).show()
//                    } //Either a network error or a internal server error
//                    is SessionStatus.NotAuthenticated -> {
//                        if (it.isSignOut) {
//                            println("User signed out")
//                        } else {
//                            println("User not signed in")
//                        }
//                    }
//                }
//            }
//        }
//    }
//    fun isValidName(text:String):Boolean{
//        return text.matches(regex = Regex("[a-zA-Z]{3,}"))
//    }
//
//    fun isValidEmail(text:String):Boolean{
//        return text.matches(regex = Regex("[a-zA-Z]+[0-9]*@gmail\\.com"))
//    }
//
//    fun isValidPassword(text:String):Boolean{
//        return text.matches(regex = Regex("^[^+_)(*&^%\\\$#!\\\\\":?><]{6,}$"))
//    }
//
//    fun isValidSelected(text:String):Boolean{
//        return text.matches(regex = Regex("^[A-C][1-2]$"))
//    }
//
//    Column(
//modifier = Modifier.fillMaxSize() , verticalArrangement = Arrangement.SpaceEvenly , horizontalAlignment = Alignment.CenterHorizontally  ){
//        Column(
//            modifier = Modifier
//                .statusBarsPadding()
//                .navigationBarsPadding()
//                .padding(start = 20.dp , end = 20.dp),
//            verticalArrangement = Arrangement.spacedBy(2.dp)  ,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                "Create Account" ,
//                style = TextStyle(
//                    fontWeight = FontWeight.Bold ,
//                    fontSize = 30.sp
//
//
//                )
//
//
//
//            )
//
//
//            Text(
//                "Create your account to start practicing Your " ,
//                style = TextStyle(
//                    fontSize = 15.sp
//                )
//            )
//            Text(
//                "English Skills" ,
//                style = TextStyle(
//                    fontSize = 15.sp
//                )
//            )
//
//        }
//
//        LazyColumn {
//            item {
//
//
//                TextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp, vertical = 5.dp),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                    shape = RectangleShape  ,
//                    value = firstName ,
//                    onValueChange = {
//                            newText -> firstName = newText
//                           if(isValidName(firstName) && firstName.isNotEmpty()){
//                               isErrorFirstName = false
//                           }
//                        else {
//                            isErrorFirstName = true
//                        }
//
//                    } ,
//                    placeholder = {
//                        Text("Enter your First name")
//                    },
//                    leadingIcon = {
//                        Icon(Icons.Rounded.Person, contentDescription = "Person Icon")
//                    } ,
//                    colors = TextFieldDefaults.colors(
//                        focusedContainerColor = WhiteColor ,
//                        unfocusedContainerColor = WhiteColor ,
//                        focusedIndicatorColor = Color.Transparent ,
//                        unfocusedIndicatorColor = Color.Transparent
//
//                    ) ,
//                    isError = isErrorFirstName ,
//                    supportingText = {
//                        if (isErrorFirstName){
//                            if (firstName.isEmpty()){
//                                Text("Insert First name")
//                            }
//                            else if (!isValidName(firstName)) {
//                                Text("insert by the pattern like osamaHelal")
//                            }
//                            else {
//                                isErrorFirstName = false
//                            }
//                        }
//                    }
//
//
//
//                    )
//                TextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp, vertical = 5.dp),
//
//                    shape = RectangleShape  ,
//                    value = lastName ,
//                    onValueChange = {
//                            newText -> lastName = newText
//                        if(isValidName(lastName) && lastName.isNotEmpty()){
//                            isErrorLastName = false
//                        }
//                        else {
//                            isErrorLastName = true
//                        }
//                    } ,
//                    placeholder = {
//                        Text("Enter your Last name")
//                    },
//                    leadingIcon = {
//                        Icon(Icons.Rounded.Person , contentDescription = "Person Icon")
//                    } ,
//                    colors = TextFieldDefaults.colors(
//                        focusedContainerColor = WhiteColor ,
//                        unfocusedContainerColor = WhiteColor ,
//                        focusedIndicatorColor = Color.Transparent ,
//                        unfocusedIndicatorColor = Color.Transparent
//
//                    ) ,
//                    isError = isErrorLastName ,
//                    supportingText = {
//                        if (isErrorLastName){
//                            if (lastName.isEmpty()){
//                                Text("Insert last name")
//                            }
//                            else if (!isValidName(lastName)) {
//                                Text("insert by the pattern like osamaHelal")
//                            }
//                            else {
//                                isErrorLastName = false
//                            }
//                        }
//                    }
//
//
//
//
//                )
//                TextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp, vertical = 5.dp),
//
//                    shape = RectangleShape  ,
//                    value = emailAddress ,
//                    onValueChange = {
//                            newText -> emailAddress = newText
//                        if(isValidEmail(emailAddress) && emailAddress.isNotEmpty()){
//                            isErrorEmailAddress = false
//                        }
//                        else {
//                            isErrorEmailAddress = true
//                        }
//                    } ,
//                    placeholder = {
//                        Text("Enter your Email")
//                    },
//                    leadingIcon = {
//                        Icon(Icons.Rounded.MailOutline , contentDescription = "Email Icon")
//                    } ,
//                    colors = TextFieldDefaults.colors(
//                        focusedContainerColor = WhiteColor,
//                        unfocusedContainerColor = WhiteColor  ,
//                        focusedIndicatorColor = Color.Transparent ,
//                        unfocusedIndicatorColor = Color.Transparent
//
//                    ) ,
//                    isError = isErrorEmailAddress ,
//                    supportingText = {
//                        if (isErrorEmailAddress){
//                            if (emailAddress.isEmpty()){
//                                Text("Insert Email")
//                            }
//                            else if (!isValidEmail(emailAddress)) {
//                                Text("insert by the pattern like osamaHelal@gmail.com")
//                            }
//                            else {
//                                isErrorEmailAddress = false
//                            }
//
//                        }
//                    }
//
//
//
//
//                )
//                TextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp , vertical = 5.dp),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                     visualTransformation = if (showPassword) {
//
//                        VisualTransformation.None
//
//                    } else {
//
//                        PasswordVisualTransformation()
//
//                    },
//                    trailingIcon = {
//                        if (showPassword ){
//                            IconButton(onClick = {
//                                showPassword = false
//
//                            }) {
//                                Icon(
//                                    painter = painterResource(
//                                        R.drawable.baseline_visibility_24
//                                    ) ,
//                                    contentDescription = "hide"
//                                )
//
//                            }
//                        }
//                        else {
//                            IconButton(
//                                onClick = {showPassword = true}
//                            ) {
//                               Icon(
//                                    painter = painterResource(R.drawable.visibility_off_24px) ,
//                                    contentDescription = "hide Icon"
//
//                                )
//                            }
//
//
//
//                        }
//                    },
//
//                    shape = RectangleShape  ,
//                    value = passwordEmail ,
//                    onValueChange = {
//                            newText -> passwordEmail = newText
//                        if(isValidPassword(passwordEmail) && passwordEmail.isNotEmpty()){
//                            isErrorPassword = false
//                        }
//                        else {
//                            isErrorPassword = true
//                        }
//
//                    } ,
//                    placeholder = {
//                        Text("Create Your password")
//                    },
//                    leadingIcon = {
//                        Icon(Icons.Rounded.Lock , contentDescription = "Password Icon")
//                    } ,
//                    colors = TextFieldDefaults.colors(
//                        focusedContainerColor = WhiteColor,
//                        unfocusedContainerColor = WhiteColor  ,
//                        focusedIndicatorColor = Color.Transparent ,
//                        unfocusedIndicatorColor = Color.Transparent
//
//                    ) ,
//                    isError = isErrorPassword ,
//                    supportingText = {
//                        if (isErrorPassword){
//                            if (passwordEmail.isEmpty()){
//                                Text("Insert password")
//                            }
//                            else if ( !isValidPassword(passwordEmail)) {
//                                Text("don't insert special symbol like +_)'(*&^%$#@!~\":?><' and the lowest number of the chacter is six")
//                            }
//                            else {
//                                isErrorPassword = false
//                            }
//
//                        }
//                    }
//
//
//
//
//                )
//                TextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp, vertical = 5.dp),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                    shape = RectangleShape  ,
//                    value = level    ,
//                    onValueChange = {
//                            newText -> level   = newText
//                        if(isValidEmail(level) && level.isNotEmpty()){
//                            isErrorLevel = false
//                        }
//                        else {
//                            isErrorLevel = true
//                        }
//                    } ,
//                    placeholder = {
//                        Text("SELECT your level")
//                    },
//                    leadingIcon = {
//                        Icon(
//                            painter = painterResource(R.drawable.public_icon) ,
//                            contentDescription = "Public Icon"
//                        )
//                    } ,
//                    colors = TextFieldDefaults.colors(
//                        focusedContainerColor = WhiteColor ,
//                        unfocusedContainerColor = WhiteColor ,
//                        focusedIndicatorColor = Color.Transparent ,
//                        unfocusedIndicatorColor = Color.Transparent
//
//                    ) ,
//                    isError = isErrorLevel ,
//                    supportingText = {
//                        if (isErrorLevel){
//                            if (level.isEmpty()){
//                                Text("Insert Level")
//                            }
//                            else if(!isValidSelected(level)) {
//                                Text("insert Those value only A1 , A2  , B1 , B2 , C1 , C2")
//                            }
//                            else {
//                                isErrorLevel = false
//                            }
//                        }
//                    }
//
//
//
//
//                )
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Start ,
//                    verticalAlignment = Alignment.CenterVertically
//
//                )
//                {
//                    Checkbox(
//                        checked = checked ,
//                        onCheckedChange = {
//                            checked= it
//                        } ,
//                        colors = CheckboxDefaults.colors(
//                            checkedColor = buttonColorSignup
//                        )
//
//
//
//                    )
//
//                    Text(
//                        "I agree with the Terms & Conditions"
//                    )
//
//                }
//
//                Column(
//                    modifier = Modifier.fillMaxWidth()
//
//                ) {
//                    Button(
//                        shape = RoundedCornerShape(10.dp),
////                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp, vertical = 20.dp),
//                        modifier = Modifier.fillMaxWidth().padding(
//                            start = 30.dp ,
//                            end = 30.dp ,
//                            top = 20.dp
//                        ),
//                        colors = ButtonDefaults.buttonColors(
//                            Color(
//                                alpha = 255,
//                                red = 98,
//                                green = 106,
//                                blue = 231
//                            )
//                        ), onClick = {
////                            navController.popBackStack()
//                            if (
//                                (isValidName(firstName) && firstName.isNotEmpty()) && isValidName(lastName) && lastName.isNotEmpty() && (isValidEmail(emailAddress) && emailAddress.isNotEmpty() ) && (isValidPassword(passwordEmail) && (passwordEmail.isNotEmpty())) &&
//                                (isValidSelected(level) && level.isNotEmpty())
//                                ){
////                                user = User(firstName = firstName , lastName = lastName , email = emailAddress , password = passwordEmail , level = level)
//                                isErrorFirstName = false
//                                isErrorFirstName = false
//                                isErrorEmailAddress = false
//                                isErrorPassword = false
//                                isErrorLevel  = false
////                                navController.popBackStack()
//                                openDialog = true
//                                    scope.launch{
//                                        createUser(supabase , context)
//
//                                    }
//
//
//
//
//
//
//                            }
//                            else {
//                                if (!isValidName(firstName) && firstName.isEmpty()){
//                                    isErrorFirstName = true
//                                }
//                                else if (!isValidName(lastName) && lastName.isEmpty() ){
//                                    isErrorLastName = true
//                                }
//                                else if (!isValidEmail(emailAddress) && emailAddress.isEmpty()){
//                                    isErrorEmailAddress = true
//                                }
//                                else if (!isValidPassword(passwordEmail) && passwordEmail.isEmpty()){
//                                    isErrorPassword = true
//                                }
//                                else  {
//                                    isErrorLevel = true
//                                }
//
//
//                            }
//
//
//                        } ,
//
//
//                    ) {
//
//                        Text(
//                            "Sign up", style = TextStyle(
//                                fontSize = 30.sp
//                            )
//                        )
//                    }
//                    if(openDialog){
//                        Dialog(
//                            onDismissRequest = { openDialog = false },
//                            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
//                        ) {
//
//                            Box(
//                                contentAlignment= Alignment.Center,
//                                modifier = Modifier
//                                    .size(100.dp)
//                                    .background(White, shape = RoundedCornerShape(8.dp))
//                            ) {
//                                Column(
//                                    verticalArrangement = Arrangement.Center
//                                ){
//                                    CircularProgressIndicator()
//                                    Text("Loading...")
//
//                                }
//
//                            }
//                        }
//                    }
//
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center ,
//                        verticalAlignment = Alignment.CenterVertically
//
//
//                    ){
//                        Text("Already registerd?")
//                        TextButton(onClick = {
//                            navController.navigate(LoginPage.route)
//                        }) {
//                            Text(
//                                "Log in" ,
//                                style = TextStyle(
//                                    color = buttonColorSignup
//                                )
//                            )
//
//                        }
//
//                    }
//                }
//
//            }
//
//        }
//
//
//
//
//
//
//
//    }
//
//
//
//}
//
//
////@Preview(showBackground = true)
////@Composable
////fun signupScreenPreview(){
////    signupScreen()
////}