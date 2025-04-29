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
import com.example.chat_fluent.R

sealed class BottomNavItem (
    val route: String,
    val label: String,
    val selectedIcon: Int,
    val unSelectedIcon: Int,

){
    object Home : BottomNavItem(
        route = "home",
        label = "Home",
        selectedIcon= R.drawable.home,
        unSelectedIcon = R.drawable.home
    )
    object Courses : BottomNavItem(
        route = "courses",
        label = "Courses",
        selectedIcon=R.drawable.courses,
        unSelectedIcon = R.drawable.courses
    )
    object Topics : BottomNavItem(
        route = "topics",
        label = "Topics",
        selectedIcon=R.drawable.document,
        unSelectedIcon = R.drawable.document
    )
    object Profile : BottomNavItem(
        route = "profile",
        label = "Profile",
        selectedIcon=R.drawable.user,
        unSelectedIcon = R.drawable.user
    )


    companion object {
        val items = listOf(Home,  Courses, Topics, Profile,)
    }
}