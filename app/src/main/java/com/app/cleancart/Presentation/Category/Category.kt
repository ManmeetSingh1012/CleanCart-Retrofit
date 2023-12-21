package com.app.cleancart.Presentation.Category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.cleancart.MainActivity
import com.app.cleancart.Presentation.Common.CommonViewModal
import com.app.cleancart.ui.theme.font
import com.app.cleancart.ui.theme.text_black

/** We will show the item according to the category choosen by the user**/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Category(navHostController: NavHostController,name:String?,context:MainActivity,modal: CommonViewModal = hiltViewModel())
{

    val result = modal.product.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {

                        navHostController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack,"image")

                    }
                },
                title = {
                    Text(
                        text = name!!,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = text_black,
                        fontFamily = font
                    )
                },

                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.White)
            )
        }, content = {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding()

                )
                .background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally) {

                if(result.isloading)
                {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                    }



                }else if(result.product.isNotEmpty()) {

                    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {


                        items(result.product)
                        {
                            if(it.category == name!!)
                            {
                                category_screen(it,navHostController,context)
                            }

                        }
                    })

                }else
                {
                    androidx.compose.material.Text(
                        text = "Something Went Wrong",
                        color = text_black,
                        fontSize = 20.sp,
                        fontFamily = font,
                        textAlign = TextAlign.Center
                    )
                }



            }
            })
}