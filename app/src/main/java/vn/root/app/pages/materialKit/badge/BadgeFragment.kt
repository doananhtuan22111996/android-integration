package vn.root.app.pages.materialKit.badge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import vn.main.app.R
import vn.main.app.databinding.FragmentBadgeBinding

class BadgeFragment : Fragment() {

    private lateinit var viewBinding: FragmentBadgeBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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
        val badge1 = viewBinding.bottomNavigation.getOrCreateBadge(R.id.menu_add)
        badge1.isVisible = true
        // An icon only badge will be displayed unless a number or text is set:
        badge1.number = 99 // or badge.text = "New"

        val badge2 = viewBinding.bottomNavigation.getOrCreateBadge(R.id.menu_awesome)
        badge2.isVisible = true
        badge2.text = getString(R.string.welcome)

        val badge3 = viewBinding.bottomNavigation.getOrCreateBadge(R.id.menu_back)
        badge3.isVisible = true
    }
}
