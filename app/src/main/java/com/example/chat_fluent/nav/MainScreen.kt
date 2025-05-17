package com.example.chat_fluent.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.chat_fluent.FeedbackScreen
import com.example.chat_fluent.OpenAIChatScreen
import com.example.chat_fluent.widgets.topics.TopicsScreen

import com.example.chat_fluent.widgets.home.HomeScreen
import com.example.chat_fluent.widgets.courses.CoursesScreen
import com.example.chat_fluent.ProfileScreen
import com.example.chat_fluent.chatbot.OpenAIChatRepository
import com.example.chat_fluent.chatbot.view.OpenAIChatScreen
import com.example.chat_fluent.chatbot.view.OpenAITestScreen
import com.example.chat_fluent.chatbot.viewmodel.OpenAIChatViewModel
import com.example.chat_fluent.chatbot.viewmodel.OpenAIViewModelFactory
import com.example.chat_fluent.data.network.gemini.OpenAIClient
import com.example.chat_fluent.data.network.gemini.prompts.EnglishTutorPrompt

//import com.example.chat_fluent.chatscreen
//import com.example.chat_fluent.chatviewmodel

@Composable
fun MainScreen() {
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomNavBar(bottomNavController, currentRoute = currentRoute
        ) }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(BottomNavItem.Home.route) { HomeScreen(bottomNavController) }
            composable(BottomNavItem.Topics.route) { TopicsScreen( navController = bottomNavController,
                onBack = {
                    // Navigate to home and reset the back stack
                    bottomNavController.navigate(BottomNavItem.Home.route) {
                        popUpTo(BottomNavItem.Home.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }

                }) }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
            composable(BottomNavItem.Courses.route) { CoursesScreen(
                navController = bottomNavController,
                onBack = {
                    // Navigate to home and reset the back stack
                    bottomNavController.navigate(BottomNavItem.Home.route) {
                        popUpTo(BottomNavItem.Home.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }

                }
            ) }

            // Add chat screen to main navigation
//            composable("chat") {
//                val chatViewModel = chatviewmodel()
//                chatscreen(chatViewModel)
//            }
//            composable("openai_chat") {
//                val chatViewModel = OpenAIChatViewModel()
//                OpenAIChatScreen(chatViewModel)
//            }
//            composable(
//                route = OpenAIChatScreen.route,
//                arguments = listOf(
//                    navArgument("topic") {
//                        type = NavType.StringType
//                        nullable = true
//                    }
//                )
//            ) { backStackEntry ->
//                val topic = backStackEntry.arguments?.getString("topic")
//                OpenAIChatScreen(
//                    onBackClick = {
//                        bottomNavController.navigate(BottomNavItem.Home.route) {
//                            popUpTo(BottomNavItem.Home.route) {
//                                inclusive = true
//                            }
//                            launchSingleTop = true
//                        }
//                    },
//                    topic = topic,
//                    onFeedbackClick =   {bottomNavController.navigate(BottomNavItem.Home.route) {
//                        popUpTo(BottomNavItem.Home.route) {
//                            inclusive = true
//                        }
//                        launchSingleTop = true
//                    }}
//                )
//            }
//            composable("temp") {
//                OpenAITestScreen()
//            }

//            composable("temp/{topic}") { backStackEntry ->
//                val topic = backStackEntry.arguments?.getString("topic")
//                OpenAITestScreen(topic = topic)
//            }
            composable(
                route = OpenAIChatScreen.route,
                arguments = listOf(
                    navArgument(OpenAIChatScreen.TOPIC_ARG) {
                        type = NavType.StringType
                        nullable = true
                    }
                )
            ) { backStackEntry ->
                val topic = backStackEntry.arguments?.getString(OpenAIChatScreen.TOPIC_ARG)
                val chatViewModel: OpenAIChatViewModel = viewModel(
                    factory = OpenAIViewModelFactory(
                        OpenAIChatRepository.getInstance(
                            OpenAIClient.getInstance(),
                            EnglishTutorPrompt
                        )
                    )
                )
                // Clear chat when leaving screen
                DisposableEffect(Unit) {
                    onDispose {
                        chatViewModel.clearChat()
                    }
                }

                OpenAITestScreen(
                    onBackClick =  {
                        chatViewModel.clearChat()
                        bottomNavController.navigate(BottomNavItem.Home.route) {
                        popUpTo(BottomNavItem.Home.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }},
                    topic = topic,
                    onFeedbackClick = { bottomNavController.navigate(FeedbackScreen.route) }
                )
            }

//            composable(
//                "temp/{topic}",
//                arguments = listOf(navArgument("topic") {
//                    type = NavType.StringType
//                    nullable = true
//                })
//            ) { backStackEntry ->
//                val topic = backStackEntry.arguments?.getString("topic")
//                OpenAITestScreen(topic = topic)
//            }

        }
    }


}
//
//@Composable
//fun BottomNavBar(navController: NavController, currentRoute: String?) {
//    NavigationBar {
//        // Use .items instead of .values()
//        BottomNavItem.items.forEach { item ->
//            NavigationBarItem(
//                icon = {
//                    Icon(
//                        painter = painterResource(
//                            id = if (currentRoute == item.route) {
//                                item.selectedIcon
//                            } else {
//                                item.unSelectedIcon
//                            }
//                        ),
//                        contentDescription = item.label,
//                        tint = if (index == selectedItemIndex) {
//                            Orange // Selected icon color
//                        } else {
//                            Color.Gray // Unselected icon color
//                        }
//                    )
//                },
//                label = { Text(item.label) },
//                selected = currentRoute == item.route,
//                onClick = {
//                    navController.navigate(item.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
//}