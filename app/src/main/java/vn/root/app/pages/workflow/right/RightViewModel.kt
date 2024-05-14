package vn.root.app.pages.workflow.right

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import vn.root.app.base.BaseViewModel
import vn.root.domain.model.ItemModel
import vn.root.domain.usecase.PagingLocalUseCase

class RightViewModel(private val pagingUseCase: PagingLocalUseCase) : BaseViewModel() {
	
	// Key trigger
	private val _pagingState = MutableStateFlow(Unit)
	
	@OptIn(ExperimentalCoroutinesApi::class)
	val paging =
		_pagingState.flatMapLatest { pagingUseCase.getPagingLocal() }.cachedIn(viewModelScope)
	
	init {
		_pagingState.value = Unit
	}
}
