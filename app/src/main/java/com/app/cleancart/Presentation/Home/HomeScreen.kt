package com.app.cleancart.Presentation.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.cleancart.MainActivity
import com.app.cleancart.Navigation.Screen
import com.app.cleancart.Presentation.Common.CommonViewModal
import com.app.cleancart.R
import com.app.cleancart.domain.Modal.common
import com.app.cleancart.Presentation.Common.productmodal
import com.app.cleancart.ui.theme.font
import com.app.cleancart.ui.theme.text_black


/** Its our main Screen or Home Screen**/
@Composable
fun HomeScreen(navHostController: NavHostController, context:MainActivity, commonViewModal: CommonViewModal = hiltViewModel())
{

    val result = commonViewModal.product.collectAsState().value

    Scaffold( backgroundColor =  Color.White,
                topBar = {
                        Row(
                         modifier = Modifier.padding(15.dp)) {

                           Text(
                                text = "Clean Cart",
                                color = text_black,
                                fontSize = 22.sp,
                                fontFamily = font,
                               modifier = Modifier.weight(0.9f)
                            )

                            Image(painter = painterResource(id = R.drawable.cart),
                                contentDescription = "cart",
                                modifier = Modifier.weight(0.1f) )

                        }
                },
        content = {

                ScreenContent(modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = it.calculateTopPadding().plus(10.dp),
                        start = 15.dp, end = 10.dp, bottom = 10.dp),
                    navHostController,result,context)
        }
    )
}

@Composable
fun ScreenContent(modifier: Modifier, navHostController: NavHostController, result: productmodal, context: MainActivity)
{

    // List to show the Category
    val list_category = listOf(
        common(R.drawable.gadget,"smartphones"),
        common(R.drawable.gadget,"laptops"),
        common(R.drawable.grocery,"groceries"),
        common(R.drawable.perfume,"fragrances"),
        common(R.drawable.decor,"home-decoration"),
                common(R.drawable.skincare,"skincare")
    )



    Column(modifier = modifier) {

        // if result is still loading
        if(result.isloading)
        {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
            }
        }else if(result.product.isNotEmpty())
        {
            // when result is loaded we will show the data
            Text(
                text = "Explore Categories",
                color = text_black,
                fontSize = 20.sp,
                fontFamily = font,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(10.dp))

            // to show the catogry item using Lazy Row
            LazyRow(content = {
                items(list_category)
                {
                    category(it,navHostController)
                    Spacer(modifier = Modifier.width(15.dp))
                }
            })

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Feature Products",
                color = text_black,
                fontSize = 20.sp,
                fontFamily = font,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(20.dp))

            // showing the feature product or main products
            LazyVerticalGrid(columns = GridCells.Fixed(2), content = {

                items(result.product)
                {
                    main_screen_items(it,navHostController,context)
                }
            })
        }else
        {
            // when something went wrong or error comes while fetching the data
            Text(
                text = "Something Went Wrong",
                color = text_black,
                fontSize = 20.sp,
                fontFamily = font,
                textAlign = TextAlign.Center
            )
        }


    }
}


@Composable
private fun category(it: common,navHostController: NavHostController)
{
    Column(modifier = Modifier.clickable {
        navHostController.navigate(Screen.Category.route+"/${it.name}")
    }

        .padding(5.dp),
        verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

        Image(painter = painterResource(it.image), contentDescription ="bag" )


        androidx.compose.material3.Text(
            text = it.name,
            color = text_black,
            fontSize = 14.sp,
            fontFamily = font
        )
    }
}