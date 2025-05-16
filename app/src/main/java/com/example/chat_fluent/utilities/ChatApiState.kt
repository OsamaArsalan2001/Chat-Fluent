package com.example.chat_fluent.utilities

import com.example.chat_fluent.models.Message

sealed class ChatApiState
{
    class Success(val data: List<Message>):ChatApiState()
    class Failure(val msg: Throwable):ChatApiState()
    object Loading:ChatApiState()

}
