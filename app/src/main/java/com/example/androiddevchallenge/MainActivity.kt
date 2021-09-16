/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.More
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.Gray
import com.example.androiddevchallenge.ui.theme.Green900
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.Pink100

sealed class ScreenList(val route: String, val icon: ImageVector) {
    object WelcomeScreen : ScreenList("Welcome", Icons.Filled.More)
    object LoginScreen : ScreenList("Login", Icons.Filled.More)
    object HomeScreen : ScreenList("Home", Icons.Filled.Home)
    object FavoriteScreen : ScreenList("Favorite", Icons.Filled.FavoriteBorder)
    object ProfileScreen : ScreenList("Profile", Icons.Filled.AccountCircle)
    object CartScreen : ScreenList("Cart", Icons.Filled.ShoppingCart)
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val items: List<ScreenList> = listOf(
        ScreenList.HomeScreen,
        ScreenList.FavoriteScreen,
        ScreenList.ProfileScreen,
        ScreenList.CartScreen,
        ScreenList.WelcomeScreen,
        ScreenList.LoginScreen
    )

    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        bottomBar = {
            if (currentRoute != ScreenList.WelcomeScreen.route &&
                currentRoute != ScreenList.LoginScreen.route
            ) {
                val isLightTheme = MaterialTheme.colors.isLight
                val color1 = if (isLightTheme) Gray else Color.White
                val color2 = if (isLightTheme) Color.LightGray else Color.Gray
                val bgColor = if (isLightTheme) Pink100 else Green900

                BottomNavigation(
                    backgroundColor = bgColor
                ) {
                    items.filter {
                        it.route != ScreenList.WelcomeScreen.route &&
                            it.route != ScreenList.LoginScreen.route
                    }.forEach { screen ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    imageVector = screen.icon,
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(
                                    text = screen.route,
                                    style = MaterialTheme.typography.subtitle2
                                )
                            },
                            selected = screen.route == currentRoute,
                            alwaysShowLabel = true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    launchSingleTop = true
                                }
                            },
                            selectedContentColor = color1,
                            unselectedContentColor = color2
                        )
                    }
                }
            }
        },
        scaffoldState = scaffoldState
    ) {
        NavHost(navController, startDestination = ScreenList.WelcomeScreen.route) {
            composable(
                route = ScreenList.WelcomeScreen.route,
            ) {
                WelcomeScreen {
                    navController.navigate(ScreenList.LoginScreen.route)
                }
            }
            composable(
                route = ScreenList.LoginScreen.route,
            ) {
                LoginScreen {
                    navController.navigate(ScreenList.HomeScreen.route)
                }
            }
            composable(
                route = ScreenList.HomeScreen.route,
            ) {
                HomeScreen()
            }
            composable(
                route = ScreenList.FavoriteScreen.route,
            ) { }
            composable(
                route = ScreenList.ProfileScreen.route,
            ) { }
            composable(
                route = ScreenList.CartScreen.route,
            ) { }
        }
    }
}
