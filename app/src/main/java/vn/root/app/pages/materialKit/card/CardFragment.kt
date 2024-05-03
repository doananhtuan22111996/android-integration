package vn.root.app.pages.materialKit.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import vn.root.app.R
import vn.root.app.databinding.FragmentCardBinding

class CardFragment : Fragment() {
	
	private lateinit var viewBiding: FragmentCardBinding
	private lateinit var navController: NavController
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewBiding = FragmentCardBinding.inflate(inflater, container, false)
		navController = findNavController()
		return viewBiding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewBiding.layoutHeader.toolbar.apply {
			title = getString(R.string.card)
			setNavigationOnClickListener { navController.popBackStack() }
			menu.clear()
		}
	}
}