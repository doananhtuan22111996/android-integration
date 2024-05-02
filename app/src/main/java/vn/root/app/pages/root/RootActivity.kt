package vn.root.app.pages.root

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.root.app.R
import vn.root.app.base.BaseActivity

class RootActivity : BaseActivity<RootViewModel>() {
	
	override val viewModel: RootViewModel by viewModel()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		installSplashScreen()
		super.onCreate(savedInstanceState)
	}
	
	override fun onInit(savedInstanceState: Bundle?) {
		setupRootNavigation()
	}
	
	private fun setupRootNavigation() {
		val navHostFragment =
			supportFragmentManager.findFragmentById(R.id.root_host_fragment) as NavHostFragment
		val navController = navHostFragment.navController
		val navInflater = navController.navInflater
		val graph = navInflater.inflate(R.navigation.nav_root)
		// This is where you change start Destination
		graph.setStartDestination(R.id.boardingFragment)
		navHostFragment.navController.graph = graph
	}
}
