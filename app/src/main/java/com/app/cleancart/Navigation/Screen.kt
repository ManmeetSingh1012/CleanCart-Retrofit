package com.app.cleancart.Navigation

/** Our Screens**/
sealed class Screen(val route:String)
{
    object Splash:Screen("SplashScreen")
    object Home:Screen("HomeScreen")
    object Detail:Screen("DetailScreen")
    object Category:Screen("Category")
}
