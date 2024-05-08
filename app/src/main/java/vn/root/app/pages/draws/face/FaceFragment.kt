package vn.root.app.pages.draws.face

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import vn.root.app.R
import vn.root.app.databinding.FragmentFaceBinding

class FaceFragment : Fragment() {
	
	private lateinit var viewBinding: FragmentFaceBinding
	private lateinit var navController: NavController
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewBinding = FragmentFaceBinding.inflate(inflater, container, false)
		navController = findNavController()
		return viewBinding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewBinding.fragment = this
		viewBinding.layoutHeader.toolbar.apply {
			title = getString(R.string.face)
			menu.clear()
			setNavigationOnClickListener { navController.popBackStack() }
		}
	}
	
	fun onHappy() {
		viewBinding.faceView.setFaceType(Face.Happy)
	}
	
	fun onSad() {
		viewBinding.faceView.setFaceType(Face.Sad)
	}
}