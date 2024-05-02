package vn.root.app.pages.materialKit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import vn.root.app.R
import vn.root.app.databinding.FragmentMaterialKitBinding

class MaterialKitFragment : Fragment() {
	
	private lateinit var viewBinding: FragmentMaterialKitBinding
	private lateinit var navController: NavController
	private lateinit var adapter: MaterialKitAdapter
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		adapter = MaterialKitAdapter(
			kits = listOf(
				MaterialKit(
					id = 1,
					header = getString(R.string.typography),
					subHeader = getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor),
					action = { navController.navigate(R.id.action_materialKitFragment_to_typographyFragment) },
				), MaterialKit(
					id = 2,
					header = getString(R.string.button),
					subHeader = getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor),
					action = { navController.navigate(R.id.action_materialKitFragment_to_typographyFragment) },
				), MaterialKit(
					id = 3,
					header = getString(R.string.card),
					subHeader = getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor),
					action = { navController.navigate(R.id.action_materialKitFragment_to_typographyFragment) },
				)
			)
		)
	}
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewBinding = FragmentMaterialKitBinding.inflate(inflater, container, false)
		navController = findNavController()
		return viewBinding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewBinding.layoutHeader.toolbar.apply {
			title = getString(R.string.material_kit)
			setNavigationOnClickListener {
				navController.popBackStack()
			}
			menu.clear()
		}
		viewBinding.rcView.adapter = adapter
	}
}