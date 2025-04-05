package com.example.chat_fluent

import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.DefaultTab.AlbumsTab.value
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chat_fluent.ui.theme.WhiteColor

@Composable
fun signupScreen(/*navController: NavController*/){
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }

    Column(
modifier = Modifier.fillMaxWidth() , verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally  ){
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(32.dp),
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
                "Create your account to start practicing Your " ,
                style = TextStyle(
                    fontSize = 15.sp
                )
            )
            Text(
                "English Skills" ,
                style = TextStyle(
                    fontSize = 15.sp
                )
            )

        }

        Column {

                TextField(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp  , vertical = 10.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    shape = RectangleShape  ,
                    value = firstName ,
                    onValueChange = {
                        newText -> firstName = newText
                    } ,
                    placeholder = {
                        Text("Enter your First name")
                    },
                    leadingIcon = {
                        Icon(Icons.Rounded.Person, contentDescription = "Person Icon")
                    } ,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = WhiteColor ,
                        unfocusedContainerColor = WhiteColor ,
                        focusedIndicatorColor = Color.Transparent ,
                        unfocusedIndicatorColor = Color.Transparent

                    ) ,


                )
            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp , vertical = 10.dp),

                shape = RectangleShape  ,
                value = lastName ,
                onValueChange = {
                        newText -> lastName = newText
                } ,
                placeholder = {
                    Text("Enter your Last name")
                },
                leadingIcon = {
                    Icon(Icons.Rounded.Person , contentDescription = "Person Icon")
                } ,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = WhiteColor ,
                    unfocusedContainerColor = WhiteColor ,
                    focusedIndicatorColor = Color.Transparent ,
                    unfocusedIndicatorColor = Color.Transparent

                ) ,


                )
            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp  , vertical = 10.dp),

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

                ) ,


                )
            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),

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
            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp , vertical = 10.dp),

                shape = RectangleShape  ,
                value = level    ,
                onValueChange = {
                        newText -> level   = newText
                } ,
                placeholder = {
                    Text("SELECT your level")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.public_icon) ,
                        contentDescription = "Public Icon"
                    )
                } ,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = WhiteColor ,
                    unfocusedContainerColor = WhiteColor ,
                    focusedIndicatorColor = Color.Transparent ,
                    unfocusedIndicatorColor = Color.Transparent

                ) ,


                )
            }



    }

}

@Preview(showBackground = true)
@Composable
fun signupScreenPreview(){
    signupScreen()
}