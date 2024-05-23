package vn.root.app_compose.pages.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import vn.root.domain.model.ResultModel
import vn.root.domain.model.TokenModel
import vn.root.domain.usecase.LoginComposeUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginComposeUseCase) :
	ViewModel() {
	
	private val _loginState = MutableStateFlow<ResultModel<TokenModel>>(ResultModel.Done)
	val loginState: StateFlow<ResultModel<TokenModel>> = _loginState
	
	fun onLogin() {
		loginUseCase.execute().onEach {
			println("${Thread.currentThread().name} $it")
			_loginState.value = it
		}.launchIn(viewModelScope)
	}
}
