package vn.root.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vn.root.domain.model.ItemModel
import vn.root.domain.repository.PagingRepository
import javax.inject.Inject

class PagingNetworkUseCase @Inject constructor(private val repository: PagingRepository) :
	BaseUseCase<String, PagingData<ItemModel>>() {
	override fun execute(vararg params: String?): Flow<PagingData<ItemModel>> {
		return repository.getPagingNetwork(params[0] ?: "en")
	}
}
