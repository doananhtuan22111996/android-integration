package vn.root.app.pages.workflow.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import vn.root.app.base.BaseViewModel
import vn.root.domain.model.ResultModel
import vn.root.domain.model.TokenModel
import vn.root.domain.usecase.LoginUseCase
import vn.root.domain.usecase.LogoutUseCase

class LoginViewModel(
	private val loginUseCase: LoginUseCase, private val logoutUseCase: LogoutUseCase
) : BaseViewModel() {
	
	private val _loginState = MutableStateFlow<ResultModel<TokenModel>>(value = ResultModel.Done)
	val login: StateFlow<ResultModel<TokenModel>> = _loginState
	
	fun onLogin() {
		viewModelScope.launch {
			loginUseCase.login().collect {
				_loginState.value = it
			}
		}
	}
}
