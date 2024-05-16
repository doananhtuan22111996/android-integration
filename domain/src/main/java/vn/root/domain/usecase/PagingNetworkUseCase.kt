package vn.root.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vn.root.domain.model.ItemModel
import vn.root.domain.repository.PagingRepository

interface PagingNetworkUseCase {
	
	suspend fun getPagingNetwork(): Flow<PagingData<ItemModel>>
}

class PagingNetworkUseCaseImpl(private val repository: PagingRepository) : PagingNetworkUseCase {
	
	override suspend fun getPagingNetwork(): Flow<PagingData<ItemModel>> {
		return repository.getPagingNetwork()
	}
}
