package vn.root.app.pages.workflow.right

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import vn.core.ui.base.BaseViewModel
import vn.root.domain.usecase.PagingLocalUseCase
import javax.inject.Inject

@HiltViewModel
class RightViewModel @Inject constructor(private val pagingUseCase: PagingLocalUseCase) : BaseViewModel() {

    // Key trigger
    private val _pagingState = MutableStateFlow(Unit)

    @OptIn(ExperimentalCoroutinesApi::class)
    val paging = _pagingState.flatMapLatest { pagingUseCase.execute() }.cachedIn(viewModelScope)
}
