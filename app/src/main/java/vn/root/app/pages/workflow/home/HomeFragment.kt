package vn.root.app.pages.workflow.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.ui.setupWithNavController
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.root.app.R
import vn.root.app.base.BaseFragment
import vn.root.app.databinding.FragmentHomeBinding
import vn.root.app.pages.root.RootViewModel
import vn.root.app.pages.workflow.left.LeftFragment
import vn.root.app.pages.workflow.right.RightFragment

class HomeFragment : BaseFragment<RootViewModel, HomeViewModel, FragmentHomeBinding>() {
	
	private val leftFragment = LeftFragment()
	private val rightFragment = RightFragment()
	
	override val sharedViewModel: RootViewModel by activityViewModel()
	
	override val viewModel: HomeViewModel by viewModel()
	
	override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
		FragmentHomeBinding::inflate
	
	override fun onInit(view: View, savedInstanceState: Bundle?) {
		viewBinding.layoutHeader.toolbar.apply {
			title = getString(R.string.home)
			navigationIcon = null
		}
		childFragmentManager.beginTransaction().apply {
			add(R.id.fragmentContainer, leftFragment)
			add(R.id.fragmentContainer, rightFragment).hide(rightFragment)
		}.commit()
		viewBinding.bottomNavigation.apply {
			setupWithNavController(navController)
			setOnItemSelectedListener {
				when (it.itemId) {
					R.id.mLeft -> childFragmentManager.beginTransaction().apply {
						hide(rightFragment)
						show(leftFragment)
					}.commit()
					
					R.id.mRight -> childFragmentManager.beginTransaction().apply {
						hide(leftFragment)
						show(rightFragment)
					}.commit()
				}
				true
			}
		}
	}
}