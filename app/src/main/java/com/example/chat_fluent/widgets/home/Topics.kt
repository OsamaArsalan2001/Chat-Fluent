package com.example.chat_fluent.widgets.home

import androidx.annotation.DrawableRes

data class Topics(val name: String,
                  val description: String? = " ",
                  @DrawableRes val imageRes: Int)
