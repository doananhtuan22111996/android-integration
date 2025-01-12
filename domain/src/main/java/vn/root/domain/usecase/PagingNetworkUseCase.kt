package vn.root.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vn.core.usecase.BaseUseCase
import vn.root.domain.model.ItemModel
import vn.root.domain.repository.PagingRepository
import javax.inject.Inject

class PagingNetworkUseCase @Inject constructor(private val repository: PagingRepository) : BaseUseCase<String, PagingData<ItemModel>>() {
    override fun execute(vararg params: String?): Flow<PagingData<ItemModel>> = repository.getPagingNetwork("en")
}
