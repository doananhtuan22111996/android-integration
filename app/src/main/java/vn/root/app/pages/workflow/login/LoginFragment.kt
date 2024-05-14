package vn.root.app.pages.workflow.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.root.app.R
import vn.root.app.base.BaseFragment
import vn.root.app.databinding.FragmentLoginBinding
import vn.root.app.pages.root.RootViewModel
import vn.root.domain.model.ResultModel

class LoginFragment : BaseFragment<RootViewModel, LoginViewModel, FragmentLoginBinding>() {
	
	override val sharedViewModel: RootViewModel by activityViewModel()
	
	override val viewModel: LoginViewModel by viewModel()
	
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