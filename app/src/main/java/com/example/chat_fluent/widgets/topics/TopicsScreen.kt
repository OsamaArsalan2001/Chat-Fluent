package com.example.chat_fluent.widgets.topics
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chat_fluent.R
import com.example.chat_fluent.widgets.courses.CourseCard
import com.example.chat_fluent.widgets.home.Courses
import com.example.chat_fluent.widgets.home.Topics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicsScreen(navController: NavController, onBack: () -> Unit){
    var selectedCourseIndex by remember { mutableStateOf(-1) }
    val topics = listOf(
        Topics("Travel",description = "Essential phrases for hotels, directions, and cultural interactions", R.drawable.travel),
        Topics("Business",description = "Professional communication: contracts, negotiations, presentations", R.drawable.business),
        Topics("Academic",description = "Essay writing, research discussions, and scholarly vocabulary", R.drawable.academic),
        Topics("User Experience",description = "UX terminology, usability testing, and design principles", R.drawable.travel),
        Topics("Practice",description = "Conversation scenarios and real-life dialogue simulations", R.drawable.practice)
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Topics") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIos,
                            contentDescription = "Back"
                        )
                    }
                }
            )

        },


        )
    { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(topics) { index, topic ->

                TopicsCard(
                    index=index,
                    topic = topic,
                    isSelected = index == selectedCourseIndex,
                    onClick = { selectedCourseIndex = index })
                //Divider(modifier = Modifier.padding(horizontal = 16.dp))

            }

        }
    }}