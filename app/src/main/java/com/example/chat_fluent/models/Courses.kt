package com.example.chat_fluent.models

import androidx.annotation.DrawableRes

data class Courses(val name: String,
                   val description: String? = " ",
                   val duration: String?,
                   @DrawableRes val imageRes: Int)