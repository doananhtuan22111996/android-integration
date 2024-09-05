package vn.root.app.pages.workflow.left

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import vn.core.ui.base.BaseViewModel
import vn.root.domain.usecase.PagingNetworkUseCase
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LeftViewModel @Inject constructor(private val pagingUseCase: PagingNetworkUseCase) :
    BaseViewModel() {
    private val langCode = Locale.getDefault().language
    private val _langCodeState = MutableStateFlow<String>(langCode)

    @OptIn(ExperimentalCoroutinesApi::class)
    val paging = _langCodeState.flatMapLatest { lang ->
        pagingUseCase.execute(lang)
    }.cachedIn(viewModelScope)

    fun onLangChanged(lang: String) {
        _langCodeState.value = lang
    }
}
