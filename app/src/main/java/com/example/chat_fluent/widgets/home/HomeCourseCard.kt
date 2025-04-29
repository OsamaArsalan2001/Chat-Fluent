package com.example.chat_fluent.widgets.home


import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.chat_fluent.ui.theme.Blue
import com.example.chat_fluent.ui.theme.LightOrange
import com.example.chat_fluent.ui.theme.Orange

@Composable
fun HomeCourseCard(index:Int, course: String, time:String, @DrawableRes imageRes: Int, isSelected: Boolean = false,
                   onClick: () -> Unit = {}) {
    Card(

        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
            pressedElevation = 2.dp // Elevation when pressed
        ),
        colors = CardDefaults.cardColors(
            containerColor = if(index%2==0) MaterialTheme.colorScheme.secondary else LightOrange
        ),
        border = if (isSelected && index%2==0) {
            BorderStroke(2.dp, Blue) // Only show border when selected
        } else if (isSelected && index%2==1) {
            BorderStroke(2.dp, Orange)
        }
        else{
            null// No border when not selected
        },
//        colors = CardDefaults.cardColors(
//            containerColor = Color.White,
//        ),
        modifier = Modifier
            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
            .size(width = 270.dp, height = 150.dp)
            .clickable (interactionSource = remember { MutableInteractionSource() },
                indication = null // Disables the ripple effect
            ){ onClick() }
    ) {
        Row ( modifier = Modifier.padding(10.dp)){
            Column(
                horizontalAlignment = Alignment.Start, // This centers the content
                modifier = Modifier.fillMaxHeight()
                    .padding(7.dp)
            ) {


                Text(
                    text = course,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        //   .padding(horizontal = 8.dp, vertical = 8.dp)
                        .align(Alignment.Start),
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(32.dp).align(Alignment.End))
                Row {
                    Icon(
                        Icons.Default.Timer, contentDescription = "Time", Modifier
                            .size(25.dp)
                            .padding(end = 5.dp), tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = time,
                        style = MaterialTheme.typography.bodyLarge,
                        //modifier = Modifier
                            //   .padding(horizontal = 8.dp, vertical = 8.dp)
                            //.align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Image(
                painter = painterResource(id = imageRes), // Add your own drawable
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .padding(start = 50.dp)
                    .size(120.dp)
                    .clip(RoundedCornerShape(5.dp)),
            )
        }

    }
}