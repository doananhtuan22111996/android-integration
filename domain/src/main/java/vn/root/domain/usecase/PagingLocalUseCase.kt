package vn.root.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vn.root.domain.model.ItemModel
import vn.root.domain.repository.PagingRepository

interface PagingLocalUseCase {

    suspend fun getPagingLocal(): Flow<PagingData<ItemModel>>

}

class PagingLocalUseCaseImpl(private val repository: PagingRepository) : PagingLocalUseCase {

    override suspend fun getPagingLocal(): Flow<PagingData<ItemModel>> {
        return repository.getPagingLocal()
    }
}
