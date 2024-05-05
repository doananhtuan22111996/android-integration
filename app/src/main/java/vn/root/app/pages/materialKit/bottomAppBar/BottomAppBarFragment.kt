package vn.root.app.pages.materialKit.bottomAppBar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.shape.MaterialShapeDrawable
import vn.root.app.R
import vn.root.app.databinding.FragmentBottomAppBarBinding

class BottomAppBarFragment : Fragment() {
	
	private lateinit var viewBinding: FragmentBottomAppBarBinding
	private lateinit var navController: NavController
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewBinding = FragmentBottomAppBarBinding.inflate(inflater, container, false)
		navController = findNavController()
		return viewBinding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewBinding.layoutHeader.toolbar.apply {
			title = context.getString(R.string.bottom_app_bar)
			setNavigationOnClickListener { navController.popBackStack() }
			menu.clear()
		}
		
		val topEdge = BottomAppBarCutCornersTopEdge(
			viewBinding.bottomAppBar.fabCradleMargin,
			viewBinding.bottomAppBar.fabCradleRoundedCornerRadius,
			viewBinding.bottomAppBar.cradleVerticalOffset
		)
		val background = viewBinding.bottomAppBar.background as MaterialShapeDrawable
		background.shapeAppearanceModel =
			background.shapeAppearanceModel.toBuilder().setTopEdge(topEdge).build()
	}
}