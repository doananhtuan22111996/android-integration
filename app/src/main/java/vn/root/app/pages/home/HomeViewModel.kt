package vn.root.app.pages.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import vn.root.app.base.BaseViewModel
import vn.root.domain.model.ItemModel
import vn.root.domain.usecase.PagingNetworkUseCase

class HomeViewModel(private val pagingUseCase: PagingNetworkUseCase) : BaseViewModel() {

    suspend fun paging(): Flow<PagingData<ItemModel>> =
        pagingUseCase.getPagingNetwork().cachedIn(viewModelScope)
}
