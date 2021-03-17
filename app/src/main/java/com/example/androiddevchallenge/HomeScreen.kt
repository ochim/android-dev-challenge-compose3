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

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                class Nav(val title: String, val resource: Int)

                val navs = listOf(
                    Nav("Home", R.drawable.ic_baseline_home_24),
                    Nav("Favorite", R.drawable.ic_baseline_favorite_border_24),
                    Nav("Profile", R.drawable.ic_baseline_account_circle_24),
                    Nav("Cart", R.drawable.ic_baseline_shopping_cart_24),
                )
                var selected by remember { mutableStateOf(0) }

                navs.forEachIndexed { index, nav ->
                    BottomNavigationItem(
                        selected = selected == index,
                        label = {
                            Text(
                                text = nav.title,
                                style = MaterialTheme.typography.caption,
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = nav.resource),
                                contentDescription = nav.title,
                            )
                        },
                        onClick = {
                            selected = index
                        }
                    )
                }
            }
        }
    ) {
        NavHost(navController, startDestination = "bHome") {
            composable("bHome") { HomeScreen() }
            composable("Favorite") { }
            composable("Profile") { }
            composable("Cart") { }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeLightPreview() {
    MyTheme {
        HomeScreen()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeDarkPreview() {
    MyTheme(darkTheme = true) {
        HomeScreen()
    }
}
