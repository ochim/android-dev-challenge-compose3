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
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.theme.Gray
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.Pink900

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    WelcomeScreen(onLogin = {})
}

@Composable
fun WelcomeScreen(onLogin: () -> Unit) {
    val isLightTheme = MaterialTheme.colors.isLight
    val resourceBg = if (isLightTheme) R.drawable.ic_light_welcome_bg else R.drawable.ic_dark_welcome_bg
    val resourceIllos = if (isLightTheme) R.drawable.ic_light_welcome_illos else R.drawable.ic_dark_welcome_illos
    val resourceLogo = if (isLightTheme) R.drawable.ic_light_logo else R.drawable.ic_dark_logo

    Surface(color = MaterialTheme.colors.primary) {
        Box {
            Image(
                painter = painterResource(id = resourceBg),
                "background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (image, box, login) = createRefs()
                Image(
                    painter = painterResource(id = resourceIllos),
                    "illos",
                    modifier = Modifier
                        .padding(
                            start = 88.dp,
                            top = 72.dp
                        )
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .constrainAs(box) {
                            top.linkTo(image.bottom, 48.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {
                    Image(
                        painter = painterResource(id = resourceLogo),
                        "logo",
                        modifier = Modifier

                    )
                    Text(
                        text = "Beautiful home garden solutions",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .paddingFromBaseline(
                                top = 32.dp,
                                bottom = 40.dp
                            ),
                        color = if (isLightTheme) Gray else Color.White
                    )
                    val text = "Create account"
                    MyButton({}, text, isLightTheme)
                }
                Text(
                    text = "Log in",
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable { onLogin() }
                        .constrainAs(login) {
                            top.linkTo(box.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    color = if (isLightTheme) Pink900 else Color.White
                )
            }
        }
    }
}

@Composable
fun MyButton(onClick: () -> Unit, text: String, isLightTheme: Boolean) {
    Button(
        { onClick() },
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = if (isLightTheme) Color.White else Gray
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
