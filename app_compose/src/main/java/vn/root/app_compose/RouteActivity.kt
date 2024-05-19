package vn.root.app_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import vn.root.app_compose.ui.theme.ApplicationTheme

class RouteActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		installSplashScreen()
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyApp()
		}
	}
}

@Composable
fun MyApp() {
	val navController = rememberNavController()
	ApplicationTheme {
		Scaffold { innerPadding ->
			RouteNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
		}
	}
}
