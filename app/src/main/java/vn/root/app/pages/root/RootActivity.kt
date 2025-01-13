package vn.root.app.pages.root

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.feature.app.R
import dagger.hilt.android.AndroidEntryPoint
import vn.core.ui.base.BaseActivity

@AndroidEntryPoint
class RootActivity : BaseActivity<RootViewModel>() {

    override val viewModel: RootViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
    }

    override fun onInit(savedInstanceState: Bundle?) {
        setupRootNavigation()
    }

    private fun setupRootNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(vn.core.ui.base.R.id.root_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.nav_root)
        // This is where you change start Destination
        graph.setStartDestination(R.id.boardingFragment)
        navHostFragment.navController.graph = graph
    }
}
