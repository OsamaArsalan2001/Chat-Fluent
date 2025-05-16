package com.example.chat_fluent.utilities

import com.example.chat_fluent.models.FeedbackResponse

sealed class FeedbackApiState {

    class Success(val data: FeedbackResponse):FeedbackApiState()
    class Failure(val msg: Throwable):FeedbackApiState()
    object Loading:FeedbackApiState()

}