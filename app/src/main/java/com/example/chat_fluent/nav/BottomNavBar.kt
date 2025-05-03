package com.example.chat_fluent.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.chat_fluent.ui.theme.Orange
import com.example.chat_fluent.ui.theme.buttonColorSignup
import com.google.android.material.bottomnavigation.BottomNavigationItemView

@Composable
fun BottomNavBar(navController: NavHostController, currentRoute: String?) {
    val items = BottomNavItem.items
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Surface(
        color = Color.White,
        tonalElevation = NavigationBarDefaults.Elevation, // Maintains shadow
    ) {
        NavigationBar(
            containerColor = Color.Transparent, // Let Surface handle color
        )
        {
            items.forEach { item ->
                val selected = currentRoute == item.route

                NavigationBarItem(
                    selected = selected,

                    //selected = selectedItemIndex == index,
                    onClick = {
                        // selectedItemIndex = index
                        if (!selected) {

                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                    /*onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }*/,
                    alwaysShowLabel = false,
                    icon = {
                        Icon(
                            painter = painterResource(
                                if (selected) {
                                    item.selectedIcon
                                } else {
                                    item.unSelectedIcon
                                }
                            ),
                            contentDescription = item.label,
                            tint = if (selected) {
                                Orange // Selected icon color
                            } else {
                                Color.Gray // Unselected icon color
                            }
                        )
                    },
                    label = { Text(text = item.label) }
                )
            }
        }
    }
        }




/*
@Composable
fun BottomNavigation(backgroundColor: Color, modifier: Modifier, content: () -> Unit) {

}

// Helper function to get current route
@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}*/
///worked
/*
@Composable
fun BottomNavBar(navController: NavHostController, currentRoute: String?) {
    val items = BottomNavItem.items
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

            NavigationBar(
                containerColor = Color.White
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        /*onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }*/,
                        alwaysShowLabel = false,
                        icon = {
                            Icon(
                                painter = painterResource( if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unSelectedIcon
                                }),
                                contentDescription = item.label,
                                tint = if (index == selectedItemIndex) {
                                    Orange // Selected icon color
                                } else {
                                    Color.Gray // Unselected icon color
                                }
                            )
                        },
                        label = { Text(text = item.label) }
                    )
                }
            }
        }
* */