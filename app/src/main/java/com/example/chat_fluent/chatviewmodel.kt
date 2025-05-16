//package com.example.chat_fluent
//
//import android.os.Build
//import android.util.Log
//import androidx.annotation.RequiresApi
//import androidx.compose.runtime.mutableStateListOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.chat_fluent.Constants.GEMINI_API_KEY
//import com.example.chat_fluent.models.messagemodel
//import com.google.ai.client.generativeai.BuildConfig
//import com.google.ai.client.generativeai.GenerativeModel
//import com.google.ai.client.generativeai.type.content
//import kotlinx.coroutines.launch
//import kotlin.getValue
//
//class chatviewmodel : ViewModel() {
//
//    val messagelist by lazy {
//        mutableStateListOf<messagemodel>()
//    }
//
//    val generativeModel = GenerativeModel(
//        modelName = "gemini-2.0-flash",
//        apiKey = GEMINI_API_KEY
//    )
//    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
//    fun sendquestion(question : String){
//        viewModelScope.launch {
//            try {
//
//                val chat = generativeModel.startChat(
//                    history = messagelist.map {
//                        content(it.role) {text(it.message)  }
//                    }.toList()
//                )
//                messagelist.add(messagemodel(question , "user"))
//                messagelist.add(messagemodel("typing...." , "model"))
//
//
//
//                val response = chat.sendMessage(question)
//                messagelist.removeAt(messagelist.size - 1)
//
//               // messagelist.removeLast()
//                messagelist.add(messagemodel(response.text.toString() , "model"))
//
//
//            }catch (e:Exception){
//                messagelist.removeAt(messagelist.size - 1)
//
//                //messagelist.removeLast()
//                messagelist.add(messagemodel("Error : "+e.message.toString(), "model"))
//            }
//        }
//    }
//
//}