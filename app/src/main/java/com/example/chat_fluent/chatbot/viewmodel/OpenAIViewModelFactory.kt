package com.example.chat_fluent.chatbot.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chat_fluent.chatbot.IOpenAIChatRepository

class OpenAIViewModelFactory(private val repo:IOpenAIChatRepository):
        ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if(modelClass.isAssignableFrom(OpenAIChatViewModel::class.java))
            {
                OpenAIChatViewModel(repo) as T
            }
            else
            {
                throw IllegalStateException("ViewModel Class not found")
            }
        }
    }
