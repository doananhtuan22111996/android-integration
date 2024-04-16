package vn.root.app.pages.homeLocal

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import vn.root.app.base.BaseViewModel
import vn.root.domain.model.ItemModel
import vn.root.domain.usecase.PagingLocalUseCase

class HomeLocalViewModel(private val pagingUseCase: PagingLocalUseCase) : BaseViewModel() {

    suspend fun paging(): Flow<PagingData<ItemModel>> =
        pagingUseCase.getPagingLocal().cachedIn(viewModelScope)
}
