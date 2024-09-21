package vn.root.app.pages.animation.crossfade

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import vn.main.app.R
import vn.main.app.databinding.FragmentCrossfadeBinding

class CrossfadeFragment : Fragment() {
	
	private lateinit var viewBinding: FragmentCrossfadeBinding
	private lateinit var navController: NavController
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewBinding = FragmentCrossfadeBinding.inflate(inflater, container, false)
		navController = findNavController()
		return viewBinding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewBinding.layoutHeader.toolbar.apply {
			title = getString(R.string.crossfade)
			menu.clear()
			setNavigationOnClickListener { navController.popBackStack() }
		}
		
		viewBinding.btnInvisible.setOnClickListener {
			AnimationUtils.loadAnimation(requireContext(), R.anim.crossfade_invisible).also {
				viewBinding.textView.apply {
					visibility = View.INVISIBLE
					startAnimation(it)
				}
			}
		}
		
		viewBinding.btnVisible.setOnClickListener {
			AnimationUtils.loadAnimation(requireContext(), R.anim.crossfade_visible).also {
				viewBinding.textView.apply {
					visibility = View.VISIBLE
					startAnimation(it)
				}
			}
		}
	}
}