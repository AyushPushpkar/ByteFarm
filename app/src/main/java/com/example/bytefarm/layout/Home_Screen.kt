package com.example.vsgarments.layout

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    modifier: Modifier,
    navController: NavController,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appbackgroundcolor)

    ) {

        val focusManager = LocalFocusManager.current
        val focusRequester = FocusRequester()

        var searchQuery by remember { mutableStateOf("") }
        val context = LocalContext.current

        var isFilteringApplied by rememberSaveable { mutableStateOf(false) }
        val keyboardController = LocalSoftwareKeyboardController.current

        Column (
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
        ) {

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(horizontal = 30.dp)
        ){

            Card(
                shape = RoundedCornerShape(50.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(50.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(5.dp))

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(topbardarkblue)
                    ) {
                        IconButton(
                            onClick = {
                                searchQuery.ifEmpty { null }?.let {

                                }
                                if (searchQuery != "")isFilteringApplied = true

                                keyboardController?.hide()
                            },
                        ) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        }
                    }

                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search products...") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                searchQuery.ifEmpty { null }?.let {

                                }
                                isFilteringApplied = true
                                keyboardController?.hide() // Hide keyboard when search is triggered
                            }
                        ),
                        modifier = Modifier.weight(1f)
                            .focusRequester(focusRequester)
                            .clip(RoundedCornerShape(bottomEnd = 50.dp, topEnd = 50.dp)),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            focusedLabelColor = appbackgroundcolor,
                            focusedTextColor = textcolorgrey,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedLabelColor = textcolorblue,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color.White,
                            unfocusedTextColor = textcolorgrey,
                            disabledTextColor = textcolorgrey,
                            disabledLabelColor = textcolorblue,
                            disabledIndicatorColor = Color.Transparent,
                            disabledContainerColor = Color.White,
                            cursorColor = textcolorblue,
                        ),
                    )

                    if (searchQuery.isNotEmpty() || isFilteringApplied) {
                        IconButton(
                            onClick = {
                                searchQuery = ""
//                                productViewModel.clearSearch() // Reset to normal products
                                isFilteringApplied = false
                                keyboardController?.hide()
                            }
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Clear search",
                                tint = Color.Gray
                            )
                        }
                    }


                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }



    }
}


@Composable
fun HomeItemCard(

    navController: NavController ,
    context: Context
) {

    val screenwidth = LocalConfiguration.current.screenWidthDp.dp - 6.dp

//    val imageItemJson = Gson().toJson(product)
//    val encodedProductItem = URLEncoder.encode(imageItemJson, "UTF-8")

    Column(
            modifier = Modifier
                .width(screenwidth / 2)
                .padding(10.dp)
        ) {

        }
    }
}

