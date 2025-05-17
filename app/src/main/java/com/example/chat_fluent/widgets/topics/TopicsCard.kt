package com.example.chat_fluent.widgets.topics


import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chat_fluent.ui.theme.Blue
import com.example.chat_fluent.ui.theme.LightOrange
import com.example.chat_fluent.ui.theme.Orange
import com.example.chat_fluent.models.Courses
import com.example.chat_fluent.models.Topics

@Composable
fun TopicsCard(index:Int,topic: Topics,isSelected: Boolean = false,navController: NavController,
               onClick: () -> Unit = {}) {
    Card(

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp // Elevation when pressed
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
            .fillMaxWidth()
            .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
            .size(width = 270.dp, height = 150.dp)
            .clickable (interactionSource = remember { MutableInteractionSource() },
                indication = null // Disables the ripple effect
            ){ onClick()
                navController.navigate("temp/${topic.name}")
            }
    ) {
        Row ( modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
//            Column (
//                horizontalAlignment = Alignment.Start, // This centers the content
//                modifier = Modifier.fillMaxSize()
//            )  {
            Image(
                painter = painterResource(id = topic.imageRes),
                contentDescription = topic.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f) // Force square aspect ratio
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = course,
//                    modifier = Modifier
//                        //   .padding(horizontal = 8.dp, vertical = 8.dp)
//                        .align(Alignment.Start),
//                    textAlign = TextAlign.Center,
//                )


            Column(
                horizontalAlignment = Alignment.Start, // This centers the content
                modifier = Modifier.fillMaxHeight()
                    .padding(7.dp)
            ) {
                Text(text = topic.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)

//                Text(
//                    text = course,
//                    style = MaterialTheme.typography.headlineSmall,
//                    modifier = Modifier
//                        //   .padding(horizontal = 8.dp, vertical = 8.dp)
//                        .align(Alignment.Start),
//                    textAlign = TextAlign.Center,
//                )
                Spacer(modifier = Modifier.height(8.dp))
                topic.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

//                Row {
//                    Icon(
//                        Icons.Default.Timer, contentDescription = "Time", Modifier
//                            .size(25.dp)
//                            .padding(end = 5.dp), tint = MaterialTheme.colorScheme.primary
//                    )
//                    course.duration?.let {
//                        Text(
//                            text = it,
//                            style = MaterialTheme.typography.bodyLarge,
//                            //modifier = Modifier
//                            //   .padding(horizontal = 8.dp, vertical = 8.dp)
//                            //.align(Alignment.CenterHorizontally),
//                            textAlign = TextAlign.Center,
//                        )
//                    }
//                }
            }
        }
//            Image(
//                painter = painterResource(id = imageRes), // Add your own drawable
//                contentDescription = null,
//                contentScale = ContentScale.Inside,
//                modifier = Modifier
//                    .padding(start = 50.dp)
//                    .size(120.dp)
//                    .clip(RoundedCornerShape(5.dp)),
//            )
    }

}
