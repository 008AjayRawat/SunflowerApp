package com.learn.mysunfloweapp.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learn.mysunfloweapp.compose.home.HomePagerScreen

@Composable
fun SunflowerApp() {
    val navHostController = rememberNavController() //will create the NavController
    SunFlowerNavHost(navHostController = navHostController)
}


@Composable
fun SunFlowerNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = "home"
    ) {

        composable(route = "home") {
            HomePagerScreen(onPlantClick = {

            })
        }

        composable(route = "plantDetail/{plantId}") {}

        composable(route = "gallery/{plantName}") {}


    }

}