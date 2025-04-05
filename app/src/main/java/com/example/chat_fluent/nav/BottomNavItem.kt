package com.example.chat_fluent.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem (
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,

){
    object Home : BottomNavItem(
        route = "home",
        label = "Home",
        selectedIcon=Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    )
    object Practice : BottomNavItem(
        route = "practice",
        label = "Practice",
        selectedIcon=Icons.Filled.List,
        unSelectedIcon = Icons.Outlined.List
    )
    object Feedback : BottomNavItem(
        route = "feedback",
        label = "Feedback",
        selectedIcon=Icons.Filled.Info,
        unSelectedIcon = Icons.Outlined.Info
    )
    object Profile : BottomNavItem(
        route = "profile",
        label = "Profile",
        selectedIcon=Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person
    )


    companion object {
        val items = listOf(Home,  Practice, Feedback, Profile,)
    }
}