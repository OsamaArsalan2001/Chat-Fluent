package com.example.chat_fluent.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chat_fluent.FeedbackScreen
import com.example.chat_fluent.HomePage

import com.example.chat_fluent.HomePageScreen
import com.example.chat_fluent.HomeScreen
import com.example.chat_fluent.PracticeScreen
import com.example.chat_fluent.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(BottomNavItem.Home.route) { HomeScreen() }
            composable(BottomNavItem.Feedback.route) { FeedbackScreen() }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
            composable(BottomNavItem.Practice.route) { PracticeScreen() }

            }
            }


    }