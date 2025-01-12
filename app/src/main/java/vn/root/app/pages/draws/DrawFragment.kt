package vn.root.app.pages.draws

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import vn.main.app.R
import vn.main.app.databinding.FragmentMaterialKitBinding
import vn.root.app.pages.materialKit.MaterialKit
import vn.root.app.pages.materialKit.MaterialKitAdapter

class DrawFragment : Fragment() {

    private lateinit var viewBinding: FragmentMaterialKitBinding
    private lateinit var navController: NavController
    private lateinit var adapter: MaterialKitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MaterialKitAdapter(
            kits = listOf(
                MaterialKit(
                    id = 1,
                    header = getString(R.string.face),
                    subHeader = getString(R.string.use_typography_to_present_your_design_and_content_as_clearly_and_efficiently_as_possible),
                    action = { navController.navigate(R.id.action_drawFragment_to_faceFragment) },
                ),
            ),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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
