package com.example.chat_fluent.Models

import kotlinx.serialization.Serializable
//import kotlinx.serialization.json.Json

@Serializable
data class User(
    val firstName:String ,
    val lastName:String,
    val email:String ,
    val password:String ,
    val level:String
)
@Serializable
data class UserList(
    val Users:List<User>
)
