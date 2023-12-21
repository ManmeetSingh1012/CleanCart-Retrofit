package com.app.cleancart.Presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.app.cleancart.MainActivity
import com.app.cleancart.Presentation.Common.CommonViewModal
import com.app.cleancart.R
import com.app.cleancart.Presentation.Common.productmodal
import com.app.cleancart.ui.theme.BlueGray50
import com.app.cleancart.ui.theme.BlueGray900
import com.app.cleancart.ui.theme.blue
import com.app.cleancart.ui.theme.font
import com.app.cleancart.ui.theme.fontB
import com.app.cleancart.ui.theme.text_black
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(navHostController: NavHostController, id:String?, context:MainActivity, commonViewModal: CommonViewModal = hiltViewModel())
{
    val state = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    val result = commonViewModal.product.collectAsState().value

    val scope = rememberCoroutineScope()

    BottomSheetScaffold(sheetContent = {

        sheetcontent(result,id)
    } , content = {
        backgroundcontent(result,id,context,navHostController)
    }, sheetShape = RoundedCornerShape(30.dp), sheetBackgroundColor = Color.White, backgroundColor = Color.White, drawerBackgroundColor = Color.White,
        sheetPeekHeight = 300.dp , sheetGesturesEnabled = true , scaffoldState = state , sheetElevation = 20.dp  )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun backgroundcontent(result: productmodal, id: String?, context: MainActivity, navHostController: NavHostController)
{


    val pagerState = rememberPagerState()

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
                        text = "Product",
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

                Spacer(modifier = Modifier.height(50.dp))



                if(result.isloading)
                {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                    }


                }else if(result.product.isNotEmpty()) {


                    val product = result.product.find {
                        it.id == id?.toInt()
                    }

                    // pager to show images in sliding form
                    // used coil library to load the image
                    HorizontalPager(count = product!!.images.size , state = pagerState,) {

                        Surface(modifier = Modifier.size(250.dp), shape = RoundedCornerShape(10.dp)) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(context).data(product!!.images[it]).apply {
                                        size(coil.size.Size.ORIGINAL)
                                    }.build()
                                ), contentDescription = null , contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        indicatorShape = CircleShape,
                        activeColor = BlueGray900,
                        inactiveColor = BlueGray50,
                        indicatorWidth = 10.dp,
                        spacing = 8.dp
                    )

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




@Composable
fun sheetcontent(result: productmodal, id: String?)
{

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {

        if(result.isloading)
        {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
            }

        }else if(result.product.isNotEmpty()) {

            val it = result.product.find {
                it.id == id!!.toInt()
            }
            Divider(color = text_black, thickness = 1.5f.dp, modifier = Modifier.padding(start = 110.dp,end = 110.dp))

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = it!!.title,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                color = text_black,
                fontFamily = fontB
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                , horizontalArrangement = Arrangement.SpaceBetween)
            {
                Text(
                    text = "$${it!!.price}",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    color = text_black,
                    fontFamily = font,
                    modifier = Modifier.weight(0.4f)
                )

                Text(
                    text = "${it!!.discountPercentage}% off",
                    fontSize = 18.sp,
                    color = blue,
                    fontFamily = font,
                    modifier = Modifier.weight(0.4f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.Start) {

                val count = it!!.rating.toFloat().toInt()
                repeat(count){

                    Image(painter = painterResource(id = R.drawable.star), contentDescription = "start" , modifier = Modifier.padding(1.dp))
                }

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "${it.rating}",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    color = text_black,
                    fontFamily = font
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Discription:",
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                color = text_black,
                fontFamily = font
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = it!!.description,
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                color = text_black,
                fontFamily = font
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                , horizontalArrangement = Arrangement.SpaceBetween)
            {
                Text(
                    text = "Category:",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    color = text_black,
                    fontFamily = font,
                    modifier = Modifier.weight(0.4f)
                )

                Text(
                    text = it!!.category,
                    fontSize = 16.sp,
                    color = blue,
                    fontFamily = font,
                    modifier = Modifier.weight(0.4f)
                )
            }


            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                , horizontalArrangement = Arrangement.SpaceBetween)
            {
                Text(
                    text = "Stock Left:",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    color = text_black,
                    fontFamily = font,
                    modifier = Modifier.weight(0.4f)
                )

                Text(
                    text = it!!.stock.toString(),
                    fontSize = 16.sp,
                    color = blue,
                    fontFamily = font,
                    modifier = Modifier.weight(0.4f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                , horizontalArrangement = Arrangement.SpaceBetween)
            {
                Text(
                    text = "Brand:",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    color = text_black,
                    fontFamily = font,
                    modifier = Modifier.weight(0.4f)
                )

                Text(
                    text = it!!.brand,
                    fontSize = 16.sp,
                    color = blue,
                    fontFamily = font,
                    modifier = Modifier.weight(0.4f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {


                },
                elevation = ButtonDefaults.buttonElevation(5.dp),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp)
                ,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = blue)
            ) {

                Text(
                    "Add to Cart",
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    fontFamily = font,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
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

}