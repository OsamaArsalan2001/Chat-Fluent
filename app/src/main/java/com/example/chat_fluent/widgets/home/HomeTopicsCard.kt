package com.example.chat_fluent.widgets.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.chat_fluent.ui.theme.Blue

@Composable
fun HomeTopicsCard(course: String, @DrawableRes imageRes: Int, isSelected: Boolean = false,
                   onClick: () -> Unit = {}) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp // Elevation when pressed
        ),
        border = if (isSelected) {
            BorderStroke(2.dp, Blue) // Only show border when selected
        } else {
            null // No border when not selected
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .size(width = 120.dp, height = 160.dp)
            .clickable (interactionSource = remember { MutableInteractionSource() },
                indication = null // Disables the ripple effect
             ){ onClick() }
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally, // This centers the content
            modifier = Modifier.fillMaxSize()
        )  {
            Image(
                painter = painterResource(id = imageRes), // Add your own drawable
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(5.dp)),
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = course,
                modifier = Modifier
                 //   .padding(horizontal = 8.dp, vertical = 8.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
            )
        }

    }
}