package com.example.chat_fluent.models

import androidx.annotation.DrawableRes

data class Topics(val name: String,
                  val description: String? = " ",
                  @DrawableRes val imageRes: Int)