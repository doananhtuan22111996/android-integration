package vn.root.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vn.root.domain.model.ItemModel

interface PagingRepository {

    suspend fun getPagingNetwork(): Flow<PagingData<ItemModel>>

    suspend fun getPagingLocal(): Flow<PagingData<ItemModel>>
}
