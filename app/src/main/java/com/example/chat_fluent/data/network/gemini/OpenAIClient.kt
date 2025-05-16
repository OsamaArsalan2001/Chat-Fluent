package com.example.chat_fluent.data.network.gemini

import com.example.chat_fluent.models.ChatRequest
import com.example.chat_fluent.models.Choice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OpenAIClient: OpenAIRemoteSource{

//    val openAIService: OpenAIApiService by lazy {
//      //  RetrofitHelper.api
//    }
    companion object
    {
        private var instance:OpenAIClient?=null
        fun getInstance(): OpenAIClient {
            return instance?: synchronized(this){
                val temp=OpenAIClient()
                instance=temp
                temp
            }
        }

    }
    override suspend fun getTutorResponseFromNetwork(request: ChatRequest): Flow<List<Choice>>  = flow {
//        println("In getTutorResponseFromNetwork: Success")
//
//        val chatList= openAIService.getTutorResponse(request).body()?.choices?: listOf()
//        println(chatList)
//        emit(chatList)
    }
}