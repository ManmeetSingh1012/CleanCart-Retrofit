package com.app.cleancart.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.cleancart.MainActivity
import com.app.cleancart.Presentation.Category.Category
import com.app.cleancart.Presentation.Home.HomeScreen
import com.app.cleancart.Presentation.Splash.SplashScreen
import com.app.cleancart.Presentation.detail.DetailScreen


/** Its our nav graph**/
@Composable
fun NavGraph(navhost:NavHostController,context:MainActivity) {

NavHost(navController = navhost, startDestination = Screen.Splash.route){

    composable(Screen.Splash.route)
    {
        SplashScreen(navhost)
    }

    composable(Screen.Home.route)
    {
        HomeScreen(navhost,context)
    }

    composable(Screen.Detail.route+"/{id}")
    {
        val id = it.arguments?.getString("id")
        DetailScreen(navHostController = navhost,id,context)
    }

    composable(Screen.Category.route+"/{name}")
    {
        val name = it.arguments?.getString("name")
        Category(navHostController = navhost, name = name , context = context)
    }
}
}