package com.learn.mysunfloweapp.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.learn.mysunfloweapp.compose.home.HomePagerScreen
import com.learn.mysunfloweapp.compose.plantdetail.PlantDetailsScreen

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
                navHostController.navigate("plantDetail/${it.plantId}")
            })
        }

        composable(
            route = "plantDetail/{plantId}",
            arguments = listOf(navArgument("plantId") {
                type = NavType.StringType
            })
        ) {
            PlantDetailsScreen(
                onBackClick = { navHostController.navigateUp() },
                onShareClick = {
//                    createShareIntent(activity, it)
                },
                onGalleryClick = {
                    navHostController.navigate("gallery/${it.name}")
                }
            )
        }

        composable(
            route = "gallery/{plantName}",
            arguments = listOf(navArgument("plantName") {
                type = NavType.StringType
            })
        ) {}


    }

}