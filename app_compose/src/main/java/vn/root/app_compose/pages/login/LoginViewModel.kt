package vn.root.app_compose.pages.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.root.domain.model.ResultModel
import vn.root.domain.model.TokenModel
import vn.root.domain.usecase.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :
	ViewModel() {
	
	private val _loginState = MutableStateFlow<ResultModel<TokenModel>>(ResultModel.Done)
	val loginState = _loginState.asStateFlow()
	
	fun onLogin() {
		viewModelScope.launch {
			loginUseCase.execute().collect {
				_loginState.value = it
			}
		}
	}
}
