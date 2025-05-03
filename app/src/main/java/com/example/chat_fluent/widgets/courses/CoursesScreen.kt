package com.example.chat_fluent.widgets.courses
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
import com.example.chat_fluent.models.Courses

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesScreen(navController: NavController, onBack: () -> Unit){
    var selectedCourseIndex by remember { mutableStateOf(-1) }
    val courses = listOf(
        Courses("Grammar",description = "Essential rules for accurate speaking and writing", duration = "2 Hours", imageRes = R.drawable.grammar),
        Courses("Writting",description = "Clear expression for emails, essays, and reports",duration ="3 Hours",imageRes = R.drawable.writing),
        Courses("Listening",description = "Understand native speakers and conversations",duration ="2 Hours",imageRes = R.drawable.listening),
        Courses("Speaking",description = "Fluency practice for real-world communication",duration ="2 Hours",imageRes = R.drawable.speaking),
        )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Courses") },
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
        itemsIndexed(courses) { index, course ->

            CourseCard(
                index=index,
                course = course,
                isSelected = index == selectedCourseIndex,
                onClick = { selectedCourseIndex = index })
            //Divider(modifier = Modifier.padding(horizontal = 16.dp))

        }

    }
}}