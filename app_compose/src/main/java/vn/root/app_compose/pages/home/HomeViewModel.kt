package vn.root.app_compose.pages.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import vn.root.domain.model.ItemModel
import vn.root.domain.usecase.PagingComposeLocalUseCase
import vn.root.domain.usecase.PagingComposeNetworkUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	pagingComposeNetworkUseCase: PagingComposeNetworkUseCase,
	pagingComposeLocalUseCase: PagingComposeLocalUseCase,
) : ViewModel() {
	
	val networkScrollState by mutableStateOf(LazyListState())
	val localScrollState by mutableStateOf(LazyListState())
	
	val networkPaging: Flow<PagingData<ItemModel>> =
		pagingComposeNetworkUseCase.execute().cachedIn(viewModelScope)
	
	val localPaging: Flow<PagingData<ItemModel>> =
		pagingComposeLocalUseCase.execute().cachedIn(viewModelScope)
}