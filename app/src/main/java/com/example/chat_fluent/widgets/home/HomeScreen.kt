package com.example.chat_fluent.widgets.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.chat_fluent.R
import com.example.chat_fluent.ui.theme.Typography

@Composable
fun HomeScreen(navController: NavHostController) {
    var selectedTopicIndex by remember { mutableStateOf(-1) }
    var selectedCourseIndex by remember { mutableStateOf(-1) }

    val progress by remember { mutableStateOf(0.75f) }
    val courses = listOf(
        Courses("Grammar", duration = "2 Hours", imageRes =  R.drawable.grammar),
        Courses("Writting",duration ="3 Hours",imageRes = R.drawable.writing),
        Courses("Listening",duration ="2 Hours",imageRes = R.drawable.listening),
        Courses("Speaking",duration ="2 Hours",imageRes = R.drawable.speaking),

        )


        val topics = listOf(
        Topics("Travel", imageRes = R.drawable.travel),
        Topics("Business",imageRes =  R.drawable.business),
        Topics("Academic", imageRes = R.drawable.academic),
        Topics("User Experience",imageRes =  R.drawable.travel),
        Topics("Practice", imageRes = R.drawable.practice)
    )
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row {
                        Text("Hi, Nouran", style = MaterialTheme.typography.headlineMedium)
                        Icon(
                            Icons.Default.WavingHand, contentDescription = "Chat", Modifier
                                .size(35.dp)
                                .padding(start = 5.dp), tint = Color.Yellow
                        )

                    }
                    Text("Let's start learning!", style = Typography.titleMedium)
                }
                Icon(
                    painter = painterResource(R.drawable.feedback), contentDescription = "Chat", Modifier
                        .background(  color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
                        .size(40.dp)
                        .padding(5.dp), tint = Color.White
                )
                // Text("9:41", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(24.dp))
            // Study Hours Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("How many hours you studied this week",
                            style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { /* Handle start */ }) {
                            Text("Let's start")
                        }
                    }
                    //Icon(Icons.Default.Loop, contentDescription = "Stats")
                    LearningProgressBar(progress = progress)
//                    Text(
//                        text = "${(progress * 100).toInt()}% completed",
//                        style = MaterialTheme.typography.bodySmall,
////                        modifier = Modifier.align(Alignment.End)
//                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Courses Section
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){
                Text("Basic Courses", style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.weight(1f,fill = true))
                TextButton(onClick = {navController.navigate("courses") {
                    // Preserve home state in back stack
                    popUpTo("home") { saveState = true }
                    launchSingleTop = true
                } },
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text("See all →", style = MaterialTheme.typography.bodyLarge)

                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(courses) { index, course ->
                    course.duration?.let {
                        HomeCourseCard(
                            index=index,
                            course = course.name,
                            time = it,
                            imageRes = course.imageRes,
                            isSelected = index == selectedCourseIndex,
                            onClick = { selectedCourseIndex = index })
                    }
                }

            }
            Spacer(modifier = Modifier.height(16.dp))

            // Topics Section
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){
                Text("Topics", style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.weight(1f,fill = true))
                TextButton(onClick = {navController.navigate("topics") {
                    // Preserve home state in back stack
                    popUpTo("home") { saveState = true }
                    launchSingleTop = true
                } },
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text("See all →", style = MaterialTheme.typography.bodyLarge)

                }
            }
                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(topics) { index, course ->
                        HomeTopicsCard(
                            course = course.name,
                            imageRes = course.imageRes,
                            isSelected = index == selectedTopicIndex,
                            onClick = { selectedTopicIndex = index })
                    }

                }
            }
        }
    }

