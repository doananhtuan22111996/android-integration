package vn.root.app.pages.materialKit.topTabBar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import vn.root.app.R
import vn.root.app.databinding.FragmentTopAppBarBinding

class TopTabBarFragment : Fragment() {
	
	private lateinit var viewBinding: FragmentTopAppBarBinding
	private lateinit var navController: NavController
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewBinding = FragmentTopAppBarBinding.inflate(inflater, container, false)
		navController = findNavController()
		return viewBinding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewBinding.layoutHeader.toolbar.apply {
			title = getString(R.string.top_tab_bars)
			setNavigationOnClickListener { navController.popBackStack() }
			menu.clear()
		}
		
		viewBinding.layoutHeader1.toolbar.apply {
			title = getString(R.string.welcome)
		}
		
		viewBinding.layoutHeader2.toolbar.apply {
			title = getString(R.string.welcome)
		}
		
		viewBinding.layoutHeader3.toolbar.apply {
			title = getString(R.string.welcome)
		}
	}
}