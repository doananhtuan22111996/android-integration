package vn.root.app.pages.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import timber.log.Timber
import vn.main.app.R
import vn.main.app.databinding.FragmentBoardingBinding
import vn.root.app.pages.perAppLanguage.PerAppLanguageBottomSheet

class BoardingFragment : Fragment() {

    private lateinit var viewBinding: FragmentBoardingBinding
    private lateinit var navController: NavController

    private val bottomSheet = PerAppLanguageBottomSheet { lang ->
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(lang.code)

        // Call this on the main thread as it may require Activity.restart()
        Timber.d("PerAppLanguageBottomSheet: ${AppCompatDelegate.getApplicationLocales().get(0)?.displayLanguage}")
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentBoardingBinding.inflate(inflater, container, false)
        navController = findNavController()
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewBinding.layoutHeader.toolbar.apply {
            title = context.getString(R.string.root_kit)
            setNavigationIcon(R.drawable.baseline_menu_24)
            setNavigationOnClickListener {
                // TODO drawable navigation
            }
            menu.clear()
        }

        viewBinding.layoutContent1.let {
            it.tvHeader.text = getString(R.string.material_kit)
            it.tvSubHeader.text =
                getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor)
            it.root.setOnClickListener {
                navController.navigate(R.id.action_boardingFragment_to_materialKitFragment)
            }

        }
        viewBinding.layoutContent2.let {
            it.tvHeader.text = getString(R.string.workflow)
            it.tvSubHeader.text =
                getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor)
            it.root.setOnClickListener {
                navController.navigate(R.id.action_boardingFragment_to_workflowFragment)
            }
        }
        viewBinding.layoutContent3.let {
            it.tvHeader.text = getString(R.string.animation)
            it.tvSubHeader.text =
                getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor)
            it.root.setOnClickListener {
                navController.navigate(R.id.action_boardingFragment_to_animationFragment)
            }
        }
        viewBinding.layoutContent4.let {
            it.tvHeader.text = getString(R.string.drawable)
            it.tvSubHeader.text =
                getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor)
            it.root.setOnClickListener {
                navController.navigate(R.id.action_boardingFragment_to_drawFragment)
            }
        }

        viewBinding.layoutContent5.let {
            it.tvHeader.text = getString(R.string.per_app_language)
            it.tvSubHeader.text =
                getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor)
            it.root.setOnClickListener {
                bottomSheet.show(childFragmentManager, PerAppLanguageBottomSheet.TAG)
            }
        }
    }
}