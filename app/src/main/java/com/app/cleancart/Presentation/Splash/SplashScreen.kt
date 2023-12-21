package com.app.cleancart.Presentation.Splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.cleancart.Navigation.Screen
import com.app.cleancart.R
import com.app.cleancart.ui.theme.font
import com.app.cleancart.ui.theme.text_black
import kotlinx.coroutines.delay


/** this is our first Screen Splash Screen **/
@Composable
fun SplashScreen(navHostController: NavHostController)
{
    Column( modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {



            Image(painter = painterResource(id = R.drawable.bag), contentDescription ="bag" )

        /** custom font is used here **/

            Text(text = "Clean Cart",
                color = text_black,
                fontSize = 26.sp,
                fontFamily = font)

    }

    LaunchedEffect(key1 = true )
    {
        delay(1500)
        navHostController.navigate(Screen.Home.route)
    }
}