package com.example.chat_fluent.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.chat_fluent.widgets.topics.TopicsScreen

import com.example.chat_fluent.widgets.home.HomeScreen
import com.example.chat_fluent.widgets.courses.CoursesScreen
import com.example.chat_fluent.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomNavBar(navController, currentRoute = currentRoute
        ) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(BottomNavItem.Home.route) { HomeScreen(navController) }
            composable(BottomNavItem.Topics.route) { TopicsScreen( navController = navController,
                onBack = {
                    // Navigate to home and reset the back stack
                    navController.navigate(BottomNavItem.Home.route) {
                        popUpTo(BottomNavItem.Home.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }

                }) }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
            composable(BottomNavItem.Courses.route) { CoursesScreen(
                navController = navController,
                onBack = {
                    // Navigate to home and reset the back stack
                    navController.navigate(BottomNavItem.Home.route) {
                        popUpTo(BottomNavItem.Home.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }

                }
            ) }

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