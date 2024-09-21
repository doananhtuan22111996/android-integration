package vn.root.app.pages.animation.hyperspaceJump

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import vn.main.app.R
import vn.main.app.databinding.FragmentHyperspaceJumpBinding

class HyperspaceJumpFragment : Fragment() {
	
	private lateinit var viewBinding: FragmentHyperspaceJumpBinding
	private lateinit var navController: NavController
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewBinding = FragmentHyperspaceJumpBinding.inflate(inflater, container, false)
		navController = findNavController()
		return viewBinding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewBinding.layoutHeader.toolbar.apply {
			title = getString(R.string.hyperspace_jump)
			setNavigationOnClickListener { navController.popBackStack() }
			menu.clear()
		}
		
		AnimationUtils.loadAnimation(requireContext(), R.anim.hyperspace_jump).also {
			viewBinding.imageView.startAnimation(it)
		}
		
		AnimationUtils.loadAnimation(requireContext(), R.anim.pendulum).also {
			viewBinding.imageView2.startAnimation(it)
		}
	}
}