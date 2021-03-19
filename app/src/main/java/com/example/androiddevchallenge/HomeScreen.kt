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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Garden
import com.example.androiddevchallenge.data.TestData
import com.example.androiddevchallenge.data.TestData.columnGardenData
import com.example.androiddevchallenge.ui.theme.Green300
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.Pink900
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = "Search",
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            onValueChange = { s: String -> },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = "Search",
                    modifier = Modifier.size(18.dp)
                )
            },
            placeholder = {
                Text(
                    text = "Search",
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.body1
                )
            }
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Browse Theme",
                modifier = Modifier.paddingFromBaseline(top = 32.dp, bottom = 16.dp),
                style = MaterialTheme.typography.h1
            )

            LazyRow {
                item {
                    TestData.rowGardenData.forEach {
                        GridListLayout(it)
                    }
                    Spacer(Modifier.width(10.dp))
                }
            }
        }
        LazyColumn {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 24.dp, bottom = 8.dp, end = 16.dp),
                ) {
                    Column(
                        modifier = Modifier.weight(9f),
                    ) {
                        Text(
                            text = "Design your home garden",
                            style = MaterialTheme.typography.h1,
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FilterList,
                            modifier = Modifier
                                .size(24.dp),
                            contentDescription = null
                        )
                    }
                }
                columnGardenData.forEach {
                    SingleListLayout(it)
                }
            }
        }
    }
}

@Composable
fun GridListLayout(garden: Garden) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .wrapContentHeight()
            .padding(5.dp)
            .clickable { },
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.wrapContentSize()
        ) {
            GlideImage(
                garden.imgUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(126.dp),
                contentDescription = null,
                fadeIn = true,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(10.dp),
                text = garden.name,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun SingleListLayout(data: Garden) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .height(100.dp)
            .padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
    ) {
        Card {
            GlideImage(
                data.imgUrl,
                modifier = Modifier.size(100.dp),
                contentDescription = null,
                fadeIn = true,
                contentScale = ContentScale.Crop,
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row {
                Column(
                    modifier = Modifier.weight(85f),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 16.dp),
                        text = data.name,
                        style = MaterialTheme.typography.h2
                    )
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "This is a description",
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Column(
                    modifier = Modifier.weight(15f),
                ) {
                    CheckBox()
                }
            }
            Spacer(Modifier.height(33.dp))
            Divider(
                Modifier.height(1.dp).padding(start = 10.dp)
            )
        }
    }
}

@Composable
fun CheckBox() {
    val isLightTheme = MaterialTheme.colors.isLight
    val color = if (isLightTheme) Pink900 else Green300

    val checkedState = remember { mutableStateOf(false) }
    Checkbox(
        modifier = Modifier
            .padding(top = 24.dp)
            .size(24.dp)
            .clickable {
                checkedState.value = !checkedState.value
            },
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it },
        colors = CheckboxDefaults.colors(
            checkedColor = color
        )
    )
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
