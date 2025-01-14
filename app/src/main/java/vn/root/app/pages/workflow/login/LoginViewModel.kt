package vn.root.app.pages.workflow.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import vn.core.domain.ResultModel
import vn.core.ui.base.BaseViewModel
import vn.root.domain.model.TokenModel
import vn.root.domain.usecase.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {

    private val _loginState = MutableStateFlow<ResultModel<TokenModel>>(value = ResultModel.Done)
    val login: StateFlow<ResultModel<TokenModel>> = _loginState

    fun onLogin() {
        viewModelScope.launch {
            loginUseCase.execute().collect {
                _loginState.value = it
            }
        }
    }
}
