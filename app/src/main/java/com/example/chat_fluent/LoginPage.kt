package com.example.chat_fluent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chat_fluent.nav.SignupPage
import com.example.chat_fluent.ui.theme.WhiteColor
import com.example.chat_fluent.ui.theme.buttonColorSignup
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController , auth: FirebaseAuth){
    var emailAddress by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("")}
    var checked by remember { mutableStateOf(true) }
    var showPassword by remember { mutableStateOf(false) }
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

                    )
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
                    value = password ,
                    onValueChange = {
                            newText -> password = newText
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
                            checkedColor = buttonColorSignup
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
                        Color(
                            alpha = 255,
                            red = 98,
                            green = 106,
                            blue = 231
                        )
                    ), onClick = {
                        navController.popBackStack()
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
                                color = buttonColorSignup
                            )
                        )

                    }

                }


            }

        }


 }
}