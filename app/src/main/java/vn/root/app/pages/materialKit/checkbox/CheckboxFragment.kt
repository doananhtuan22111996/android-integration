package vn.root.app.pages.materialKit.checkbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import vn.main.app.R
import vn.main.app.databinding.FragmentCheckboxBinding

class CheckboxFragment : Fragment() {
	
	private lateinit var viewBinding: FragmentCheckboxBinding
	private lateinit var navController: NavController
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewBinding = FragmentCheckboxBinding.inflate(inflater, container, false)
		navController = findNavController()
		return viewBinding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewBinding.layoutHeader.toolbar.apply {
			title = context.getString(R.string.checkbox)
			setNavigationOnClickListener { navController.popBackStack() }
			menu.clear()
		}
	}
}