package vn.root.app_compose

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import vn.root.app_compose.pages.ExerciseOne
import vn.root.app_compose.pages.ExerciseThree
import vn.root.app_compose.pages.ExerciseTwo
import vn.root.app_compose.pages.Main
import vn.root.app_compose.pages.OnBoarding
import vn.root.app_compose.pages.home.HomeScreen
import vn.root.app_compose.pages.login.LoginScreen

const val ON_BOARDING_ROUTE = "on_boarding"
const val MAIN_ROUTE = "main"
const val EXERCISE_ONE_ROUTE = "exercise_one"
const val EXERCISE_TWO_ROUTE = "exercise_two"
const val EXERCISE_THREE_ROUTE = "exercise_three"
const val LOGIN_ROUTE = "login"
const val HOME_ROUTE = "home"

@Composable
fun RouteNavHost(windowSizeClass: WindowSizeClass, navController: NavHostController) {
    NavHost(navController = navController, startDestination = ON_BOARDING_ROUTE) {
        composable(ON_BOARDING_ROUTE) {
            OnBoarding(navigate = {
                navController.navigate(MAIN_ROUTE) {
                    popUpTo(MAIN_ROUTE) { inclusive = true }
                }
            })
        }
        composable(MAIN_ROUTE) {
            Main(onItemClick = {
                when (it) {
                    0 -> navController.navigate(EXERCISE_ONE_ROUTE)
                    1 -> navController.navigate(EXERCISE_TWO_ROUTE)
                    2 -> navController.navigate(EXERCISE_THREE_ROUTE)
                    3 -> navController.navigate(LOGIN_ROUTE)
                    else -> {}
                }
            })
        }
        composable(EXERCISE_ONE_ROUTE) {
            ExerciseOne(onBackPress = {
                navController.navigateUp()
            })
        }
        composable(EXERCISE_TWO_ROUTE) {
            ExerciseTwo(windowSizeClass = windowSizeClass, onBackPress = {
                navController.navigateUp()
            })
        }
        composable(EXERCISE_THREE_ROUTE) {
            ExerciseThree(onBackPress = {
                navController.navigateUp()
            })
        }
        composable(LOGIN_ROUTE) {
            LoginScreen(onBackPress = {
                navController.navigateUp()
            }, onLoginSuccess = {
                navController.navigate(HOME_ROUTE)
            })
        }
        composable(HOME_ROUTE) {
            HomeScreen(onBackPress = { navController.navigateUp() })
        }
    }
}
