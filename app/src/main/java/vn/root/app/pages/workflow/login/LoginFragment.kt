package vn.root.app.pages.workflow.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import vn.core.domain.ResultModel
import vn.core.ui.base.BaseFragment
import vn.main.app.R
import vn.main.app.databinding.FragmentLoginBinding
import vn.root.app.pages.root.RootViewModel

@AndroidEntryPoint
class LoginFragment : BaseFragment<RootViewModel, LoginViewModel, FragmentLoginBinding>() {

    override val sharedViewModel: RootViewModel by activityViewModels()

    override val viewModel: LoginViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding =
        FragmentLoginBinding::inflate

    override fun onInit(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnLogin.setOnClickListener {
            viewModel.onLogin()
        }
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.login.collect {
                    when (it) {
                        is ResultModel.Success -> navController.navigate(R.id.action_loginFragment_to_homeFragment)
                        is ResultModel.AppException -> viewModel.setAppException(it)
                        is ResultModel.Done -> viewModel.setLoadingOverlay(false)
                        else -> viewModel.setLoadingOverlay(true)
                    }
                }
            }
        }
    }
}
