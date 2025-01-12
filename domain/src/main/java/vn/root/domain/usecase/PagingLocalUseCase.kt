package vn.root.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vn.core.usecase.BaseUseCase
import vn.root.domain.model.ItemModel
import vn.root.domain.repository.PagingRepository
import javax.inject.Inject

class PagingLocalUseCase @Inject constructor(private val repository: PagingRepository) : BaseUseCase<Any, PagingData<ItemModel>>() {
    override fun execute(vararg params: Any?): Flow<PagingData<ItemModel>> = repository.getPagingLocal()
}
