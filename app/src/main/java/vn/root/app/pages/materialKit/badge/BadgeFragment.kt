package vn.root.app.pages.materialKit.badge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import vn.root.app.R
import vn.root.app.databinding.FragmentBadgeBinding

class BadgeFragment : Fragment() {
	
	private lateinit var viewBinding: FragmentBadgeBinding
	private lateinit var navController: NavController
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewBinding = FragmentBadgeBinding.inflate(inflater, container, false)
		navController = findNavController()
		return viewBinding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewBinding.layoutHeader.toolbar.apply {
			title = getString(R.string.badge)
			setNavigationOnClickListener { navController.popBackStack() }
			menu.clear()
		}
		
		val badgeDrawable = BadgeDrawable.create(requireContext())
		BadgeUtils.attachBadgeDrawable(badgeDrawable, viewBinding.bottomNavigation);
	}
}