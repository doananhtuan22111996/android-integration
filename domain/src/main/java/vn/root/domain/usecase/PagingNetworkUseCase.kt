package vn.root.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vn.root.domain.model.ItemModel
import vn.root.domain.repository.PagingRepository
import javax.inject.Inject

interface PagingNetworkUseCase {
	
	suspend fun getPagingNetwork(): Flow<PagingData<ItemModel>>
}

@Deprecated("Migrating to Hilt")
class PagingNetworkUseCaseImpl(private val repository: PagingRepository) : PagingNetworkUseCase {
	
	override suspend fun getPagingNetwork(): Flow<PagingData<ItemModel>> {
		return repository.getPagingNetwork()
	}
}

class PagingComposeNetworkUseCase @Inject constructor(private val repository: PagingRepository) :
	BaseUseCase<Any, PagingData<ItemModel>>() {
	override fun execute(vararg params: Any?): Flow<PagingData<ItemModel>> {
		return repository.getPagingNetwork()
	}
}
