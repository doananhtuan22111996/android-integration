package vn.root.app_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import vn.root.app_compose.ui.theme.ApplicationTheme

class RouteActivity : ComponentActivity() {
	@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		installSplashScreen()
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			val windowSizeClass = calculateWindowSizeClass(this)
			MyApp(windowSizeClass)
		}
	}
}

@Composable
fun MyApp(windowSizeClass: WindowSizeClass) {
	val navController = rememberNavController()
	ApplicationTheme {
		RouteNavHost(windowSizeClass = windowSizeClass, navController = navController)
	}
}
