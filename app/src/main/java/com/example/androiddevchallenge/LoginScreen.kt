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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Log in with email",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .paddingFromBaseline(top = 184.dp, bottom = 16.dp)
                    .fillMaxWidth()
            )
            InputForm("Email address")
            Spacer(modifier = Modifier.height(8.dp))
            InputForm(
                "Password (8+ characters)",
                visualTransformation = PasswordVisualTransformation()
            )
            Text(
                text = buildAnnotatedString {
                    append("By clicking below, you agree to our ")
                    append(
                        AnnotatedString(
                            "Terms of Use",
                            SpanStyle(textDecoration = TextDecoration.Underline)
                        )
                    )
                    append(" and consent")
                },
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .paddingFromBaseline(top = 24.dp)
                    .fillMaxWidth()
            )
            Text(
                text = buildAnnotatedString {
                    append("to our ")
                    append(
                        AnnotatedString(
                            "Privacy Policy",
                            SpanStyle(textDecoration = TextDecoration.Underline)
                        )
                    )
                    append(".")
                },
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .paddingFromBaseline(top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
            )
            MyButton(onClick = { onLogin() }, text = "Log in")
        }
    }
}

@Composable
private fun InputForm(
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val (text, setText) = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = setText,
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1
            )
        },
        visualTransformation = visualTransformation
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginLightPreview() {
    MyTheme {
        LoginScreen(onLogin = {})
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginDarkPreview() {
    MyTheme(darkTheme = true) {
        LoginScreen(onLogin = {})
    }
}
