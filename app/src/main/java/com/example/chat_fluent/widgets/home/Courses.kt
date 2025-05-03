package com.example.chat_fluent.widgets.home

import androidx.annotation.DrawableRes

data class Courses(val name: String,
                   val description: String? = " ",
                   val duration: String?,
                   @DrawableRes val imageRes: Int)


