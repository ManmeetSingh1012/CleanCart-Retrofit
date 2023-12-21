package com.app.cleancart.Presentation.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.app.cleancart.MainActivity
import com.app.cleancart.Navigation.Screen
import com.app.cleancart.R
import com.app.cleancart.domain.Modal.Product
import com.app.cleancart.ui.theme.blue
import com.app.cleancart.ui.theme.font
import com.app.cleancart.ui.theme.text_black
import kotlin.math.roundToInt


/** this is the screen where we will show the products fetched from the api and this function is called from Home Screen Compose **/
@Composable
fun main_screen_items(it: Product, navHostController: NavHostController,context:MainActivity)
{
    Card(modifier = Modifier.height(height = 250.dp)

        .padding(5.dp)
        .clickable {

            navHostController.navigate(Screen.Detail.route+"/${it.id}")

        }, elevation = CardDefaults.cardElevation(5.dp)
        , colors = CardDefaults.cardColors(containerColor = Color.White),shape = RoundedCornerShape(20.dp)
    ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally)
        {



            Surface(modifier = Modifier.size(100.dp), shape = RoundedCornerShape(10.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context).data(it.thumbnail).apply {
                            size(coil.size.Size.ORIGINAL)
                        }.build()
                    ), contentDescription = null , contentScale = ContentScale.FillBounds
                )
            }




            Text(
                text = it.title,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = text_black,
                fontFamily = font
            )


            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween)
            {
                Text(
                    text = "$${it.price}",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = text_black,
                    fontFamily = font,

                )

                Text(
                    text = "${it.discountPercentage}% off",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = blue,
                    fontFamily = font,

                )
            }

            Row(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.Center) {

                val count = it.rating.toFloat().roundToInt()
                repeat(count){

                    Image(painter = painterResource(id = R.drawable.star),
                        contentDescription = "start" ,
                        modifier = Modifier.padding(3.dp))
                }

            }



        }
    }
}