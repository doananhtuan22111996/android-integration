package vn.root.app.pages.animation

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

class AnimationFragment : Fragment() {

    private lateinit var viewBinding: FragmentMaterialKitBinding
    private lateinit var navController: NavController
    private lateinit var adapter: MaterialKitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MaterialKitAdapter(
            kits = listOf(
                MaterialKit(
                    id = 1,
                    header = getString(R.string.hyperspace_jump),
                    subHeader = getString(R.string.motion_makes_a_ui_expressive_and_easy_to_use),
                    action = { navController.navigate(R.id.action_animationFragment_to_hyperspaceJumpFragment) },
                ),
                MaterialKit(
                    id = 2,
                    header = getString(R.string.crossfade),
                    subHeader = getString(R.string.motion_makes_a_ui_expressive_and_easy_to_use),
                    action = { navController.navigate(R.id.action_animationFragment_to_crossfadeFragment) },
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
            title = getString(R.string.animation)
            setNavigationOnClickListener {
                navController.popBackStack()
            }
            menu.clear()
        }
        viewBinding.rcView.adapter = adapter
    }
}
