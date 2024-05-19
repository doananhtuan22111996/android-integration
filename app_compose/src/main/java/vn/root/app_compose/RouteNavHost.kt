package vn.root.app_compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import vn.root.app_compose.pages.ExerciseOne
import vn.root.app_compose.pages.ExerciseTwo
import vn.root.app_compose.pages.Main
import vn.root.app_compose.pages.OnBoarding

const val ON_BOARDING_ROUTE = "on_boarding"
const val MAIN_ROUTE = "main"
const val EXERCISE_ONE_ROUTE = "exercise_one"
const val EXERCISE_TWO_ROUTE = "exercise_two"

@Composable
fun RouteNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
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
					else -> {}
				}
			})
		}
		composable(EXERCISE_ONE_ROUTE) {
			ExerciseOne(onBackPress = {
				navController.popBackStack()
			})
		}
		composable(EXERCISE_TWO_ROUTE) {
			ExerciseTwo(onBackPress = {
				navController.popBackStack()
			})
		}
	}
}